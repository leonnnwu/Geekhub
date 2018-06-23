package com.lwu.geekhub.helper

import android.net.Uri
import com.lwu.geekhub.BuildConfig

/**
 * Created by lwu on 6/16/18.
 */

val authorizationUrl: Uri = Uri.Builder().scheme("https")
    .authority("github.com")
    .appendPath("login")
    .appendPath("oauth")
    .appendPath("authorize")
    .appendQueryParameter("client_id", BuildConfig.GITHUB_CLIENT_ID)
    .appendQueryParameter("redirect_uri", BuildConfig.REDIRECT_URL)
    .appendQueryParameter("scope", "user,repo,gist,notifications,read:org")
    .appendQueryParameter("state", BuildConfig.APPLICATION_ID)
    .build()