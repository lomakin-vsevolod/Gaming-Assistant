package com.epam.training.gamingassistant.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.epam.training.gamingassistant.MainActivity;
import com.epam.training.gamingassistant.R;
import com.epam.training.gamingassistant.adapter.NewsFeedAdapter;
import com.epam.training.gamingassistant.bo.newsfeed.GetNewsFeedResponse;
import com.epam.training.gamingassistant.imageloader.ImageLoader;
import com.epam.training.gamingassistant.tasks.GetNewsFeedListTask;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NewsFeedFragment extends Fragment implements GetNewsFeedListTask.OnNewsFeedListResponse {

    private String token;
    private ListView listView;
    private NewsFeedAdapter newsFeedAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        token = getArguments().getString(MainActivity.TOKEN);
        View rootView = inflater.inflate(R.layout.news_feed_fragment, container,
                false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.news_list);
    }

    @Override
    public void onResume() {
        super.onResume();
        GetNewsFeedListTask getNewsFeedListTask = new GetNewsFeedListTask(token);
        getNewsFeedListTask.setListener(this);
        getNewsFeedListTask.execute();
    }

    @Override
    public void onCompleted(GetNewsFeedResponse getNewsFeedResponse) {
        newsFeedAdapter = new NewsFeedAdapter(getActivity(), getNewsFeedResponse);
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
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }


}