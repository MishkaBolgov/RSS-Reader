package mishka.rssreader.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import javax.inject.Inject;

import mishka.rssreader.data.db.DbHelper;
import mishka.rssreader.data.rss.RssHelper;

public class SimpleDataManager implements DataManager {
    private RssHelper rssHelper;
    private DbHelper dbHelper;
    private Context context;

    @Inject
    public SimpleDataManager(RssHelper rssHelper, DbHelper dbHelper, Context context) {
        this.rssHelper = rssHelper;
        this.dbHelper = dbHelper;
        this.context = context;
    }

    @Override
    public void getAllPosts(PostFetchedListener postFetchedListener) {
        if (isInternetConnected()) {
            updateAndFetchFromDb(postFetchedListener);
        } else {
            fetchFromDb(postFetchedListener);
            Toast.makeText(context, "No internet connection.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null)
                return true;
        }
        return false;
    }

    private void fetchFromDb(PostFetchedListener postFetchedListener) {
        new AsyncFeedFetcher(dbHelper, rssHelper, postFetchedListener).execute(false);
    }

    private void updateAndFetchFromDb(PostFetchedListener postFetchedListener) {
        new AsyncFeedFetcher(dbHelper, rssHelper, postFetchedListener).execute(true);
    }

}
