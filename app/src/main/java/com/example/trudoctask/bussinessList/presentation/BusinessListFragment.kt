package com.example.trudoctask.bussinessList.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.trudoctask.BaseFragmentWithInjector
import com.example.trudoctask.databinding.BusinessList


class BusinessListFragment : BaseFragmentWithInjector(), OpenDetailsInterface {


    private val businessAdapter by lazy { BusinessListAdapter( this) }


    private val businessListViewModel: BusinessListViewModel by lazy {
        ViewModelProviders.of(this).get(BusinessListViewModel::class.java)
    }

    private lateinit var binding: BusinessList


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BusinessList.inflate(inflater, container, false)
        binding.apply {
            viewModel = businessListViewModel
            lifecycleOwner = viewLifecycleOwner
            adapter = businessAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSearch.setOnClickListener {
            businessAdapter.submitList(null)
            businessListViewModel.search(binding.etSearch.text.toString())
        }
    }


    override fun getFragmentVM(): Class<out ViewModel> {
        return BusinessListViewModel::class.java
    }


    override fun openDetails(id: String?) {
        val direction =
            BusinessListFragmentDirections.actionListScreenToDetailsFragment(id)
        findNavController().navigate(direction)
    }

    override fun retry() {
        businessListViewModel.retry()
    }

}




