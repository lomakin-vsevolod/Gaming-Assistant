package com.epam.training.gamingassistant.loaders;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import com.epam.training.gamingassistant.api.VkApi;
import com.epam.training.gamingassistant.bo.UserProfile;
import com.epam.training.gamingassistant.bo.users.GetUsersResponse;
import com.epam.training.gamingassistant.bo.wall.GetWallResponse;
import com.epam.training.gamingassistant.ui.MainActivity;

public class UserProfileLoader extends AsyncTaskLoader<UserProfile> {

    private String token;
    private String userId;

    public UserProfileLoader(Context context, Bundle args, String token) {
        super(context);
        this.token = token;
        if (args != null) {
            this.userId = args.getString(MainActivity.USER_ID);
        }
    }

    @Override
    public UserProfile loadInBackground() {
        if (userId != null) {
            GetUsersResponse getUsersResponse = VkApi.getUsersResponse(token + "&user_ids=" + userId);
            GetWallResponse getWallResponse = VkApi.getWallResponse(token + "&owner_id=" + userId);
            return new UserProfile(getUsersResponse, getWallResponse);
        } else {
            GetUsersResponse getUsersResponse = VkApi.getUsersResponse(token);
            GetWallResponse getWallResponse = VkApi.getWallResponse(token);
            return new UserProfile(getUsersResponse, getWallResponse);
        }
    }

    @Override
    public void deliverResult(UserProfile data) {
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
    public void onCanceled(UserProfile data) {
        super.onCanceled(data);
    }
}
