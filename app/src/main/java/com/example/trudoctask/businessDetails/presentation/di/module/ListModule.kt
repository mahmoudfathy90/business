package com.example.trudoctask.businessDetails.presentation.di.module

import androidx.lifecycle.ViewModel
import com.example.trudoctask.bussinessList.data.repository.ListRepository
import com.example.trudoctask.bussinessList.domain.IListRepository
import com.example.trudoctask.bussinessList.presentation.BusinessListViewModel
import dagger.Binds

import dagger.Module
import dagger.multibindings.IntoMap

@Module
 abstract  class ListModule {



    @Binds
    abstract fun businessList(repository: ListRepository) :IListRepository


    @Binds
    @IntoMap
    @ViewModelKey(BusinessListViewModel::class)
    internal abstract fun itemViewModel(viewModel: BusinessListViewModel):ViewModel

}