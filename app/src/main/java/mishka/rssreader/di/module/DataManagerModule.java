package mishka.rssreader.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mishka.rssreader.data.AppDatabase;
import mishka.rssreader.data.model.RssItemDao;
import mishka.rssreader.data.rss.RssHelper;
import mishka.rssreader.data.rss.RetrofitRssFetcher;

@Module
public class DataManagerModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context) {
        AppDatabase database = Room.databaseBuilder(context, AppDatabase.class, "database").build();
        return database;
    }

    @Provides
    RssItemDao provideRssItemDao(AppDatabase database){
        return database.rssItemDao();
    }

    @Provides
    RssHelper provideRssHelper(RetrofitRssFetcher rssHelper) {
        return rssHelper;
    }
}
