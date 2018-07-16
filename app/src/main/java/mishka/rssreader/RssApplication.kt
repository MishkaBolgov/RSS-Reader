package mishka.rssreader;

import android.app.Application;

import mishka.rssreader.di.component.ApplicationComponent;
import mishka.rssreader.di.component.DaggerApplicationComponent;
import mishka.rssreader.di.module.ApplicationModule;

public class RssApplication: Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
//        deleteDatabase("database")
    }
}
