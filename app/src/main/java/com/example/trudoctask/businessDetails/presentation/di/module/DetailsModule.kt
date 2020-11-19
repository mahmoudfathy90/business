package com.example.trudoctask.businessDetails.presentation.di.module

import androidx.lifecycle.ViewModel
import com.example.trudoctask.businessDetails.data.repository.DetailsRepository
import com.example.trudoctask.businessDetails.domain.IDetailsRepository
import com.example.trudoctask.businessDetails.presentation.screens.details.DetailsViewModel
import dagger.Binds

import dagger.Module
import dagger.multibindings.IntoMap

@Module
 abstract  class DetailsModule {



    @Binds
    abstract fun details(repository: DetailsRepository) :IDetailsRepository


    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    internal abstract fun detailsViewModel(viewModel: DetailsViewModel): ViewModel
}