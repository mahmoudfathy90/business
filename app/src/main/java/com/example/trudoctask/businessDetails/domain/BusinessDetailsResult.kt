package com.example.trudoctask.businessDetails.domain

import com.example.trudoctask.businessDetails.data.service.response.BusinessModel

sealed class BusinessDetailsResult{
    data class Success(val data: BusinessModel) : BusinessDetailsResult()
    object Loading : BusinessDetailsResult()
    data class Error(val throwable: Throwable?) : BusinessDetailsResult()
}

//sealed class StateView {
//    data class Success<out T>(val data: T) : StateView()
//
//    data class ErrorMessage(val message: String?) : StateView()
//    data class Error(val message: Throwable?) : StateView()
//
//    object Loading : StateView()
//
//    data class PaginationSuccess<out T>(val data: T) : StateView()
//    data class PaginationError(val error: Throwable?) : StateView()
//    object Empty : StateView()
//    object PaginationLoading : StateView()
//    object PaginationFinished : StateView()
//
//}