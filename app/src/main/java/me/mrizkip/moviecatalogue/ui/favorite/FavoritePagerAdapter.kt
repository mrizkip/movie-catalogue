package me.mrizkip.moviecatalogue.ui.favorite

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import me.mrizkip.moviecatalogue.R

class FavoritePagerAdapter(context: Context, fragmentManager: FragmentManager, behaviour: Int): FragmentPagerAdapter(fragmentManager, behaviour) {
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