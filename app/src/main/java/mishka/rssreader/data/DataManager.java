package mishka.rssreader.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

import io.realm.RealmResults;
import mishka.rssreader.data.model.RealmRssItem;

public interface DataManager {
    void update();
    RealmResults<RealmRssItem> getRssItems();
    RealmRssItem getRssItemById(int id);
}
