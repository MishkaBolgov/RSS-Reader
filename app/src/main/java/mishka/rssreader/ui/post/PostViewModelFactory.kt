package mishka.rssreader.ui.post;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.view.View

import mishka.rssreader.data.DataManager;

class PostViewModelFactory(val dataManager: DataManager, val postId: Int) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(dataManager, postId) as T
    }
}
