package mishka.rssreader.ui.feed;

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel


import javax.inject.Inject

import mishka.rssreader.data.DataManager
import mishka.rssreader.data.model.RssItem

class FeedViewModel @Inject constructor (val dataManager: DataManager): ViewModel() {
    val posts: LiveData<List<RssItem>> = dataManager.getPosts()

    fun update() = dataManager.update()
}
