package com.example.trudoctask.bussinessList.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.trudoctask.businessDetails.data.service.response.BusinessModel

import com.example.trudoctask.bussinessList.data.service.reguest.ListRequestModel
import com.example.trudoctask.bussinessList.domain.BusinessListResult
import com.example.trudoctask.bussinessList.domain.ListUseCase
import com.example.trudoctask.utils.Constants.Companion.DEFALUT_CITY
import com.example.trudoctask.utils.Constants.Companion.FIRSTPAGE
import com.example.trudoctask.utils.Constants.Companion.PAGE_SIZE
import kotlinx.coroutines.*
import javax.inject.Inject


class ItemDataSource @Inject constructor(private val useCase: ListUseCase) :
    PageKeyedDataSource<Int, BusinessModel>() {


    private var defaultViewState = BusinessListState()
    val stateEvent = MutableLiveData<BusinessListState>()
    private var modelRequest = ListRequestModel(DEFALUT_CITY, limit = PAGE_SIZE, offset = FIRSTPAGE)
    var retry: (() -> Unit)? = null
    private lateinit var job: Job
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, BusinessModel>
    ) {
        loadData(onLoading = {
            stateEvent.postValue(defaultViewState.copy(loading = true))
        }, onError = {
            stateEvent.postValue(defaultViewState.copy(error = it))
        }) { result ->
            callback.onResult(result.data, null, FIRSTPAGE)
        }

    }

    fun search(term:String){
        modelRequest = modelRequest.copy(term = term)
    }

    private fun loadData(
        param: LoadParams<Int>? = null,
        onLoading: () -> Unit,
        onError: (Throwable?) -> Unit,
        onResult: (BusinessListResult.Success) -> Unit
    ) {
        val currentPage = param?.key ?: FIRSTPAGE
        Log.e("currentPAge ", " : $currentPage")
        job = GlobalScope.launch(Dispatchers.IO) {
            onLoading()
            val result = useCase.execute(
                requestModel = modelRequest.copy(
                    offset = PAGE_SIZE.times(currentPage)
                )
            )
            when (result) {
                is BusinessListResult.Error -> {
                    onError(result.throwable)
                    retry = {loadData(param, onLoading, onError, onResult)}
                }
                is BusinessListResult.Success -> {
                    onResult.invoke(result)
                }
            }

        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BusinessModel>) {
        loadData(
            params,
            onLoading = {
                stateEvent.postValue(defaultViewState.copy(loadingMore = true))
            },
            onError = {
                stateEvent.postValue(defaultViewState.copy(errorLoadMore = it))
            }
        ) {
            callback.onResult(it.data, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BusinessModel>) {
        //Ignored because loadInitial
    }

    /**
     * function to cancel the GlobalScope in view Model
     */
    fun cancelJob() {
        job.cancel()
    }


}

data class BusinessListState(
    val loading: Boolean = false,
    val empty: Boolean = false,
    val error: Throwable? = null,
    val loadingMore: Boolean = false,
    val errorLoadMore: Throwable? = null
)
