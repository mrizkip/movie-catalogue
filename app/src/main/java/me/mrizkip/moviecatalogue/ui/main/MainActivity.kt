package me.mrizkip.moviecatalogue.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import me.mrizkip.moviecatalogue.R
import me.mrizkip.moviecatalogue.ui.favorite.FavoriteFragment
import me.mrizkip.moviecatalogue.ui.movie.MovieFragment
import me.mrizkip.moviecatalogue.ui.tvShow.TvShowFragment

class MainActivity : AppCompatActivity() {

    private var selectedItem: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_toolbar)
        supportActionBar?.title = "Movie Catalogue"

        main_bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomMenu_movies -> loadFragment(MovieFragment())
                R.id.bottomMenu_tvShows -> loadFragment(TvShowFragment())
                R.id.bottomMenu_favorites -> loadFragment(FavoriteFragment())
            }
            true
        }
        if (savedInstanceState != null) {
            main_bottomNavigation.selectedItemId = selectedItem
        } else {
            main_bottomNavigation.selectedItemId = R.id.bottomMenu_movies
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_content, fragment)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        selectedItem = main_bottomNavigation.selectedItemId
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
