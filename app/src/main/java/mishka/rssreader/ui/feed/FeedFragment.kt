package mishka.rssreader.ui.feed


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.fragment_feed.view.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*

import mishka.rssreader.R
import mishka.rssreader.data.model.RssItem
import mishka.rssreader.di.component.DaggerFeedComponent
import mishka.rssreader.di.module.FeedModule
import mishka.rssreader.ui.MainActivity
import javax.inject.Inject

class FeedFragment : Fragment(), OnFeedItemClickListener {
    @Inject
    lateinit var adapter: FeedAdapter

    @Inject
    lateinit var viewModel: FeedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_feed, container, false)

        val component = DaggerFeedComponent.builder().feedModule(FeedModule(this)).applicationComponent((activity as MainActivity).getApplicationComponent()).build()

        component.inject(this)

        view.rvFeed.layoutManager = LinearLayoutManager(context)

        view.rvFeed.adapter = adapter

        viewModel.posts.observe(this, object : Observer<List<RssItem>> {
            override fun onChanged(posts: List<RssItem>?) {
                adapter.posts = posts!!
            }
        })

        view.refresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                viewModel.update()
                refresh.isRefreshing = false
            }
        })

        (activity)?.invalidateOptionsMenu()

        hideSettings()
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.feed_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onItemClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt("postId", id)
        findNavController().navigate(R.id.postFragment, bundle)
    }

    fun notifyCurrentChannelChanged() {
        viewModel.update()
        hideSettings()
    }

    var isSettingsOpened = false

    fun onChannelSettingsClick() {
        if (isSettingsOpened) hideSettings() else showSettings()
    }

    private fun showSettings() {
        childFragmentManager.findFragmentById(R.id.channelSettings).view?.visibility = View.VISIBLE
        isSettingsOpened = true
    }

    private fun hideSettings() {
        childFragmentManager.findFragmentById(R.id.channelSettings).view?.visibility = View.GONE
        isSettingsOpened = false
    }

}
