package com.lwu.geekhub.data.model

/**
 * Created by lwu on 6/18/18.
 */
data class AuthMode(
    val scopes: List<String>,
    val note: String,
    val note_url: String,
    val client_id: String,
    val client_secret: String,
    val fingerprint: String
)