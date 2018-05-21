package mishka.rssreader.ui.feed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import mishka.rssreader.R;
import mishka.rssreader.data.Post;

public class FeedFragment extends Fragment implements FeedMvpView {

    @Inject
    FeedMvpPresenter presenter;

    @Inject
    FeedAdapter feedAdapter;

    private RecyclerView feedRecyclerView;

    private SwipeRefreshLayout refreshLayout;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        feedRecyclerView = view.findViewById(R.id.feed_recycler_view);

        refreshLayout = view.findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.updateFeed();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        feedRecyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void updateFeed(List<Post> posts) {
        feedAdapter.setPosts(posts);
        refreshLayout.setRefreshing(false);
    }

    public void onAttached() {
        feedRecyclerView.setAdapter(feedAdapter);
        presenter.setMvpView(this);
        presenter.onViewPrepared();
    }

}
