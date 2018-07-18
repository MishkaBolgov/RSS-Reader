package mishka.rssreader.di.component

import dagger.Component
import mishka.rssreader.di.ActivityScope
import mishka.rssreader.di.module.ChannelSettingsModule
import mishka.rssreader.ui.channelsettings.ChannelSettingsFragment

@Component(dependencies = [ApplicationComponent::class],modules = [ChannelSettingsModule::class])
@ActivityScope
interface ChannelSettingsComponent {
    fun inject(channelSettingsFragment: ChannelSettingsFragment)
}