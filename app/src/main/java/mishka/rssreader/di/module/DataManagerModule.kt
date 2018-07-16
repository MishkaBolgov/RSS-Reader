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
class DataManagerModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

    @Provides
    fun provideRssItemDao(database: AppDatabase): RssItemDao {
        return database.rssItemDao()
    }

    @Provides
    fun provideRssHelper(rssHelper: RetrofitRssFetcher): RssHelper {
        return rssHelper
    }
}
