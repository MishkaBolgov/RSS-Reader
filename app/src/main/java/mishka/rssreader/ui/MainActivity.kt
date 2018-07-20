package mishka.rssreader.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation.findNavController
import com.google.gson.Gson
import kotlinx.android.synthetic.main.toolbar.*
import mishka.rssreader.R
import mishka.rssreader.RssApplication
import mishka.rssreader.ui.feed.FeedFragment
import mishka.rssreader.ui.post.PostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

    }

    fun getApplicationComponent() = (application as RssApplication).applicationComponent

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.nav_host_fragment)
        when (navController.currentDestination.id) {
            R.id.feedFragment -> {
                return findNavController(this, R.id.nav_host_fragment).navigateUp()

            }
            R.id.postFragment -> {
                onBackPressed()
            }
        }

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.settings) {
            val feedFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment).childFragmentManager.fragments[0] as FeedFragment
            feedFragment.onChannelSettingsClick()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val navController = findNavController(this, R.id.nav_host_fragment)
        when (navController.currentDestination.id) {
            R.id.feedFragment -> {
                getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
                getSupportActionBar()?.setDisplayShowHomeEnabled(false)
                menuInflater.inflate(R.menu.feed_menu, menu)
            }
            R.id.postFragment -> {
                getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
                getSupportActionBar()?.setDisplayShowHomeEnabled(true)
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

}
