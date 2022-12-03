package com.diegot.services.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

class ApiClient {

    private val baseUrl = "http://st.fletx.co:3000/"
    private val token = "ab11cb7605a030ee350d08f805057413"

    private val retrofitClient: Retrofit by lazy { initRetrofit() }

    private fun initRetrofit(): Retrofit {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        logger.level = HttpLoggingInterceptor.Level.HEADERS

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(object: Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "Bearer $token")
                    return chain.proceed(request.build())
                }
            }).build()

        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getHolderVehicleService() = retrofitClient.create(VehicleService::class.java)
}