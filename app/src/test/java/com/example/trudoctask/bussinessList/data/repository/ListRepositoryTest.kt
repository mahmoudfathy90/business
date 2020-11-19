package com.example.trudoctask.bussinessList.data.repository

import com.example.trudoctask.bussinessList.BusinessListDummyData
import com.example.trudoctask.bussinessList.data.service.ListApiService
import com.example.trudoctask.bussinessList.data.service.reguest.ListRequestModel
import com.example.trudoctask.bussinessList.data.service.response.BusinessListModel
import com.example.trudoctask.utils.Constants
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
 class ListRepositoryTest {


    @Mock
    lateinit var listApiService: ListApiService

    lateinit var listRepository: ListRepository
    val businessRequest = ListRequestModel(Constants.DEFALUT_CITY,30,0,null)
    val businessListModel = Gson().fromJson(BusinessListDummyData, BusinessListModel::class.java)


    @Before
    fun before(){
        listRepository= ListRepository(listApiService)
    }

    @Test
    fun testValidateCode() = runBlockingTest{
        listRepository.getAllBusiness(businessRequest)
        verify(listApiService).getAllBusinesses(businessRequest.location,businessRequest.limit,businessRequest.offset,businessRequest.term)
        verifyNoMoreInteractions(listApiService)
    }



    @Test
    fun testData() = runBlockingTest{
        whenever(listApiService.getAllBusinesses(businessRequest.location,businessRequest.limit,businessRequest.offset,businessRequest.term)).thenReturn(businessListModel)
        val result = listRepository.getAllBusiness(businessRequest)
        assert(result == businessListModel)
    }
}