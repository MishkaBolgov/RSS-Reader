package mishka.rssreader.ui.feed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mishka.rssreader.R;
import mishka.rssreader.data.Post;

public class FeedAdapter extends RecyclerView.Adapter<PostViewHolder>{
    private List<Post> posts;

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @Inject
    public FeedAdapter() {
        posts = new ArrayList<>();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view, parent, false);
        PostViewHolder postViewHolder = new PostViewHolder(view);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.setPost(posts.get(position));
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

}
