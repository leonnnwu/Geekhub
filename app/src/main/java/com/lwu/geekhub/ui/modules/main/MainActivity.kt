package com.lwu.geekhub.ui.modules.main

import android.os.Bundle
import com.lwu.geekhub.R
import com.lwu.geekhub.data.persistance.AppDatabase
import com.lwu.geekhub.ui.base.NavActivity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : NavActivity() {

    override val contentViewResource = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppDatabase
            .getInstance(this)
            .userDao()
            .getCurrentUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { user ->
                message.text = getString(R.string.title_welcome, user.login)
            }
    }
}
