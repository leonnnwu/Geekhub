package com.lwu.geekhub.ui

import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent

/**
 * Created by lwu on 6/22/18.
 */
fun Context.startCustomTab(uri: Uri) {
    CustomTabsIntent.Builder().build().apply {
        //TODO: Don't hard code. Fallback to webview, if chrome is not installed.
        intent.setPackage("com.android.chrome")
    }.launchUrl(this, uri)
}