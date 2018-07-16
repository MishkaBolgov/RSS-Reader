package mishka.rssreader.di.component;

import dagger.Component;
import mishka.rssreader.di.ActivityScope;
import mishka.rssreader.di.module.ApplicationModule;
import mishka.rssreader.di.module.FeedModule;
import mishka.rssreader.ui.feed.FeedActivity;

@Component(modules = [FeedModule::class], dependencies = [ApplicationComponent::class])
@ActivityScope
interface FeedComponent {
    fun inject(feedActivity: FeedActivity)
}
