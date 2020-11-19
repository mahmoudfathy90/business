package com.example.trudoctask.businessDetails.presentation.di.component

import com.example.trudoctask.BaseFragmentWithInjector
import com.example.trudoctask.businessDetails.presentation.di.module.*
import com.example.trudoctask.businessDetails.presentation.myApp.MyApplication


import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, NetworkModule::class,ViewModelModule::class
        ,DetailsModule::class,ListModule::class]
)

interface NetworkComponent {

 fun inject(fragment: BaseFragmentWithInjector)



    @Component.Builder
    interface NetworkBuilder {
        fun builder(): NetworkComponent
        @BindsInstance
        fun application(app: MyApplication): NetworkBuilder
        @BindsInstance
        fun baseUrl(baseUrl: String): NetworkBuilder

    }
}