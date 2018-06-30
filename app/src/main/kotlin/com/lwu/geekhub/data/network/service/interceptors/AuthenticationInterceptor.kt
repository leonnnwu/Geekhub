package com.lwu.geekhub.data.network.service.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by lwu on 6/22/18.
 */
class AuthenticationInterceptor(private val token: String?): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
        token?.let {
            request.addHeader("Authorization", "token $token")
        }
        return chain.proceed(request.build())
    }

}