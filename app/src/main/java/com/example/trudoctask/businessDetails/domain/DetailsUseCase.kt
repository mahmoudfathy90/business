package com.example.trudoctask.businessDetails.domain


import java.lang.Exception

import javax.inject.Inject

class DetailsUseCase @Inject constructor(private val repository: IDetailsRepository)
{

    /**
     * function used execute the details of business from repository
     * @param params [String]  id of business  to get it's details
     */
    suspend fun execute(params: String?): BusinessDetailsResult {
        return try {
            val model = repository.getDetails(params)
             BusinessDetailsResult.Success(model)
          }
        catch (ex :Exception){
            BusinessDetailsResult.Error(ex)
        }
    }
}