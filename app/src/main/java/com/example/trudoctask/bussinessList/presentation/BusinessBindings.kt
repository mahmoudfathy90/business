package com.example.trudoctask.bussinessList.presentation

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.example.trudoctask.businessDetails.data.service.response.BusinessModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingMethods(
    value = [
        BindingMethod(type = RecyclerView::class, attribute = "adapter", method = "setAdapter")
    ]
)
object BusinessBindings {
    @BindingAdapter("businessList", "viewState")
    @JvmStatic
    fun RecyclerView.setBusinessList(
        businessList: PagedList<BusinessModel>?,
        viewState: BusinessListState?
    ) {
        businessList?.let { (adapter as? BusinessListAdapter)?.submitList(businessList) }
        viewState?.let { (adapter as? BusinessListAdapter)?.setState(viewState) }
    }


    @BindingAdapter("categoriesChips")
    @JvmStatic
    fun ChipGroup.setCategories(categoriesList: List<BusinessModel.Category>?) {
        if (categoriesList.isNullOrEmpty()) return
        this.removeAllViews()
        categoriesList.forEach { this.addView(context.createCategoryChip(it))}
    }

    private fun Context.createCategoryChip(category: BusinessModel.Category): Chip {
        return Chip(this).apply {
            isCheckable = false
            isCloseIconVisible = false
            isClickable = false
            text = category.title
        }
    }

    @BindingAdapter("textVisibility")
    @JvmStatic
    fun TextView.setVisibility(text :String?){
      visibility = if (text.isNullOrBlank()) View.GONE else View.VISIBLE
    }

}