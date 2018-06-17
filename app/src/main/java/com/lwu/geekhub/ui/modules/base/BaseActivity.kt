package com.lwu.geekhub.ui.modules.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lwu.geekhub.ui.modules.login.LoginActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by lwu on 6/16/18.
 */
abstract class BaseActivity: AppCompatActivity() {

    private lateinit var compositeDisposable: CompositeDisposable

    abstract val contentViewResource: Int
    open val loginRequired: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewResource)
        compositeDisposable = CompositeDisposable()

        if (loginRequired) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}