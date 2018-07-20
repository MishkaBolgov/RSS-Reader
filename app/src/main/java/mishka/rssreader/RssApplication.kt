package mishka.rssreader;

import android.app.Application;
import android.content.Context

import mishka.rssreader.di.component.ApplicationComponent;
import mishka.rssreader.di.component.DaggerApplicationComponent;
import mishka.rssreader.di.module.ApplicationModule;
import mishka.rssreader.ui.channelsettings.CHANNEL_URLS_PREF
import mishka.rssreader.ui.channelsettings.CURRENT_CHANNEL_KEY
import mishka.rssreader.ui.channelsettings.CURRENT_CHANNEL_URL_PREF
import mishka.rssreader.ui.channelsettings.ChannelConfigUtils

public class RssApplication: Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        deleteDatabase("database")




//        ChannelConfigUtils(this).deleteAllChannels()
//        ChannelConfigUtils(this).resetCurrentChannelUrl()
        ChannelConfigUtils(this)
    }
}
