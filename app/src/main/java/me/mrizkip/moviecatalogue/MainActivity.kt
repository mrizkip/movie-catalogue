package me.mrizkip.moviecatalogue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import me.mrizkip.moviecatalogue.movie.MovieFragment
import me.mrizkip.moviecatalogue.tvShow.TvShowFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_toolbar)
        supportActionBar?.apply {
            title = "Movie Catalogue"
        }

        setupViewPager(main_viewPager)
        main_tabLayout.setupWithViewPager(main_viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val viewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)
        val movieFragment = MovieFragment()
        val tvShowFragment = TvShowFragment()

        viewPagerAdapter.addFragment(movieFragment)
        viewPagerAdapter.addFragment(tvShowFragment)

        viewPager?.adapter = viewPagerAdapter
        viewPager?.offscreenPageLimit = viewPagerAdapter.count - 1
    }
}
