package com.epam.training.gamingassistant.tasks;

import android.os.AsyncTask;

import com.epam.training.gamingassistant.api.VkApi;
import com.epam.training.gamingassistant.bo.UserProfile;
import com.epam.training.gamingassistant.bo.users.GetUsersResponse;
import com.epam.training.gamingassistant.bo.wall.GetWallResponse;

import java.lang.ref.WeakReference;

public class GetUserProfileTask extends AsyncTask<Void, Void, UserProfile> {

    private WeakReference<OnUserProfile> listener;
    private String token;

    public interface OnUserProfile {
        public void onCompleted(UserProfile userProfile);

        public void onError(String error);
    }

    public void setListener(OnUserProfile listener) {
        this.listener = new WeakReference<OnUserProfile>(listener);
    }

    public GetUserProfileTask(String token) {
        this.token = token;
    }

    @Override
    protected UserProfile doInBackground(Void... params) {
        GetUsersResponse getUsersResponse = VkApi.getUsersResponse(token);
        GetWallResponse getWallResponse = VkApi.getWallResponse(token);
        UserProfile userProfile = new UserProfile(getUsersResponse, getWallResponse);
        return userProfile;
    }

    @Override
    protected void onPostExecute(UserProfile userProfile) {
        if (listener != null) {
            final OnUserProfile onUserProfile = listener.get();
            if (onUserProfile != null) {
                if (userProfile != null) {
                    onUserProfile.onCompleted(userProfile);
                } else {
                    onUserProfile.onError("Bad connection!");
                }
            }
        }
    }
}
