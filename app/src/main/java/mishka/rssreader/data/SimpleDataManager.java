package mishka.rssreader.data;

import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

import io.realm.RealmResults;
import mishka.rssreader.data.model.RealmRssItem;
import mishka.rssreader.data.realm.RealmHelper;
import mishka.rssreader.data.rss.RssHelper;
import mishka.rssreader.utils.NetworkUtils;

public class SimpleDataManager implements DataManager {
    private RssHelper rssHelper;
    private RealmHelper realmHelper;
    private NetworkUtils networkUtils;

    @Inject
    public SimpleDataManager(RssHelper rssHelper, RealmHelper realmHelper, NetworkUtils networkUtils) {
        this.rssHelper = rssHelper;
        this.realmHelper = realmHelper;
        this.networkUtils = networkUtils;
    }

    @Override
    public void update() {
        if (networkUtils.isInternetConnected())
            new AsyncPostFetcher(rssHelper, realmHelper).execute();
    }

    @Override
    public RealmResults<RealmRssItem> getRssItems() {
        update();
        return realmHelper.getRssItems();
    }

    @Override
    public RealmRssItem getRssItemById(int id) {
        return realmHelper.getRssItemById(id);
    }

    static class AsyncPostFetcher extends AsyncTask<Void, Void, Void> {
        private RssHelper rssHelper;
        private RealmHelper realmHelper;


        public AsyncPostFetcher(RssHelper rssHelper, RealmHelper realmHelper) {
            this.rssHelper = rssHelper;
            this.realmHelper = realmHelper;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            updateRealmDb();
            return null;
        }

        private void updateRealmDb() {

            List<RealmRssItem> realmItems = rssHelper.getRealmRss();
            if (realmItems.size() > 0) {
                realmHelper.deleteAll();
                realmHelper.insertAll(realmItems);
            }
        }
    }
}
