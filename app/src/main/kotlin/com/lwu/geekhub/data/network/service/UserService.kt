package com.lwu.geekhub.data.network.service

import com.lwu.geekhub.data.model.User
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by lwu on 6/25/18.
 */
interface UserServiceApi {

    @GET("user")
    fun getUser(): Single<User>
}

class UserService(token: String? = null): RetrofitServiceBase<UserServiceApi>(
    token = token,
    serviceClass = UserServiceApi::class.java
) {
    fun getUser(): Single<User> = service.getUser()
}