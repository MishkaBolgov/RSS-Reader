package mishka.rssreader.ui.feed;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mishka.rssreader.R;
import mishka.rssreader.data.model.RssItem;
import mishka.rssreader.ui.post.PostActivity;

public class RssItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private RssItem post;
    private TextView title;
    private TextView pubDate;

    public RssItemViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        pubDate = itemView.findViewById(R.id.pub_date);
        itemView.setOnClickListener(this);
    }

    public void setPost(RssItem post) {
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
