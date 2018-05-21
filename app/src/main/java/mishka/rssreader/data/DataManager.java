package mishka.rssreader.data;

import java.util.List;

public interface DataManager {
    interface PostFetchedListener {
        void onPostsFetched(List<Post> posts);
    }
    void getAllPosts(PostFetchedListener postFetchedListener);
}
