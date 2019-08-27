package me.mrizkip.moviecatalogue.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import me.mrizkip.moviecatalogue.R

class MainViewPagerAdapter(context: Context, fragmentManager: FragmentManager?): FragmentPagerAdapter(fragmentManager) {
    private val fragments = ArrayList<Fragment>()
    private val tabTitles = arrayOf(context.getString(R.string.tab_movies), context.getString(
        R.string.tab_tv_shows
    ))

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return tabTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }
}