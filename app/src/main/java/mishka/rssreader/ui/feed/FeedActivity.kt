package mishka.rssreader.ui.feed;

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import mishka.rssreader.R


import javax.inject.Inject

import mishka.rssreader.data.model.RssItem
import mishka.rssreader.di.component.DaggerFeedComponent
import mishka.rssreader.ui.BaseActivity

class FeedActivity: BaseActivity() {

    @Inject lateinit var adapter: FeedAdapter

    @Inject lateinit var viewModel : FeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setSupportActionBar(toolbar)

        val feedComponent = DaggerFeedComponent.builder().applicationComponent(getApplicationComponent()).build()

        feedComponent.inject(this)

        rvFeed.setLayoutManager(LinearLayoutManager(this))

        rvFeed.setAdapter(adapter)

        viewModel.posts.observe(this, object: Observer<List<RssItem>>{
            override fun onChanged(posts: List<RssItem>?) {
                adapter.posts = posts!!
            }
        })

        refresh.setOnRefreshListener(object: SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                viewModel.update()
                refresh.isRefreshing = false
            }
        })
    }
}
