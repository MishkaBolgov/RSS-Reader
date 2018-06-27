package mishka.rssreader.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

import mishka.rssreader.data.model.RssItem;

public interface DataManager {
    void update();

    LiveData<RssItem> getPostById(int postId);

    LiveData<List<RssItem>> getPosts();
}
