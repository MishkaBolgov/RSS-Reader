package mishka.rssreader.ui.feed;

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import mishka.rssreader.R


import javax.inject.Inject

import mishka.rssreader.data.model.RssItem
import mishka.rssreader.di.component.DaggerFeedComponent
import mishka.rssreader.di.component.FeedComponent
import mishka.rssreader.ui.BaseActivity

class FeedActivity : BaseActivity() {

    @Inject
    lateinit var adapter: FeedAdapter

    @Inject
    lateinit var viewModel: FeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val component = DaggerFeedComponent.builder().applicationComponent(getApplicationComponent()).build()

        component.inject(this)

        rvFeed.layoutManager = LinearLayoutManager(this)

        rvFeed.adapter = adapter

        viewModel.posts.observe(this, object : Observer<List<RssItem>> {
            override fun onChanged(posts: List<RssItem>?) {
                adapter.posts = posts!!
            }
        })

        refresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                viewModel.update()
                refresh.isRefreshing = false
            }
        })

        hideSettings()
    }

    fun notifyCurrentChannelChanged() {
        viewModel.update()
        hideSettings()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.feed_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    var isSettingsOpened = false

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.settings -> if (isSettingsOpened) hideSettings() else showSettings()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSettings() {
        channelSettings.view?.visibility = View.VISIBLE
        isSettingsOpened = true
    }

    private fun hideSettings() {
        channelSettings.view?.visibility = View.GONE
        isSettingsOpened = false
    }

}
