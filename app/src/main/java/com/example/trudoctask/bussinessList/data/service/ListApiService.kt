package com.example.trudoctask.bussinessList.data.service

import com.example.trudoctask.bussinessList.data.service.response.BusinessListModel

import retrofit2.http.GET
import retrofit2.http.Query

interface ListApiService {

    /**
     * function used get the list of business from backend
     * @param location [String]  geographic area to be used when searching for businesses
     * @param limit [Int]  number of items for list to use by paging
     * @param offset [Int]  Offset the list of returned business results by this amount.
     */

    @GET("businesses/search")
    suspend fun getAllBusinesses(
        @Query("location") location: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("term") term: String?
    ): BusinessListModel

}