package com.example.trudoctask.bussinessList.domain

import com.example.trudoctask.businessDetails.data.service.response.BusinessModel


sealed class BusinessListResult {
    data class Success(val data: List<BusinessModel>) : BusinessListResult()
    object Loading : BusinessListResult()
    object LoadingMore : BusinessListResult()
    data class Error(val throwable: Throwable?) : BusinessListResult()
    data class ErrorLoadMore(val throwable: Throwable?) : BusinessListResult()
    object Empty : BusinessListResult()


}