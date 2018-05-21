package mishka.rssreader.data.rss;

import java.util.List;

import mishka.rssreader.data.Post;

public interface RssHelper {
    List<Post> fetchPosts();
}
