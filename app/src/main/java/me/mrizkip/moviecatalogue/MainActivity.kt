package me.mrizkip.moviecatalogue

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
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
        supportActionBar?.title = "Movie Catalogue"

        setupViewPager(main_viewPager)
        main_tabLayout.setupWithViewPager(main_viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val viewPagerAdapter = MainViewPagerAdapter(this, supportFragmentManager)
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menuMain_language) {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
