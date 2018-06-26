package com.lwu.geekhub.ui.modules.main

import android.os.Bundle
import com.lwu.geekhub.R
import com.lwu.geekhub.helper.USERNAME
import com.lwu.geekhub.helper.getSharedPref
import com.lwu.geekhub.ui.base.NavActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : NavActivity() {

    override val contentViewResource = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        message.text = getString(R.string.title_welcome, getSharedPref().getString(USERNAME, ""))
    }
}
