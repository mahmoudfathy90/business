package com.example.trudoctask.bussinessList.domain

import com.example.trudoctask.bussinessList.data.service.reguest.ListRequestModel
import com.example.trudoctask.bussinessList.data.service.response.BusinessListModel


interface IListRepository {
     /**
      * function used to get  the list of business from api service
      * @param requestModel [ListRequestModel]  model  that contains all param to use it  @Query
      * in ListApiService
      */
     suspend fun getAllBusiness(requestModel: ListRequestModel): BusinessListModel

}