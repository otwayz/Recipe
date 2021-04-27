package com.way.recipe.base

import android.os.Bundle
import cn.leancloud.AVUser
import com.way.recipe.common.SignInActivity
import com.way.recipe.main.HomeActivity

/**
 * Created by Otway on 2021/4/6.
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentUser = AVUser.getCurrentUser()
        if (currentUser == null) {
            startActivity(SignInActivity.createIntent(this))
        } else {
            startActivity(HomeActivity.createIntent(this))
        }
        finish()
    }
}