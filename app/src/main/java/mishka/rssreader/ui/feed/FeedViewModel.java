package mishka.rssreader.ui.feed;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import mishka.rssreader.data.DataManager;
import mishka.rssreader.data.model.RssItem;

public class FeedViewModel extends ViewModel {
    private final LiveData<List<RssItem>> posts;
    private DataManager dataManager;

    @Inject
    public FeedViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
        this.posts = dataManager.getPosts();
    }

    public LiveData<List<RssItem>> getPosts() {
        return posts;
    }

    public void update() {
        dataManager.update();
    }
}
