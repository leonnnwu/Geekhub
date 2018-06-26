package com.lwu.geekhub.data.network.model

/**
 * Created by lwu on 6/18/18.
 */
data class AccessToken(
    val access_token: String,
    val token_type: String,
    val scope: String
)