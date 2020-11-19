package com.example.trudoctask.businessDetails.data.repository

import com.example.trudoctask.businessDetails.data.service.DetailsApiService
import com.example.trudoctask.businessDetails.data.service.response.BusinessModel
import com.example.trudoctask.businessDetails.domain.IDetailsRepository
import javax.inject.Inject

class DetailsRepository @Inject constructor(val detailsApiService: DetailsApiService) :IDetailsRepository
{
    override suspend fun getDetails(id: String?): BusinessModel {
       return detailsApiService.getDetails(id)
    }

}