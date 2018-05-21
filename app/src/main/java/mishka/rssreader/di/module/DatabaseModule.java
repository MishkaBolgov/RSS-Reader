package mishka.rssreader.di.module;

import dagger.Module;
import dagger.Provides;
import mishka.rssreader.data.DataManager;
import mishka.rssreader.data.SimpleDataManager;
import mishka.rssreader.data.db.DbHelper;
import mishka.rssreader.data.db.SimpleDbHelper;
import mishka.rssreader.data.rss.RssHelper;
import mishka.rssreader.data.rss.SimpleRssHelper;

@Module(includes = ContextModule.class)
public class DatabaseModule {
    @Provides
    DataManager provideDataManager(SimpleDataManager dataManager) {
        return dataManager;
    }

    @Provides
    DbHelper provideDbHelper(SimpleDbHelper dbHelper) {
        return dbHelper;
    }

    @Provides
    RssHelper provideRssHelper(SimpleRssHelper rssHelper) {
        return rssHelper;
    }

}
