package mishka.rssreader.data.rss;

import java.util.List;

import javax.inject.Inject;

import mishka.rssreader.data.Post;

public class SimpleRssHelper implements RssHelper {
    @Inject
    public SimpleRssHelper() {
    }

    @Override
    public List<Post> fetchPosts() {
        return PostFetcher.fetch();
    }
}
