package com.example.trudoctask.bussinessList.data.service.reguest


data class ListRequestModel(
    var location: String?,
    var limit: Int? ,
    var offset: Int?,
    var term: String?=null
)

