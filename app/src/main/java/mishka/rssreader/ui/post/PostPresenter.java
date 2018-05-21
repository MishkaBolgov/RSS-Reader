package mishka.rssreader.ui.post;

import javax.inject.Inject;

import mishka.rssreader.data.Post;
import mishka.rssreader.ui.MvpView;

public class PostPresenter implements PostMvpPresenter {
    private PostMvpView view;
    private Post post;

    @Inject
    public PostPresenter(Post post) {
        this.post = post;
    }

    @Override
    public void setMvpView(MvpView mvpView) {
        view = (PostMvpView) mvpView;
        onViewPrepared();
    }

    @Override
    public void onViewPrepared() {
        if (post.hasImage())
            view.showPost(post.getImageLink(), post.getTitle(), post.getFullText());
        else view.showPost(post.getTitle(), post.getFullText());
    }
}
