package mishka.rssreader.ui.post;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import mishka.rssreader.R;
import mishka.rssreader.data.Post;
import mishka.rssreader.di.component.DaggerPostComponent;
import mishka.rssreader.di.component.PostComponent;
import mishka.rssreader.di.module.PostModule;

public class PostActivity extends AppCompatActivity implements PostMvpView {

    @Inject
    PostPresenter presenter;

    private ImageView image;
    private TextView tvTitle;
    private TextView tvFullText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        image = findViewById(R.id.post_image);
        tvTitle = findViewById(R.id.title);
        tvFullText = findViewById(R.id.full_text);

        Intent intent = getIntent();
        Post post = (Post) intent.getSerializableExtra("post");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        PostComponent postComponent = DaggerPostComponent.builder().postModule(new PostModule(post)).build();
        postComponent.inject(this);
        presenter.setMvpView(this);
    }

    @Override
    public void showPost(String imageUrl, String title, String fullText) {
        Picasso.get().load(imageUrl).into(image);
        tvTitle.setText(title);
        tvFullText.setText(fullText);
    }

    @Override
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
