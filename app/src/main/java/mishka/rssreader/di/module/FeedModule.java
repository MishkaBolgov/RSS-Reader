package mishka.rssreader.di.module;

import dagger.Module;
import dagger.Provides;
import mishka.rssreader.data.DataManager;
import mishka.rssreader.data.SimpleDataManager;
import mishka.rssreader.ui.feed.FeedAdapter;
import mishka.rssreader.ui.feed.FeedViewModel;

@Module
public class FeedModule {

    @Provides
    FeedAdapter provideFeedAdapter(){
        return new FeedAdapter();
    }

    @Provides
    FeedViewModel provideFeedViewModel(DataManager dataManager){
        return new FeedViewModel(dataManager);
    }

}
