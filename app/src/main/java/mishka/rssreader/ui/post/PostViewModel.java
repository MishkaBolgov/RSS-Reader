package mishka.rssreader.ui.post;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import mishka.rssreader.data.DataManager;
import mishka.rssreader.data.model.RealmRssItem;


public class PostViewModel extends ViewModel {
    private RealmRssItem post;

    public PostViewModel(DataManager dataManager, int postId) {
        this.post = dataManager.getRssItemById(postId);
    }

    public RealmRssItem getPost() {
        return post;
    }
}
