package mishka.rssreader.ui.post;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import mishka.rssreader.data.DataManager;
import mishka.rssreader.data.model.RssItem;

public class PostViewModel extends ViewModel {
    private LiveData<RssItem> post;

    public PostViewModel(DataManager dataManager, int postId) {
        this.post = dataManager.getPostById(postId);
    }

    public LiveData<RssItem> getPost() {
        return post;
    }
}
