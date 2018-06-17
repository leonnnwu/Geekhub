package com.lwu.geekhub.ui.modules.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lwu.geekhub.R
import com.lwu.geekhub.helper.getText
import com.lwu.geekhub.ui.modules.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override val contentViewResource: Int = R.layout.activity_login
    override val loginRequired: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        login_btn.setOnClickListener {
            if (login_progress.visibility == View.GONE) {
                username.error = if (username.getText().isEmpty()) getString(R.string.empty_error) else null
                password.error = if (password.getText().isEmpty()) getString(R.string.empty_error) else null

                if (username.getText().isNotEmpty() && password.getText().isNotEmpty()) {
                    Toast.makeText(this, "${username.getText()} + ${password.getText()}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
