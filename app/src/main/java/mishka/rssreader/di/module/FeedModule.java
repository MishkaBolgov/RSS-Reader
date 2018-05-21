package mishka.rssreader.di.module;

import dagger.Module;
import dagger.Provides;
import mishka.rssreader.ui.feed.FeedAdapter;
import mishka.rssreader.ui.feed.FeedMvpPresenter;
import mishka.rssreader.ui.feed.FeedPresenter;

@Module(includes = DatabaseModule.class)
public class FeedModule {

    @Provides
    FeedMvpPresenter provideFeedMvpPresenter(FeedPresenter feedPresenter){
        return feedPresenter;
    }

    @Provides
    FeedAdapter provideFeedAdapter(){
        return new FeedAdapter();
    }
}
