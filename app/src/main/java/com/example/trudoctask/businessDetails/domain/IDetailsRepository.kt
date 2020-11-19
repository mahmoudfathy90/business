package com.example.trudoctask.businessDetails.domain

import com.example.trudoctask.businessDetails.data.service.response.BusinessModel


interface IDetailsRepository {
     /**
      * function used to get  the details of business from api service
      * @param id [String]  id of business  to get it's details
      */
     suspend fun getDetails(id: String?): BusinessModel

}