package com.example.trudoctask.businessDetails.presentation.screens.details

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.example.trudoctask.BaseFragmentWithInjector
import com.example.trudoctask.R
import com.example.trudoctask.databinding.ActivityDetailsBinding
import com.example.trudoctask.utils.ContextUtils.goToMap
import com.example.trudoctask.utils.ContextUtils.makeCall
import com.example.trudoctask.utils.ContextUtils.openUrlInBrowser
import com.example.trudoctask.utils.ContextUtils.share


class DetailsFragment : BaseFragmentWithInjector() {


    private val viewModel: DetailsViewModel by lazy {
        vm as DetailsViewModel
    }

    private lateinit var binding: ActivityDetailsBinding
    private val navArg by navArgs<DetailsFragmentArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
        viewModel.callEvent.observe(viewLifecycleOwner, Observer {
            context?.makeCall(it)
        })
        viewModel.showOnMapEvent.observe(viewLifecycleOwner, Observer {
            context?.goToMap(it.first, it.second)

        })
        viewModel.visitSiteEvent.observe(viewLifecycleOwner, Observer {
            context?.openUrlInBrowser(it)
        })
        viewModel.shareEvent.observe(viewLifecycleOwner, Observer {
            context?.share(it)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.share ->{
                viewModel.onShareClicked()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getFragmentVM(): Class<out ViewModel> {
        return DetailsViewModel::class.java
    }


}

