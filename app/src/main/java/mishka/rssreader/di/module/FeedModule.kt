package mishka.rssreader.di.module;

import dagger.Module;
import dagger.Provides;
import mishka.rssreader.data.DataManager;
import mishka.rssreader.data.SimpleDataManager;
import mishka.rssreader.ui.channelsettings.ChannelSettingsViewModel
import mishka.rssreader.ui.feed.FeedAdapter;
import mishka.rssreader.ui.feed.FeedViewModel;
import mishka.rssreader.ui.feed.OnFeedItemClickListener

@Module
class FeedModule(val itemClickListener: OnFeedItemClickListener) {

    @Provides
    fun provideFeedAdapter(): FeedAdapter {
        return FeedAdapter(itemClickListener)
    }

    @Provides
    fun provideFeedViewModel(dataManager: DataManager): FeedViewModel {
        return FeedViewModel(dataManager)
    }

}
