package mishka.rssreader.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import mishka.rssreader.data.DataManager;
import mishka.rssreader.di.module.ApplicationModule;
import mishka.rssreader.di.module.DataManagerModule;

@Component(modules = [ApplicationModule::class, DataManagerModule::class])
@Singleton
interface ApplicationComponent {
    fun getDataManager(): DataManager
    fun getContext(): Context
}
