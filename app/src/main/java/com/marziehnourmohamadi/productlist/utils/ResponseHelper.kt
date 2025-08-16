package com.marziehnourmohamadi.productlist.utils

import android.util.Log
import com.marziehnourmohamadi.productlist.data.repository.network.NetworkMonitoring
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResponseHelper @Inject constructor(
    private val networkMonitor: NetworkMonitoring
) {
    suspend fun <T> parseApiResult(apiCall: suspend () -> Response<T>): Result<T> {
        return try {
            if (!networkMonitor.isConnected()) {
                return Result.failure(Exception("No internet connection"))
            }
            val resp = apiCall()
            if (resp.isSuccessful) {
                val body = resp.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("The body is empty"))
                }
            } else {
                Log.e("parseApi", "HTTP ${resp.code()}", )
                Result.failure(Exception("Error has occurred"))
            }
        } catch (e: Exception) {
            Log.e("parseApi", "try-catch $e", )
            Result.failure(Exception("Error has occurred"))
        }
    }
}