package mishka.rssreader.di.module;

import dagger.Module;
import dagger.Provides;
import mishka.rssreader.data.Post;

@Module
public class PostModule {
    private Post post;

    public PostModule(Post post) {
        this.post = post;
    }

    @Provides
    Post providePost(){
        return post;
    }
}
