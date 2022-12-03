package com.diegot.services.api

import retrofit2.Response
import java.net.HttpURLConnection

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T?> {

            // Error = false, Success = true
            val isValid = if (response.isSuccessful) {
                when (response.code()) {
                    HttpURLConnection.HTTP_OK -> true
                    else -> false
                }
            } else {
                false
            }

            return if (isValid) {
                val body = response.body()
                ApiSuccessResponse(response.code(), response.message(), body)
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(response.code(), errorMsg)
            }
        }
    }
}

data class ApiSuccessResponse<T>(val code: Int, val message: String?, val body: T) : ApiResponse<T>()
data class ApiErrorResponse<T>(val code: Int, val message: String?) : ApiResponse<T>()