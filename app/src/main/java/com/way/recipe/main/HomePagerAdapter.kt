package com.way.recipe.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by Otway on 2021/4/8.
 */
class HomePagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    companion object {
        private const val HOME_PAGE_SIZE = 2
        private val PAGE_TITLE = arrayOf("商品", "配方")
    }

    override fun getItemCount(): Int {
        return HOME_PAGE_SIZE
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> IngredientFragment.newInstance()
            1 -> RecipeFragment.newInstance()
            else -> throw IllegalArgumentException("unknown home vp position:$position")
        }
    }

    fun getPageTitle(position: Int): CharSequence {
        return PAGE_TITLE[position]
    }
}