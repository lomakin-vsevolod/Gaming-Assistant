package com.epam.training.gamingassistant.loaders;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import com.epam.training.gamingassistant.api.VkApi;
import com.epam.training.gamingassistant.bo.newsfeed.GetNewsFeedResponse;


public class NewsFeedLoader extends AsyncTaskLoader<GetNewsFeedResponse> {

    private String token;

    public NewsFeedLoader(Context context, Bundle args, String token) {
        super(context);
        this.token = token;
    }

    @Override
    public GetNewsFeedResponse loadInBackground() {
        return VkApi.getFeed(token);
    }

    @Override
    public void deliverResult(GetNewsFeedResponse data) {
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
    public void onCanceled(GetNewsFeedResponse data) {
        super.onCanceled(data);
    }
}
