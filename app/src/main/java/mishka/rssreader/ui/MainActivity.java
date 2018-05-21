package mishka.rssreader.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import mishka.rssreader.R;
import mishka.rssreader.di.module.ContextModule;
import mishka.rssreader.di.component.DaggerFeedComponent;
import mishka.rssreader.di.component.FeedComponent;
import mishka.rssreader.ui.feed.FeedFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FeedFragment feedFragment = (FeedFragment) getSupportFragmentManager().findFragmentById(R.id.feed_fragment);
        FeedComponent feedComponent = DaggerFeedComponent.builder().contextModule(new ContextModule(this)).build();
        feedComponent.inject(feedFragment);
        feedFragment.onAttached();
    }

}
