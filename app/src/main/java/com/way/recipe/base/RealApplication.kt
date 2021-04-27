package com.way.recipe.base

import android.app.Application
import cn.leancloud.AVLogger
import cn.leancloud.AVOSCloud

/**
 * Created by Otway on 2021/4/6.
 */
class RealApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AVOSCloud.setLogLevel(AVLogger.Level.DEBUG);
        AVOSCloud.initialize(
            this,
            "4iY0KS3rs1RwzX7RgWph7qU8-9Nh9j0Va",
            "E55esrn0CmYuCtWceqVbH4sO",
            "https://4iy0ks3r.lc-cn-e1-shared.com"
        )
    }
}