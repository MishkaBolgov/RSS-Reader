package mishka.rssreader.ui.post;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mishka.rssreader.R;
import mishka.rssreader.data.model.RssItem;
import mishka.rssreader.di.component.DaggerPostComponent;
import mishka.rssreader.di.component.PostComponent;
import mishka.rssreader.di.module.PostModule;
import mishka.rssreader.ui.BaseActivity;

public class PostActivity extends BaseActivity {


    @BindView(R.id.post_image)
    ImageView image;
    @BindView(R.id.title)
    TextView tvTitle;
    @BindView(R.id.full_text)
    TextView tvFullText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    PostViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        Intent intent = getIntent();
        int postId = intent.getIntExtra("post_id", -1);


        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        PostComponent postComponent = DaggerPostComponent.builder().postModule(new PostModule(this, postId)).applicationComponent(getApplicationComponent()).build();
        postComponent.inject(this);

        viewModel.getPost().observe(this, new Observer<RssItem>() {
            @Override
            public void onChanged(@Nullable RssItem post) {
                if (post != null)
                    if (post.hasImage())
                        showPost(post.getEnclosureLink(), post.getTitle(), post.getFullText());
                    else showPost(post.getTitle(), post.getFullText());
            }
        });

    }

    public void showPost(String imageUrl, String title, String fullText) {
        Picasso.get().load(imageUrl).into(image);
        tvTitle.setText(title);
        tvFullText.setText(fullText);
    }

    public void showPost(String title, String fullText) {
        image.setVisibility(View.GONE);
        tvTitle.setText(title);
        tvFullText.setText(fullText);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
