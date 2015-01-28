package com.epam.training.gamingassistant.tasks;

import android.os.AsyncTask;

import com.epam.training.gamingassistant.api.VkApi;
import com.epam.training.gamingassistant.bo.newsfeed.GetNewsFeedResponse;

import java.lang.ref.WeakReference;


public class GetNewsFeedListTask extends AsyncTask<Void,Void,GetNewsFeedResponse> {

    private WeakReference<OnNewsFeedListResponse> listener;
    private String token;

    public interface OnNewsFeedListResponse{
        public void onCompleted(GetNewsFeedResponse getNewsFeedResponse);
        public void onError(String error);
    }

    public void setListener(OnNewsFeedListResponse listener){
        this.listener = new WeakReference<OnNewsFeedListResponse>(listener);
    }

    public GetNewsFeedListTask (String token){
        this.token = token;
    }

    @Override
    protected GetNewsFeedResponse doInBackground(Void... params) {
        return VkApi.getFeed(token);
    }

    @Override
    protected void onPostExecute(GetNewsFeedResponse getNewsFeedResponse) {
        if (listener != null) {
            final OnNewsFeedListResponse onNewsFeedListResponse = listener.get();
            if (onNewsFeedListResponse != null) {
                if (getNewsFeedResponse != null) {
                    onNewsFeedListResponse.onCompleted(getNewsFeedResponse);
                } else {
                    onNewsFeedListResponse.onError("Bad connection!");
                }
            }
        }
    }
}
