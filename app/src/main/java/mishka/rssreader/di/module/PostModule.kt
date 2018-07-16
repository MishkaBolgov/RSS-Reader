package mishka.rssreader.di.module;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import dagger.Module;
import dagger.Provides;
import mishka.rssreader.data.DataManager;
import mishka.rssreader.ui.post.PostViewModel;
import mishka.rssreader.ui.post.PostViewModelFactory;

@Module
class PostModule(val activity: FragmentActivity, val postId: Int) {

    @Provides
    fun providePostViewModel(factory: PostViewModelFactory): PostViewModel {
        return ViewModelProviders.of(activity, factory).get(PostViewModel::class.java)
    }

    @Provides
    fun providePostViewModelFactory(dataManager: DataManager): PostViewModelFactory {
        return PostViewModelFactory (dataManager, postId)
    }

}
