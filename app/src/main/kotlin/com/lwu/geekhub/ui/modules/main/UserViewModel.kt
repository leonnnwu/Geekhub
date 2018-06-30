package com.lwu.geekhub.ui.modules.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.lwu.geekhub.data.model.User
import com.lwu.geekhub.data.persistance.AppDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    val currentUser = MutableLiveData<User>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun loadCurrentUser() {
        compositeDisposable.add(
            AppDatabase
                .getInstance(getApplication())
                .userDao()
                .getCurrentUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { user ->
                    currentUser.value = user
                }
        )
    }
}