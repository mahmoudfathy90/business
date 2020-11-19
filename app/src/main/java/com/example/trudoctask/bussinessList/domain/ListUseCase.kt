package com.example.trudoctask.bussinessList.domain


import com.example.trudoctask.businessDetails.data.service.response.BusinessModel
import com.example.trudoctask.bussinessList.data.service.reguest.ListRequestModel
import java.lang.Exception

import javax.inject.Inject

class ListUseCase @Inject constructor(private val repository: IListRepository)
{
    /**
     * function used execute the all businesses from repository
     * @param requestModel [ListRequestModel]  model that contains parms
     * @return [BusinessListResult] sealed class that indicated success or loading or error or empty
     */
    suspend fun execute(requestModel: ListRequestModel): BusinessListResult {
        return try {
            val model = repository.getAllBusiness(requestModel)
            if (model.businesses?.isEmpty()!!){
                BusinessListResult.Empty
            } else{
                BusinessListResult.Success(model.businesses as List<BusinessModel>)
            }
        } catch (ex :Exception){
            BusinessListResult.Error(ex)
        }
    }
}