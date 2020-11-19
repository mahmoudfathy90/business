package com.example.trudoctask.businessDetails.presentation.screens.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.trudoctask.businessDetails.data.service.response.BusinessModel
import com.example.trudoctask.businessDetails.domain.BusinessDetailsResult
import com.example.trudoctask.businessDetails.domain.DetailsUseCase
import com.example.trudoctask.utils.EnhancedLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val useCase: DetailsUseCase) :
    ViewModel() {
    private var defaultViewState = State()
    val event = MutableLiveData<State>()
    val businessModel = Transformations.map(event){
        it.data
    }

    val callEvent = EnhancedLiveEvent<String>()
    val shareEvent = EnhancedLiveEvent<String>()
    val visitSiteEvent = EnhancedLiveEvent<String>()
    val showOnMapEvent = EnhancedLiveEvent<Pair<Double, Double>>()

    /**
     * Function used to get details of business by id
     * @param id [String] id of business  to get it's details
     */
    fun getDetails(id: String?) {
        GlobalScope.launch(Dispatchers.IO) {
            var viewState = defaultViewState.copy(loading = true)
            event.postValue(viewState)
            val result = useCase.execute(id)
            viewState = when (result) {
                is BusinessDetailsResult.Error -> defaultViewState.copy(error = result.throwable)
                is BusinessDetailsResult.Success -> defaultViewState.copy(data = result.data)
                else -> defaultViewState.copy()
            }
            event.postValue(viewState)
        }
    }

    fun onCallClicked(){
        businessModel.value?.phone?.let {
            callEvent.postValue(it)
        }
    }

    fun onVisitSiteClicked(){
        businessModel.value?.url?.let {
            visitSiteEvent.postValue(it)
        }
    }

    fun onShowOnMapClicked(){
        businessModel.value?.coordinates?.takeIf { it.latitude != null && it.longitude != null }?.let {
            showOnMapEvent.postValue(Pair(it.latitude!!,it.longitude!!))
        }
    }

    fun onShareClicked() {
        businessModel.value?.let {
            shareEvent.postValue("my business name is  ${it.name}  and my categories is ${it.categoriesText()}and my price category us  ${it.price} and review count ${it.reviewCount} and  rating is ${it.rating} ")
        }
    }

    data class State(
        val loading: Boolean = false,
        val error: Throwable? = null,
        val data: BusinessModel? = null
    )

}