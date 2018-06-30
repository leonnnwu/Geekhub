package com.lwu.geekhub.ui.modules.login

import android.content.Intent
import android.os.Bundle
import com.lwu.geekhub.BuildConfig
import com.lwu.geekhub.R
import com.lwu.geekhub.data.model.AccessToken
import com.lwu.geekhub.data.model.User
import com.lwu.geekhub.data.network.service.LoginService
import com.lwu.geekhub.data.network.service.UserService
import com.lwu.geekhub.data.persistance.AppDatabase
import com.lwu.geekhub.helper.*
import com.lwu.geekhub.ui.base.BaseActivity
import com.lwu.geekhub.ui.modules.main.MainActivity
import com.lwu.geekhub.ui.startCustomTab
import io.reactivex.Completable
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
            val tokenCode =
                intent.data.getQueryParameter("code")
                    ?.takeIf { it.isNotEmpty() }
            tokenCode?.let {
                LoginService().getAccessToken(
                    it,
                    BuildConfig.GITHUB_CLIENT_ID,
                    BuildConfig.GITHUB_CLIENT_SECRET,
                    BuildConfig.APPLICATION_ID,
                    BuildConfig.REDIRECT_URL
                )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onTokenResponse)
            }
        }
    }

    private fun onTokenResponse(accessToken: AccessToken) {
        getSharedPref().edit()
            .putString(TOKEN, accessToken.access_token)
            .commit()
        UserService(accessToken.access_token).getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::saveUserToDb)
    }

    private fun saveUserToDb(user: User) {
        val userDao = AppDatabase.getInstance(this).userDao()

        userDao.getCurrentUser()
            .flatMapCompletable { currentUser ->
                currentUser.isLoggedIn = false
                Completable.fromAction { userDao.updateUser(currentUser) }
            }
            .doFinally {
                user.isLoggedIn = true
                userDao.insertUser(user)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                startActivity(Intent(this, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                })
                finish()
            }
            .subscribe()
    }
}
