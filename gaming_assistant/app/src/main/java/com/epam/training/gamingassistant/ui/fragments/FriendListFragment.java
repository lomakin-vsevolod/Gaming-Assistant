package com.epam.training.gamingassistant.ui.fragments;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.epam.training.gamingassistant.R;
import com.epam.training.gamingassistant.adapter.FriendsAdapter;
import com.epam.training.gamingassistant.bo.friends.Friend;
import com.epam.training.gamingassistant.imageloader.ImageLoader;
import com.epam.training.gamingassistant.loaders.FriendsLoader;
import com.epam.training.gamingassistant.ui.MainActivity;
import com.epam.training.gamingassistant.ui.UserProfileActivity;

import java.util.List;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FriendListFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Friend>> {
    private static final int FRIENDS_LOADER_ID = 1;

    private String token;
    private ListView listView;
    private ProgressBar progressBar;
    private TextView textView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FriendsAdapter friendsAdapter;
    private LoaderManager.LoaderCallbacks<List<Friend>> loaderCallbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        token = getArguments().getString(MainActivity.TOKEN);
        return inflater.inflate(R.layout.friend_list_fragment, container,
                false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.friend_list);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        textView = (TextView) view.findViewById(R.id.empty);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.friends_frame);
        loaderCallbacks = this;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLoaderManager().restartLoader(FRIENDS_LOADER_ID, null, loaderCallbacks);
            }
        });
        showProgress();
        getLoaderManager().initLoader(FRIENDS_LOADER_ID, null, loaderCallbacks);
    }


    @Override
    public Loader<List<Friend>> onCreateLoader(int id, Bundle args) {
        Loader<List<Friend>> loader = null;
        if (id == FRIENDS_LOADER_ID) {
            loader = new FriendsLoader(getActivity(), args, token);
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<Friend>> loader, List<Friend> data) {
        setData(data);
        dismissProgress();
    }

    @Override
    public void onLoaderReset(Loader<List<Friend>> loader) {

    }

    public void setData(List<Friend> friendList) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        if (friendList != null) {
            friendsAdapter = new FriendsAdapter(getActivity(), friendList);
            listView.setAdapter(friendsAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Long userId = friendsAdapter.getId(position);
                    Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                    intent.putExtra(MainActivity.TOKEN, token);
                    intent.putExtra(MainActivity.USER_ID, userId.toString());
                    startActivity(intent);
                }
            });
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
            swipeRefreshLayout.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }


    }


    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }

    public void dismissProgress() {
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }


}