package me.mrizkip.moviecatalogue.ui.reminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.mrizkip.moviecatalogue.R

class ReminderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        supportActionBar?.title = "Reminder"


    }
}
