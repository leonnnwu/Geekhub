package com.lwu.geekhub.data.network.model

/**
 * Created by lwu on 6/18/18.
 */
data class AccessToken(
    val id: Int,
    val token: String,
    val hashed_token: String,
    val token_type: String
)