package com.way.recipe.base

import android.R
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * Created by Otway on 2021/4/6.
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 默认都有返回键
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        initToolbar()
    }

    private fun initToolbar() {
        val toolbar: Toolbar? = findToolBar()
        toolbar?.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    open fun findToolBar(): Toolbar? {
        return null
    }

    fun showToast(msg: String?) {
        if (TextUtils.isEmpty(msg)) {
            return
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}