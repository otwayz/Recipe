package com.way.recipe.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.way.recipe.R
import com.way.recipe.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity() {
    private lateinit var homePagerAdapter: HomePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        homePagerAdapter = HomePagerAdapter(this)
        home_vp.adapter = homePagerAdapter
        TabLayoutMediator(
            home_tab_layout,
            home_vp,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = homePagerAdapter.getPageTitle(position)
            }).attach()

        home_floating.setOnClickListener {

        }
    }


    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}