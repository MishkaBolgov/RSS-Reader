package mishka.rssreader.ui.feed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mishka.rssreader.R;
import mishka.rssreader.data.model.RealmRssItem;

public class FeedAdapter extends RecyclerView.Adapter<RssItemViewHolder>{
    private List<RealmRssItem> posts;

    public void setPosts(List<RealmRssItem> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @Inject
    public FeedAdapter() {
        posts = new ArrayList<>();
    }

    @Override
    public RssItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view, parent, false);
        RssItemViewHolder postViewHolder = new RssItemViewHolder(view);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(RssItemViewHolder holder, int position) {
        holder.setPost(posts.get(position));
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

}
