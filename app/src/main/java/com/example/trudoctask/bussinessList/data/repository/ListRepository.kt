package com.example.trudoctask.bussinessList.data.repository

import com.example.trudoctask.bussinessList.data.service.ListApiService
import com.example.trudoctask.bussinessList.data.service.reguest.ListRequestModel
import com.example.trudoctask.bussinessList.data.service.response.BusinessListModel
import com.example.trudoctask.bussinessList.domain.IListRepository
import javax.inject.Inject

class ListRepository @Inject constructor(private val apiService: ListApiService) :IListRepository
{
    override suspend fun getAllBusiness(requestModel: ListRequestModel): BusinessListModel {
        return apiService.getAllBusinesses(
            location = requestModel.location,
            limit = requestModel.limit, offset = requestModel.offset,term = requestModel.term
        )
    }

}