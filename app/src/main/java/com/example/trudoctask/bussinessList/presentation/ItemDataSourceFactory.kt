package com.example.trudoctask.bussinessList.presentation

import androidx.compose.runtime.resetSourceInfo
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.trudoctask.businessDetails.data.service.response.BusinessModel

import javax.inject.Inject


class ItemDataSourceFactory @Inject constructor(private val item: ItemDataSource) :
    DataSource.Factory<Int, BusinessModel>() {

    private val itemLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, BusinessModel>> =
        MutableLiveData()


    override fun create(): DataSource<Int, BusinessModel> {
        itemLiveDataSource.postValue(item)
        return item
    }

    fun stateEvent() = item.stateEvent

    fun getLiveData() = itemLiveDataSource
    fun getDataSource() = item
    fun search(term: String) {
        item.search(term)
//        itemLiveDataSource.value?.invalidate()

    }

}