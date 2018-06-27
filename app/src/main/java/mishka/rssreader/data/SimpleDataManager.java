package mishka.rssreader.data;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

import mishka.rssreader.data.model.RssItem;
import mishka.rssreader.data.model.RssItemDao;
import mishka.rssreader.data.rss.RssHelper;

public class SimpleDataManager implements DataManager {
    private RssHelper rssHelper;
    private RssItemDao rssItemDao;

    @Inject
    public SimpleDataManager(RssHelper rssHelper, RssItemDao rssItemDao) {
        this.rssHelper = rssHelper;
        this.rssItemDao = rssItemDao;
    }

    @Override
    public LiveData<List<RssItem>> getPosts() {
        update();
        return rssItemDao.getAll();
    }

    @Override
    public void update() {
        new AsyncPostFetcher(rssHelper, rssItemDao).execute();
    }

    @Override
    public LiveData<RssItem> getPostById(int postId) {
        return rssItemDao.getItemById(postId);
    }


    static class AsyncPostFetcher extends AsyncTask<Void, Void, Void> {
        private RssHelper rssHelper;
        private RssItemDao rssItemDao;


        public AsyncPostFetcher(RssHelper rssHelper, RssItemDao rssItemDao) {
            this.rssHelper = rssHelper;
            this.rssItemDao = rssItemDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<RssItem> items = rssHelper.getRss();
            if (items.size() > 0) {
                rssItemDao.deleteAll();
                rssItemDao.insertAll(items);
            }
            return null;
        }
    }




}
