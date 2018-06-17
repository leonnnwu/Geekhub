package com.lwu.geekhub.ui.modules.base

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.lwu.geekhub.ui.modules.login.LoginActivity

/**
 * Created by lwu on 6/16/18.
 */
abstract class BaseActivity: AppCompatActivity() {

    abstract val contentViewResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewResource)

        startActivity(Intent(this, LoginActivity::class.java))
    }
}