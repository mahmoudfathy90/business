package com.example.trudoctask.businessDetails.data.repository

import com.example.trudoctask.businessDetails.data.service.DetailsApiService
import com.example.trudoctask.businessDetails.data.service.response.BusinessModel
import com.example.trudoctask.businessDetails.domain.IDetailsRepository
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
class DetailsRepositoryTest {
    @Mock
    lateinit var detailsApiService: DetailsApiService

    lateinit var detailsRepository: IDetailsRepository
    val defaultId = "MMMMMMM"
    val bussiness = Gson().fromJson(DummyData,BusinessModel::class.java)

    @Before
    fun before() {
        detailsRepository = DetailsRepository(detailsApiService)
    }

    @Test
    fun testValidateCode() = runBlockingTest{
        detailsRepository.getDetails(defaultId)
        verify(detailsApiService).getDetails(defaultId)
        verifyNoMoreInteractions(detailsApiService)
    }

    @Test
    fun testData() = runBlockingTest{
        whenever(detailsApiService.getDetails(defaultId)).thenReturn(bussiness)
        val result = detailsRepository.getDetails(defaultId)
        assert(result == bussiness)
    }

    @Test
    fun testDataError() = runBlockingTest{
        val expectedException =  Exception()
        whenever(detailsApiService.getDetails(defaultId)).thenAnswer { throw expectedException }
        try {
            detailsRepository.getDetails(defaultId)
        }catch (e:Exception){
            assert(expectedException == e)
        }
    }
}