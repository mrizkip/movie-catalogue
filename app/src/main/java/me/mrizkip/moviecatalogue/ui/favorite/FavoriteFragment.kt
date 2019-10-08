package me.mrizkip.moviecatalogue.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*
import me.mrizkip.moviecatalogue.R

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
        favorite_tabLayout.setupWithViewPager(favorite_viewPager)
    }

    private fun setupViewPager() {
        val adapter = FavoritePagerAdapter(
            context!!,
            childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        val movieFragment = FavoriteMovieFragment()
        val tvShowFragment = FavoriteTvShowFragment()

        adapter.addFragment(movieFragment)
        adapter.addFragment(tvShowFragment)

        favorite_viewPager.adapter = adapter
    }
}
