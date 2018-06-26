package mishka.rssreader.ui.feed;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mishka.rssreader.R;
import mishka.rssreader.data.Post;
import mishka.rssreader.ui.post.PostActivity;

public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Post post;
    private TextView title;
    private TextView pubDate;

    public PostViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        pubDate = itemView.findViewById(R.id.pub_date);
        itemView.setOnClickListener(this);
    }

    public void setPost(Post post) {
        this.post = post;
        title.setText(post.getTitle());
        pubDate.setText(post.getFormattedPubDate());
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), PostActivity.class);
        intent.putExtra("post_id", post.getId());
        view.getContext().startActivity(intent);
    }
}
