package mishka.rssreader.ui.feed;

import java.util.List;

import mishka.rssreader.data.Post;
import mishka.rssreader.ui.MvpView;

public interface FeedMvpView extends MvpView {
    void updateFeed(List<Post> posts);
}
