package com.example.trudoctask.businessDetails.presentation.screens.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trudoctask.businessDetails.data.service.response.BusinessModel
import com.example.trudoctask.businessDetails.domain.BusinessDetailsResult
import com.example.trudoctask.businessDetails.domain.DetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val useCase: DetailsUseCase):
    ViewModel() {
    private var defaultViewState=State()
   public val event = MutableLiveData<State>()


    /**
     * Function used to get details of business by id
     * @param id [String] id of business  to get it's details
     */
    fun getDetails(id:String?){
        GlobalScope.launch(Dispatchers.IO) {
            var viewState = defaultViewState.copy(loading = true)
            event.postValue(viewState)
            val result = useCase.execute(id)
            viewState = when(result){
                is BusinessDetailsResult.Error -> defaultViewState.copy(error = result.throwable)
                is BusinessDetailsResult.Success -> defaultViewState.copy(data = result.data)
                else -> defaultViewState.copy()
            }
            event.postValue(viewState)
        }
    }

    data class State(val loading:Boolean = false,val  error:Throwable?= null,val data :BusinessModel?=null)

}