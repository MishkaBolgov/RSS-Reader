package mishka.rssreader.di.module;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import dagger.Module;
import dagger.Provides;
import mishka.rssreader.data.DataManager;
import mishka.rssreader.ui.post.PostViewModel;
import mishka.rssreader.ui.post.PostViewModelFactory;

@Module
public class PostModule {

    private FragmentActivity activity;
    private int postId;

    public PostModule(FragmentActivity activity, int postId) {
        this.activity = activity;
        this.postId = postId;
    }

    @Provides
    PostViewModel providePostViewModel(PostViewModelFactory factory){
        return ViewModelProviders.of(activity, factory).get(PostViewModel.class);
    }

    @Provides
    PostViewModelFactory providePostViewModelFactory(DataManager dataManager){
        return new PostViewModelFactory(dataManager, postId);
    }

}
