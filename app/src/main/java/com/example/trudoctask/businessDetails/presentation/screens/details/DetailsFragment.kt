package com.example.trudoctask.businessDetails.presentation.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.example.trudoctask.BaseFragmentWithInjector
import com.example.trudoctask.R
import com.example.trudoctask.databinding.ActivityDetailsBinding
import com.example.trudoctask.utils.makeCall
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.custom_details_layout.view.*


class DetailsFragment : BaseFragmentWithInjector() {


    private val viewModel: DetailsViewModel by lazy {
        vm as DetailsViewModel
    }

    private lateinit var binding: ActivityDetailsBinding
    private val navArg by navArgs<DetailsFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.activity_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getDetails(navArg.id)
        phone.value.setOnClickListener {
            it.makeCall(viewModel.event.value?.data?.phone)
        }

    }


    override fun getFragmentVM(): Class<out ViewModel> {
        return DetailsViewModel::class.java
    }


}