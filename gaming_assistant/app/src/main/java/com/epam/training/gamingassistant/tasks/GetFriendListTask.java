package com.epam.training.gamingassistant.tasks;

import android.os.AsyncTask;

import com.epam.training.gamingassistant.api.VkApi;
import com.epam.training.gamingassistant.bo.friends.GetFriendsResponse;

import java.lang.ref.WeakReference;

public class GetFriendListTask extends AsyncTask<Void, Void, GetFriendsResponse> {

    private WeakReference<OnFriendListResponse> listener;
    private String token;

    public interface OnFriendListResponse {
        public void onCompleted(GetFriendsResponse getFriendsResponse);
        public void onError(String error);
    }

    public void setListener(OnFriendListResponse listener) {
        this.listener = new WeakReference<OnFriendListResponse>(listener);
    }

    public GetFriendListTask(String token) {
        this.token = token;
    }

    @Override
    protected GetFriendsResponse doInBackground(Void... args) {
        return VkApi.getFriendsList(token);
    }

    @Override
    protected void onPostExecute(GetFriendsResponse getFriendsResponse) {
        if (listener != null) {
            final OnFriendListResponse onFriendListResponse = listener.get();
            if (onFriendListResponse != null) {
                if (getFriendsResponse != null) {
                    onFriendListResponse.onCompleted(getFriendsResponse);
                } else {
                    onFriendListResponse.onError("Bad connection!");
                }
            }
        }
    }

}
