package mishka.rssreader.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import mishka.rssreader.data.DataManager;
import mishka.rssreader.data.SimpleDataManager;

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
