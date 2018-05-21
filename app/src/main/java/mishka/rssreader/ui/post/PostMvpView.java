package mishka.rssreader.ui.post;

import mishka.rssreader.ui.MvpView;

public interface PostMvpView extends MvpView {
    void showPost(String imageUrl, String title, String fullText);
    void showPost(String title, String fullText);
}
