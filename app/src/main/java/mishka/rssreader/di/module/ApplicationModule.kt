package mishka.rssreader.di.module

import android.content.Context

import dagger.Module
import dagger.Provides
import mishka.rssreader.data.DataManager
import mishka.rssreader.data.SimpleDataManager

@Module(includes = [DataManagerModule::class])
class ApplicationModule(val context: Context) {

    @Provides
    fun provideContext(): Context{
        return context
    }

    @Provides
    fun provideDataManager(simpleDataManager: SimpleDataManager): DataManager {
        return simpleDataManager
    }
}
