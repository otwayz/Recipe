package com.way.recipe.base.api

import cn.leancloud.AVUser
import com.way.recipe.utils.AccountUtils
import io.reactivex.Observable

/**
 * Created by Otway on 2021/4/6.
 */
class ApiService private constructor() {
    companion object {
        val instance = Holder.holder
    }

    private object Holder {
        val holder = ApiService()
    }


    // 登录
    fun signIn(account: String, password: String): Observable<out AVUser> {
        return when {
            AccountUtils.isMobile(account) -> {
                AVUser.loginByMobilePhoneNumber(account, password)
            }
            AccountUtils.isEmail(account) -> {
                AVUser.loginByEmail(account, password)
            }
            else -> {
                AVUser.logIn(account, password)
            }
        }
    }

    // 注册
    fun signUp(account: String, password: String): Observable<AVUser> {
        val user = AVUser()
        user.username = account
        user.password = password
        if (AccountUtils.isMobile(account)) {
            user.mobilePhoneNumber = account
        } else if (AccountUtils.isEmail(account)) {
            user.email = account
        }
        return user.signUpInBackground()
    }
}