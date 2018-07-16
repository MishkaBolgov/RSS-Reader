package mishka.rssreader.data

import android.arch.lifecycle.LiveData

import mishka.rssreader.data.model.RssItem

interface DataManager {
    fun update()
    fun getPostById(postId: Int): LiveData<RssItem>
    fun getPosts(): LiveData<List<RssItem>>
}
