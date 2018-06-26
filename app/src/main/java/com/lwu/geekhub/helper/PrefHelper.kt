package com.lwu.geekhub.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by lwu on 6/22/18.
 */
const val TOKEN = "TOKEN"
// TODO: To be converted to database model
const val USERNAME = "USERNAME"


fun Context.getSharedPref(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)