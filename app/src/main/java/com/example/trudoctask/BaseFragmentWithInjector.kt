package com.example.trudoctask

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.trudoctask.businessDetails.presentation.di.module.ViewModelFactory
import com.example.trudoctask.businessDetails.presentation.myApp.MyApplication

import javax.inject.Inject

abstract class BaseFragmentWithInjector : androidx.fragment.app.Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory


    lateinit var vm: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as MyApplication).networkComponent.inject(this)
         vm = ViewModelProviders.of(this, factory)[getFragmentVM()]
    }

    abstract fun getFragmentVM(): Class<out ViewModel>



}