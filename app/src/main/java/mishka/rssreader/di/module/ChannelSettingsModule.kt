package mishka.rssreader.di.module

import dagger.Module
import dagger.Provides
import mishka.rssreader.ui.channelsettings.ChannelConfigUtils
import mishka.rssreader.ui.channelsettings.ChannelSettingsViewModel

@Module
class ChannelSettingsModule{

    @Provides
    fun provideChannelSettingsViewModel(channelConfigUtils: ChannelConfigUtils): ChannelSettingsViewModel {
        return ChannelSettingsViewModel(channelConfigUtils)
    }

}