package com.lwu.geekhub.helper

import android.support.design.widget.TextInputLayout

/**
 * Created by lwu on 6/17/18.
 */
fun TextInputLayout.getText(): String = this.editText?.text?.toString() ?: ""