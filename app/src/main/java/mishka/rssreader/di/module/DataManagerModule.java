package mishka.rssreader.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mishka.rssreader.data.AppDatabase;
import mishka.rssreader.data.PostDao;
import mishka.rssreader.data.rss.RssHelper;
import mishka.rssreader.data.rss.SimpleRssHelper;

@Module
public class DataManagerModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context) {
        AppDatabase database = Room.databaseBuilder(context, AppDatabase.class, "database").build();
        System.out.println("Database created: " + database.hashCode());
        return database;
    }

    @Provides
    PostDao providePostDao(AppDatabase database) {
        return database.postDao();
    }

    @Provides
    RssHelper provideRssHelper(SimpleRssHelper rssHelper) {
        return rssHelper;
    }
}
