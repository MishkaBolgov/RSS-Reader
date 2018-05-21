package mishka.rssreader.di.component;

import dagger.Component;
import mishka.rssreader.di.module.ContextModule;
import mishka.rssreader.di.module.PostModule;
import mishka.rssreader.ui.post.PostActivity;

@Component(modules = {PostModule.class, ContextModule.class})
public interface PostComponent {
    void inject(PostActivity postActivity);
}
