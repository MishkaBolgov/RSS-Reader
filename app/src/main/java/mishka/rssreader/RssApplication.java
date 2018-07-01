package mishka.rssreader;

import android.app.Application;

import io.realm.Realm;
import mishka.rssreader.di.component.ApplicationComponent;
import mishka.rssreader.di.component.DaggerApplicationComponent;
import mishka.rssreader.di.module.ApplicationModule;

public class RssApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        Realm.init(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
