package mishka.rssreader.ui.feed;

import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import mishka.rssreader.R;
import mishka.rssreader.data.model.RealmRssItem;
import mishka.rssreader.di.component.DaggerFeedComponent;
import mishka.rssreader.di.component.FeedComponent;
import mishka.rssreader.ui.BaseActivity;

public class FeedActivity extends BaseActivity {

    @BindView(R.id.feed_recycler_view)
    RecyclerView feedRecyclerView;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    FeedAdapter adapter;

    @Inject
    FeedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        FeedComponent feedComponent = DaggerFeedComponent.builder().applicationComponent(getApplicationComponent()).build();

        feedComponent.inject(this);

        feedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        feedRecyclerView.setAdapter(adapter);

        adapter.setPosts(viewModel.getRealmItems());

        viewModel.getRealmItems().addChangeListener(new RealmChangeListener<RealmResults<RealmRssItem>>() {
            @Override
            public void onChange(RealmResults<RealmRssItem> realmRssItems) {
                adapter.setPosts(realmRssItems);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.update();
                refreshLayout.setRefreshing(false);
            }
        });
    }
}
