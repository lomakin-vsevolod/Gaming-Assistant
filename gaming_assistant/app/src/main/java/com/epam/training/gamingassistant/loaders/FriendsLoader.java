package com.epam.training.gamingassistant.loaders;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import com.epam.training.gamingassistant.api.VkApi;
import com.epam.training.gamingassistant.bo.friends.Friend;
import com.epam.training.gamingassistant.bo.friends.GetFriendsResponse;
import com.epam.training.gamingassistant.db.FriendsDbHelper;

import java.util.List;

public class FriendsLoader extends AsyncTaskLoader<List<Friend>> {

    private String token;

    public FriendsLoader(Context context, Bundle args, String token) {
        super(context);
        this.token = token;
    }

    @Override
    public List<Friend> loadInBackground() {
        FriendsDbHelper friendsDbHelper = new FriendsDbHelper(getContext());
        GetFriendsResponse getFriendsResponse = VkApi.getFriendsList(token);
        List<Friend> list = getFriendsResponse.getItems();
        if (list != null) {
            friendsDbHelper.refreshTable();
            friendsDbHelper.insertFriends(list);
            return list;
        } else {
            list = friendsDbHelper.getFriends();
            if (list.size() != 0) {
                return list;
            }
        }
        return null;
    }

    @Override
    public void deliverResult(List<Friend> data) {
        if (isReset()) {
            return;
        }

        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();
    }

    @Override
    public void onCanceled(List<Friend> data) {
        super.onCanceled(data);
    }

}
