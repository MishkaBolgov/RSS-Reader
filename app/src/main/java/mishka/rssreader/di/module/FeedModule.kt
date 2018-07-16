package mishka.rssreader.di.module;

import dagger.Module;
import dagger.Provides;
import mishka.rssreader.data.DataManager;
import mishka.rssreader.data.SimpleDataManager;
import mishka.rssreader.ui.feed.FeedAdapter;
import mishka.rssreader.ui.feed.FeedViewModel;

@Module
class FeedModule {

    @Provides
    fun provideFeedAdapter(): FeedAdapter {
        return FeedAdapter()
    }

    @Provides
    fun provideFeedViewModel(dataManager: DataManager): FeedViewModel {
        return FeedViewModel (dataManager)
    }

}
