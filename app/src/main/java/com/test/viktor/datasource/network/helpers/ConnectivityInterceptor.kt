package com.test.viktor.datasource.network.helpers

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (ConnectivityUtils.isConnected(context))
            return chain.proceed(chain.request())
        else
            throw NoConnectivityException("No Network")
    }
}