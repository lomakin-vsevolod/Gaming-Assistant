package com.epam.training.gamingassistant.fragments;

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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.epam.training.gamingassistant.MainActivity;
import com.epam.training.gamingassistant.R;
import com.epam.training.gamingassistant.adapter.FriendsAdapter;
import com.epam.training.gamingassistant.bo.friends.Friend;
import com.epam.training.gamingassistant.db.FriendsDbHelper;
import com.epam.training.gamingassistant.imageloader.ImageLoader;
import com.epam.training.gamingassistant.loaders.FriendsLoader;

import java.util.List;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FriendListFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Friend>> {
    private static final int FRIENDS_LOADER_ID = 1;

    private String token;
    private ListView listView;
    private ProgressBar progressBar;
    private TextView textView;
    private FriendsAdapter friendsAdapter;

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
        showProgress();
        getLoaderManager().initLoader(FRIENDS_LOADER_ID, null, this);
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
        if (friendList != null) {
            friendsAdapter = new FriendsAdapter(getActivity(), friendList);
            listView.setAdapter(friendsAdapter);
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
            listView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }


    }


    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    public void dismissProgress() {
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }


}