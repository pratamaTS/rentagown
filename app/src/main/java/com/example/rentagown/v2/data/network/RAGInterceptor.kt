package com.example.rentagown.v2.data.network

import com.example.rentagown.Connection.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class RAGInterceptor(private val sessionManager: SessionManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader("Accept-Encoding", "gzip, deflate, br")
        requestBuilder.addHeader("Content-Type", "application/json")
        requestBuilder.addHeader("Host", "apps.rentagown.id")

        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }

}