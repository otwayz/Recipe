package com.way.recipe.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import cn.leancloud.AVException
import cn.leancloud.AVUser
import com.way.recipe.R
import com.way.recipe.base.BaseActivity
import com.way.recipe.base.WayObserver
import com.way.recipe.base.api.ApiService
import com.way.recipe.main.HomeActivity
import com.way.recipe.utils.MiscUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_sign_in.*

/**
 * Created by Otway on 2021/4/6.
 *  登录
 */
class SignInActivity : BaseActivity(), TextWatcher {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // 账号
        MiscUtils.addEditTextQuickDelete(
            et_account,
            ContextCompat.getDrawable(
                this,
                R.drawable.icon_et_delete
            )
        )
        et_account.addTextChangedListener(this)

        // 密码
        MiscUtils.addEditTextQuickDelete(
            et_password,
            ContextCompat.getDrawable(
                this,
                R.drawable.icon_et_delete
            )
        )
        et_password.addTextChangedListener(this)

        // 登录
        btn_sign_in.setOnClickListener {
            doSignIn(et_account.text.toString(), et_password.text.toString())
        }

        // 注册
        tv_sign_up.setOnClickListener {
            startActivity(SignUpActivity.createIntent(this))
        }
    }

    private fun doSignIn(account: String, password: String) {
        if (TextUtils.isEmpty(account)) {
            showToast("请输入用户名")
            return
        }
        if (TextUtils.isEmpty(password)) {
            showToast("请输入密码")
            return
        }

        ApiService.instance.signIn(account, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : WayObserver<AVUser>() {
                override fun onSuccess(data: AVUser) {
                    startActivity(HomeActivity.createIntent(this@SignInActivity))
                    finish()
                }

                override fun onFailure(e: AVException) {
                    showToast(if (e.code == 211) "账户或密码不正确" else e.message)
                }
            })
    }

    override fun afterTextChanged(s: Editable?) {
        // do nothing
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // do nothing
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val account: Editable = et_account.text
        val password: Editable = et_password.text
        btn_sign_in.isEnabled = account.isNotEmpty() && password.isNotEmpty()
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, SignInActivity::class.java)
        }
    }
}