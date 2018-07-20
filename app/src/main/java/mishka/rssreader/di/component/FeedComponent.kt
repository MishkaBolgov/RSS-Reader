package mishka.rssreader.di.component

import dagger.Component
import mishka.rssreader.di.ActivityScope
import mishka.rssreader.di.module.FeedModule
import mishka.rssreader.ui.channelsettings.ChannelSettingsFragment
import mishka.rssreader.ui.feed.FeedFragment

@Component(modules = [FeedModule::class], dependencies = [ApplicationComponent::class])
@ActivityScope
interface FeedComponent {
    fun inject(feedActivity: ChannelSettingsFragment)
    fun inject(feedFragment: FeedFragment)
}
