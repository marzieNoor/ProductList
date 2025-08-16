package com.marziehnourmohamadi.productlist.data.repository.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkMonitoring @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun isConnected(): Boolean = withContext(Dispatchers.IO) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return@withContext false
        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return@withContext false
        return@withContext capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}