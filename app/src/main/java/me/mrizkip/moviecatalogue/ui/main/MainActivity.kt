package me.mrizkip.moviecatalogue.ui.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.model.FavoriteTvShow
import me.mrizkip.moviecatalogue.ui.favorite.FavoriteFragment
import me.mrizkip.moviecatalogue.ui.movie.MovieFragment
import me.mrizkip.moviecatalogue.ui.tvShow.TvShowFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_toolbar)
        supportActionBar?.title = "Movie Catalogue"

        main_bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.bottomMenu_movies -> loadFragment(MovieFragment(), savedInstanceState)
                R.id.bottomMenu_tvShows -> loadFragment(TvShowFragment(), savedInstanceState)
                R.id.bottomMenu_favorites -> loadFragment(FavoriteFragment(), savedInstanceState)
            }
            true
        }
        main_bottomNavigation.selectedItemId = R.id.bottomMenu_movies
    }

    private fun loadFragment(fragment: Fragment, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_content, fragment)
                .commit()
        }
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val viewPagerAdapter =
            MainViewPagerAdapter(this, supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        val movieFragment = MovieFragment()
        val tvShowFragment = TvShowFragment()

        viewPagerAdapter.addFragment(movieFragment)
        viewPagerAdapter.addFragment(tvShowFragment)

        viewPager?.adapter = viewPagerAdapter
        viewPager?.offscreenPageLimit = viewPagerAdapter.count - 1
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuMain_language) {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
