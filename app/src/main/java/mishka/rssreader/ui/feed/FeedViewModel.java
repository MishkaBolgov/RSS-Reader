package mishka.rssreader.ui.feed;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.realm.RealmResults;
import mishka.rssreader.data.DataManager;
import mishka.rssreader.data.model.RealmRssItem;

public class FeedViewModel extends ViewModel {
    private final RealmResults<RealmRssItem> realmResults;
    private DataManager dataManager;

    @Inject
    public FeedViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
        realmResults = dataManager.getRssItems();
    }

    public void update() {
        dataManager.update();
    }

    public RealmResults<RealmRssItem> getRealmItems() {
        return realmResults;
    }
}
