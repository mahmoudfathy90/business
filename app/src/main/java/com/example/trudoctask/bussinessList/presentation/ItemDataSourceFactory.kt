package com.example.trudoctask.bussinessList.presentation

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.trudoctask.businessDetails.data.service.response.BusinessModel

import javax.inject.Inject
import javax.inject.Provider


class ItemDataSourceFactory @Inject constructor(private var item: ItemDataSource) :
    DataSource.Factory<Int, BusinessModel>() {
    @Inject lateinit var itemDataSourceProvider : Provider<ItemDataSource>
    private val itemLiveDataSource: MutableLiveData<PageKeyedDataSource<Int, BusinessModel>> =
        MutableLiveData()


    override fun create(): DataSource<Int, BusinessModel> {
        itemLiveDataSource.postValue(item)
        return item
    }

    fun stateEvent() = item.stateEvent
    fun getDataSource() = item
    fun search(term: String?) {
        item = itemDataSourceProvider.get()
        item.search(term)
        itemLiveDataSource.value?.invalidate()
    }

}