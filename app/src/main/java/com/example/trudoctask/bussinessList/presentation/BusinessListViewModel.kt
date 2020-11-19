package com.example.trudoctask.bussinessList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.trudoctask.businessDetails.data.service.response.BusinessModel
import com.example.trudoctask.utils.Constants.Companion.PAGE_SIZE
import javax.inject.Inject

class BusinessListViewModel @Inject constructor(private val item: ItemDataSourceFactory) :
    ViewModel() {
    var itemPagedList: LiveData<PagedList<BusinessModel>>


    init {
        val config = PagedList.Config.Builder().setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        itemPagedList = LivePagedListBuilder(item, config).build()
    }

    fun stateEvent() = item.stateEvent()

    fun search(term:String){
        item.getDataSource().search(term)
        val config = PagedList.Config.Builder().setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        itemPagedList = LivePagedListBuilder(item, config).build()
    }

    override fun onCleared() {
        item.getDataSource().cancelJob()
        super.onCleared()
    }

    fun retry() {
        item.getDataSource().retry?.let { it() }
    }
}