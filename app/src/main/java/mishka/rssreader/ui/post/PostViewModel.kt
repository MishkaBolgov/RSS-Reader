package mishka.rssreader.ui.post;

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

import mishka.rssreader.data.DataManager
import mishka.rssreader.data.model.RssItem

class PostViewModel(dataManager: DataManager, postId: Int) : ViewModel() {
    val post: LiveData<RssItem> = dataManager.getPostById(postId)

}
