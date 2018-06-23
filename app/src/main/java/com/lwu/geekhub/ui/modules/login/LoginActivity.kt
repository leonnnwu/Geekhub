package com.lwu.geekhub.ui.modules.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lwu.geekhub.BuildConfig
import com.lwu.geekhub.R
import com.lwu.geekhub.data.network.service.LoginService
import com.lwu.geekhub.helper.*
import com.lwu.geekhub.ui.base.BaseActivity
import com.lwu.geekhub.ui.modules.main.MainActivity
import com.lwu.geekhub.ui.startCustomTab
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override val contentViewResource: Int = R.layout.activity_login
    override val loginRequired: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signin_btn.setOnClickListener {
            startCustomTab(authorizationUrl)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        onHandleAuthIntent(intent)
        setIntent(null)
    }

    override fun onResume() {
        super.onResume()
        onHandleAuthIntent(intent)
        setIntent(null)
    }

    private fun onHandleAuthIntent(intent: Intent?) {
        if (intent != null && intent.data != null && intent.data.toString().startsWith(BuildConfig.REDIRECT_URL)) {
            val tokenCode = intent.data.getQueryParameter("code")?.takeIf { it.isNotEmpty() }
            tokenCode?.let {
                LoginService().getAccessToken(it,
                                              BuildConfig.GITHUB_CLIENT_ID,
                                              BuildConfig.GITHUB_CLIENT_SECRET,
                                              BuildConfig.APPLICATION_ID,
                                              BuildConfig.REDIRECT_URL)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe (
                        {  response ->
                            //TODO: Error handling
                            getSharedPref().edit().putString(TOKEN, response.body()?.token).apply()
                            startActivity(Intent(this, MainActivity::class.java).apply {
                                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            })
                            finish()
                        }
                    )
            }
        }
    }
}
