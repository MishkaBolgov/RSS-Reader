package mishka.rssreader.data.realm;

import java.util.List;

import io.realm.RealmResults;
import mishka.rssreader.data.model.RealmRssItem;

public interface RealmHelper {
    void insertAll(List<RealmRssItem> items);
    void deleteAll();

    RealmResults<RealmRssItem> getRssItems();

    RealmRssItem getRssItemById(int id);
}
