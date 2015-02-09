package com.epam.training.gamingassistant.ui.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.epam.training.gamingassistant.R;
import com.epam.training.gamingassistant.adapter.NewsFeedAdapter;
import com.epam.training.gamingassistant.bo.newsfeed.GetNewsFeedResponse;
import com.epam.training.gamingassistant.imageloader.ImageLoader;
import com.epam.training.gamingassistant.loaders.NewsFeedLoader;
import com.epam.training.gamingassistant.ui.MainActivity;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NewsFeedFragment extends Fragment implements LoaderManager.LoaderCallbacks<GetNewsFeedResponse> {
    private static final int NEWS_FEED_LOADER_ID = 1;

    private String token;
    private ListView listView;
    private ProgressBar progressBar;
    private TextView textView;
    private FrameLayout frameLayout;
    private NewsFeedAdapter newsFeedAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        token = getArguments().getString(MainActivity.TOKEN);

        return inflater.inflate(R.layout.news_feed_fragment, container,
                false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.news_list);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        textView = (TextView) view.findViewById(R.id.empty);
        frameLayout = (FrameLayout) view.findViewById(R.id.news_frame);
        showProgress();
        getLoaderManager().initLoader(NEWS_FEED_LOADER_ID, null, this);
    }

    @Override
    public Loader<GetNewsFeedResponse> onCreateLoader(int id, Bundle args) {
        Loader<GetNewsFeedResponse> loader = null;
        if (id == NEWS_FEED_LOADER_ID) {
            loader = new NewsFeedLoader(getActivity(), args, token);
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<GetNewsFeedResponse> loader, GetNewsFeedResponse data) {
        setData(data);
        dismissProgress();
    }

    @Override
    public void onLoaderReset(Loader<GetNewsFeedResponse> loader) {

    }

    public void setData(GetNewsFeedResponse data) {
        if (data.getItems() != null) {
            newsFeedAdapter = new NewsFeedAdapter(getActivity(), data);
            listView.setAdapter(newsFeedAdapter);
            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    switch (scrollState) {
                        case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                            ImageLoader.getImageLoader().start();
                            break;
                        case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                            ImageLoader.getImageLoader().stop();
                            break;
                        case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                            ImageLoader.getImageLoader().stop();
                            break;
                    }
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                }
            });
        } else {
            Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            frameLayout.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }


    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        frameLayout.setVisibility(View.GONE);
    }

    public void dismissProgress() {
        progressBar.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
    }
}