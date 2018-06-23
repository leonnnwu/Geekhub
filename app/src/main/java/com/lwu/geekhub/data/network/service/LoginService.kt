package com.lwu.geekhub.data.network.service

import com.lwu.geekhub.data.network.model.AccessToken
import com.lwu.geekhub.data.network.model.AuthMode
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by lwu on 6/18/18.
 */
interface LoginServiceApi {
    @POST("authorizations")
    fun login(@Body authMode: AuthMode): Single<Response<AccessToken>>

    @FormUrlEncoded
    @POST("access_token") @Headers("Accept: application/json")
    fun getAccessToken(@Field("code") code: String,
                       @Field("client_id") clientId: String,
                       @Field("client_secret") clientSecret: String,
                       @Field("state") state: String,
                       @Field("redirect_uri") redirectUri: String): Single<Response<AccessToken>>
}

class LoginService(token: String? = null): RetrofitServiceBase<LoginServiceApi>(
    token = token,
    serviceClass = LoginServiceApi::class.java
) {
    fun getAccessToken(code: String,
                       clientId: String,
                       clientSecret: String,
                       state: String,
                       redirectUri: String): Single<Response<AccessToken>> =
        service.getAccessToken(code, clientId, clientSecret, state, redirectUri)

    override val baseUrl: String = "https://github.com/login/oauth/"
}