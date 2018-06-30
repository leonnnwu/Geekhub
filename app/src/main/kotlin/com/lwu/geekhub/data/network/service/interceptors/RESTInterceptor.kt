package com.lwu.geekhub.data.network.service.interceptors

import com.lwu.geekhub.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by lwu on 6/26/18.
 */
class RESTInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("User-Agent", BuildConfig.APPLICATION_ID)

        return chain.proceed(request.build())
    }

}