package mishka.rssreader.ui.feed;

import java.util.List;

import javax.inject.Inject;

import mishka.rssreader.data.DataManager;
import mishka.rssreader.data.Post;
import mishka.rssreader.ui.MvpView;

public class FeedPresenter implements FeedMvpPresenter, DataManager.PostFetchedListener {
    private DataManager dataManager;
    private FeedMvpView feedMvpView;

    @Inject
    public FeedPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void setMvpView(MvpView mvpView) {
        feedMvpView = (FeedMvpView) mvpView;
    }

    @Override
    public void onViewPrepared() {
        dataManager.getAllPosts(this);
    }

    @Override
    public void onPostsFetched(List<Post> posts) {
        feedMvpView.updateFeed(posts);
    }

    @Override
    public void updateFeed() {
        dataManager.getAllPosts(this);
    }
}
