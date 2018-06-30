package com.lwu.geekhub.ui.modules.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lwu.geekhub.R
import com.lwu.geekhub.data.model.User
import com.lwu.geekhub.ui.base.NavActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : NavActivity() {

    override val contentViewResource = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        userViewModel.loadCurrentUser()
        userViewModel.currentUser.observe(this, Observer<User> { user ->
            if (user != null) {
                message.text = getString(R.string.title_welcome, user.login)
            }
        })
    }
}
