package mishka.rssreader.data.db;

import java.util.List;

import mishka.rssreader.data.Post;

public interface DbHelper {
    void insertAllPosts(List<Post> posts);
    List<Post> getAllPosts();
    void erase();
}
