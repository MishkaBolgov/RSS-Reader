package mishka.rssreader.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import mishka.rssreader.data.AppDatabase;
import mishka.rssreader.data.DataManager;
import mishka.rssreader.data.PostDao;
import mishka.rssreader.data.SimpleDataManager;
import mishka.rssreader.data.rss.RssHelper;
import mishka.rssreader.data.rss.SimpleRssHelper;

@Module(includes = DataManagerModule.class)
public class ApplicationModule { private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext(){
        return context;
    }

    @Provides
    DataManager provideDataManager(SimpleDataManager simpleDataManager) {
        return simpleDataManager;
    }
}
