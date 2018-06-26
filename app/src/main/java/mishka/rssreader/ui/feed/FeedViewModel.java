package mishka.rssreader.ui.feed;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import mishka.rssreader.data.DataManager;
import mishka.rssreader.data.Post;
import mishka.rssreader.data.SimpleDataManager;

public class FeedViewModel extends ViewModel {
    private final LiveData<List<Post>> posts;
    private DataManager dataManager;

    @Inject
    public FeedViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
        this.posts = dataManager.getPosts();
    }

    public LiveData<List<Post>> getPosts() {
        return posts;
    }

    public void update() {
        dataManager.update();
    }
}
