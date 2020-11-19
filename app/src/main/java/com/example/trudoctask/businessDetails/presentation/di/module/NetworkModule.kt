package com.example.trudoctask.businessDetails.presentation.di.module

import android.content.Context
import com.example.trudoctask.businessDetails.data.service.DetailsApiService
import com.example.trudoctask.utils.Constants
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.example.trudoctask.businessDetails.presentation.di.qualifiers.ForApplication
import com.example.trudoctask.bussinessList.data.service.ListApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory


import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Reusable
    @Provides
    fun providesHttpCache(@ForApplication appContext: Context): Cache? {
        val cacheSize = 10 * 1024 * 1024
        var cache: Cache? = null
        try {
            val myDir = File(appContext.cacheDir, "response")
            myDir.mkdir()
            cache = Cache(myDir, cacheSize.toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return cache
    }

    @Reusable
    @Provides
    fun providesGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .serializeNulls()
        return gsonBuilder.create()
    }

    @Reusable
    @Provides
    fun provideOkhttpClient(cache: Cache?,@ForApplication appContext: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
             .cache(cache)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor (RequestInterceptor())
            .build()
    }

    class RequestInterceptor :Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val request: Request =
                chain.request().newBuilder()
                    .addHeader("Authorization", Constants.TOKEN)
                    .build()
            request.newBuilder()
            return chain.proceed(request)
        }
    }

    @Reusable
    @Provides
    fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }


    @Reusable
    @Provides
    fun providesApiService(retrofit: Retrofit): DetailsApiService {
        return retrofit.create(DetailsApiService::class.java)
    }

    @Reusable
    @Provides
    fun providesListApiService(retrofit: Retrofit): ListApiService {
        return retrofit.create(ListApiService::class.java)
    }
}