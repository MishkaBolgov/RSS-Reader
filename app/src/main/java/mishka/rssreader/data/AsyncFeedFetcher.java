package mishka.rssreader.data;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import mishka.rssreader.data.db.DbHelper;
import mishka.rssreader.data.rss.RssHelper;

public class AsyncFeedFetcher extends AsyncTask<Boolean, Void, List<Post>> {
    private DbHelper dbHelper;
    private RssHelper rssHelper;
    private DataManager.PostFetchedListener listener;

    AsyncFeedFetcher(DbHelper dbHelper, RssHelper rssHelper, DataManager.PostFetchedListener listener) {
        this.dbHelper = dbHelper;
        this.rssHelper = rssHelper;
        this.listener = listener;
    }

    @Override
    protected List<Post> doInBackground(Boolean... booleans) {
        boolean updateFromRss = booleans[0];
        if (updateFromRss) {
            dbHelper.erase();
            dbHelper.insertAllPosts(rssHelper.fetchPosts());
        }
        return dbHelper.getAllPosts();
    }

    @Override
    protected void onPostExecute(List<Post> posts) {
        super.onPostExecute(posts);
        if (posts == null)
            posts = new ArrayList<>();
        listener.onPostsFetched(posts);
    }

}
