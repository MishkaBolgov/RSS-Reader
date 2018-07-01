package mishka.rssreader.data.realm;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import mishka.rssreader.data.model.RealmRss;
import mishka.rssreader.data.model.RealmRssItem;

public class SimpleRealmHelper implements RealmHelper {

    @Inject
    public SimpleRealmHelper() {
    }

    @Override
    public void insertAll(final List<RealmRssItem> items) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (RealmRssItem item : items) {
                    item.setId( getNextItemId());
                    realm.copyToRealm(item);
                }
            }
        });
    }

    private int getNextItemId() {
        Realm realm = Realm.getDefaultInstance();
        Number maxId = realm.where(RealmRssItem.class).max("id");
        if (maxId == null)
            return 0;
        else return maxId.intValue() + 1;
    }

    @Override
    public void deleteAll() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(RealmRssItem.class).findAll().deleteAllFromRealm();
            }
        });
    }

    @Override
    public RealmResults<RealmRssItem> getRssItems() {
        return Realm.getDefaultInstance().where(RealmRssItem.class).findAll();
    }

    @Override
    public RealmRssItem getRssItemById(int id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(RealmRssItem.class).equalTo("id", id).findFirst();
    }
}
