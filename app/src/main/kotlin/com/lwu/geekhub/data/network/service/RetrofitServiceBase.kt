package com.lwu.geekhub.data.network.service

import com.lwu.geekhub.BuildConfig
import com.lwu.geekhub.data.network.service.converters.ResponseConverter
import com.lwu.geekhub.data.network.service.interceptors.AuthenticationInterceptor
import com.lwu.geekhub.data.network.service.interceptors.RESTInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by lwu on 6/18/18.
 */
abstract class RetrofitServiceBase<T>(
    private val token: String?,
    private val serviceClass: Class<T>
) {
    protected open val baseUrl = "https://api.github.com"

    protected val service: T by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient.Builder().apply {
                addInterceptor(AuthenticationInterceptor(token))
                addInterceptor(RESTInterceptor())
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                }
            }.build())
            .addConverterFactory(ResponseConverter())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(serviceClass)
    }
}