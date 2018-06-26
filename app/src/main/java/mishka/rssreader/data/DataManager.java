package mishka.rssreader.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface DataManager {
    void update();

    LiveData<Post> getPostById(int postId);

    LiveData<List<Post>> getPosts();

    interface PostFetchedListener {
        void onPostsFetched(List<Post> posts);
    }
}
