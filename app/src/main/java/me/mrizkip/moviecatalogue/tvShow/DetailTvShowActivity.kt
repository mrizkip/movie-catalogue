package me.mrizkip.moviecatalogue.tvShow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.mrizkip.moviecatalogue.R

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW = "EXTRA_TV_SHOW"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
    }
}
