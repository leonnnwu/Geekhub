package com.lwu.geekhub.ui.base

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.lwu.geekhub.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by lwu on 6/16/18.
 */
abstract class NavActivity: BaseActivity() {
    val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}