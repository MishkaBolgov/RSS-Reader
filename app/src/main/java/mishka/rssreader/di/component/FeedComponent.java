package mishka.rssreader.di.component;

import dagger.Component;
import mishka.rssreader.di.module.FeedModule;
import mishka.rssreader.ui.feed.FeedFragment;

@Component(modules = FeedModule.class)
public interface FeedComponent {
    void inject(FeedFragment feedFragment);
}
