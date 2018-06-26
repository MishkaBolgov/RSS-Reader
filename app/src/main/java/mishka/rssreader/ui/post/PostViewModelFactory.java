package mishka.rssreader.ui.post;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import mishka.rssreader.data.DataManager;
import mishka.rssreader.data.Post;
import mishka.rssreader.data.SimpleDataManager;

public class PostViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private int postId;
    private DataManager dataManager;

    public PostViewModelFactory(DataManager dataManager, int postId) {
        this.postId = postId;
        this.dataManager = dataManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PostViewModel(dataManager, postId);
    }
}
