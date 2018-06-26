package mishka.rssreader.ui.post;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import mishka.rssreader.data.DataManager;
import mishka.rssreader.data.Post;

public class PostViewModel extends ViewModel {
    private LiveData<Post> post;

    public PostViewModel(DataManager dataManager, int postId) {
        this.post = dataManager.getPostById(postId);
    }

    public LiveData<Post> getPost() {
        return post;
    }
}
