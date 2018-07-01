package mishka.rssreader.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mishka.rssreader.data.realm.RealmHelper;
import mishka.rssreader.data.realm.SimpleRealmHelper;
import mishka.rssreader.data.rss.RssHelper;
import mishka.rssreader.data.rss.RetrofitRssFetcher;

@Module
public class DataManagerModule {
    @Provides
    RssHelper provideRssHelper(RetrofitRssFetcher rssHelper) {
        return rssHelper;
    }

    @Provides
    RealmHelper provideRealmHelper(SimpleRealmHelper realmHelper){
        return realmHelper;
    }
}
