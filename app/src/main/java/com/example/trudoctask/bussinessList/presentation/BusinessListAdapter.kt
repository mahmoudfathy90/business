package com.example.trudoctask.bussinessList.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IntDef
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trudoctask.R
import com.example.trudoctask.businessDetails.data.service.response.BusinessModel
import com.example.trudoctask.databinding.BusinessItemLayout
import com.example.trudoctask.databinding.ErrorLayout


class BusinessListAdapter(var detailsInterface: OpenDetailsInterface) :
    PagedListAdapter<BusinessModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    private var viewState = BusinessListState()
        set(value) {
            if (field == value) return
            if (value.loadingMore || value.errorLoadMore != null) {
                if (field.loadingMore || field.errorLoadMore != null) {
                    notifyItemChanged(currentList?.size!!)
                } else {
                    notifyItemInserted(currentList?.size!!)
                }
            } else {
                notifyItemRemoved(currentList?.size!!)
            }
            field = value
        }

    override fun getItemCount(): Int {
        if (viewState.loadingMore || viewState.errorLoadMore != null) {
            return super.getItemCount() + 1
        }
        return super.getItemCount()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == currentList?.size) {
            when {
                viewState.loadingMore -> {
                    AdapterViewType.LOADING
                }
                viewState.errorLoadMore != null -> {
                    AdapterViewType.ERROR
                }
                else -> {
                    AdapterViewType.NORMAL
                }
            }
        } else AdapterViewType.NORMAL
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BusinessModel>() {
            // The ID property identifies when items are the same.
            override fun areItemsTheSame(oldItem: BusinessModel, newItem: BusinessModel) =
                oldItem.id == newItem.id

            // If you use the "==" operator, make sure that the object implements
            // .equals(). Alternatively, write custom data comparison logic here.
            override fun areContentsTheSame(
                oldItem: BusinessModel, newItem: BusinessModel
            ) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            AdapterViewType.LOADING -> {
                val view = layoutInflater.inflate(R.layout.loading, parent, false)
                object : RecyclerView.ViewHolder(view) {}
            }
            AdapterViewType.ERROR -> {
                val binding = ErrorLayout.inflate(layoutInflater, parent, false)
                ErrorViewHolder(binding, viewState.errorLoadMore, detailsInterface)
            }
            else -> {
                val binding = BusinessItemLayout.inflate(layoutInflater, parent, false)
                ItemViewHolder(binding, detailsInterface)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            if (itemCount - 1 > position) {
                val item: BusinessModel = getItem(position)!!
                holder.bind(item)
            }
        }

    }

    fun setState(viewState: BusinessListState) {
        this.viewState = viewState
    }

    class ItemViewHolder(
        private val binding: BusinessItemLayout,
        private val openDetailsInterface: OpenDetailsInterface
    ) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            binding.handler = openDetailsInterface
        }

        fun bind(item: BusinessModel) {
            binding.model = item
        }
    }

    class ErrorViewHolder(
        binding: ErrorLayout,
        val error: Throwable?,
        openDetailsInterface: OpenDetailsInterface
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.error = error
            binding.handler = openDetailsInterface
        }


    }
}

@IntDef(AdapterViewType.LOADING, AdapterViewType.ERROR, AdapterViewType.NORMAL)
annotation class AdapterViewType {
    companion object {
        const val LOADING = 1
        const val ERROR = 2
        const val NORMAL = 3
    }
}
