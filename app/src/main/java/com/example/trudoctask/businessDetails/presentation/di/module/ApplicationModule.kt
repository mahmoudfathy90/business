package com.example.trudoctask.businessDetails.presentation.di.module

import android.content.Context
import com.example.trudoctask.businessDetails.presentation.myApp.MyApplication
import com.example.trudoctask.businessDetails.presentation.di.qualifiers.ForApplication

import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @ForApplication
    fun applicationContext(app: MyApplication): Context {
        return app.applicationContext
    }

}