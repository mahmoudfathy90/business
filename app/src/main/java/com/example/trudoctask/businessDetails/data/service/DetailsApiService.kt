package com.example.trudoctask.businessDetails.data.service

import com.example.trudoctask.businessDetails.data.service.response.BusinessModel

import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApiService {

    /**
     * function used get the details of business from backend
     * @param id [String]  id of business  to get it's details
     */
    @GET("businesses/{id}")
     suspend fun getDetails(@Path("id") id:String?):BusinessModel


}