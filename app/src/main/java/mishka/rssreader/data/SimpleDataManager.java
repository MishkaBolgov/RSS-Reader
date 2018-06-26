package mishka.rssreader.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import mishka.rssreader.data.rss.RssHelper;

public class SimpleDataManager implements DataManager {

    private PostDao postDao;
    private RssHelper rssHelper;

    @Inject
    public SimpleDataManager(PostDao postDao, RssHelper rssHelper) {
        this.postDao = postDao;
        this.rssHelper = rssHelper;
    }

    @Override
    public void update() {
        new AsyncPostFetcher(rssHelper, postDao).execute();
    }

    @Override
    public LiveData<Post> getPostById(int postId) {
        return postDao.getPostById(postId);
    }

    static class AsyncPostFetcher extends AsyncTask<Void, Void, Void> {
        private RssHelper rssHelper;
        private PostDao postDao;

        public AsyncPostFetcher(RssHelper rssHelper, PostDao postDao) {
            this.rssHelper = rssHelper;
            this.postDao = postDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<Post> posts = rssHelper.fetchPosts();
            if (posts.size() > 0) {
                postDao.deleteAll();
                postDao.insertAll(posts);
            }
            return null;
        }
    }


    public LiveData<List<Post>> getPosts() {
        update();
        return postDao.getPosts();
    }

}
