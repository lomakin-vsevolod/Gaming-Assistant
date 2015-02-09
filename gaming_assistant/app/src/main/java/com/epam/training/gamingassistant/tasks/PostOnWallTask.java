package com.epam.training.gamingassistant.tasks;


import android.os.AsyncTask;

import com.epam.training.gamingassistant.api.VkApi;

import java.lang.ref.WeakReference;

public class PostOnWallTask extends AsyncTask<Void, Void, String> {
    private WeakReference<OnPostWallResponse> listener;
    private String token;

    public interface OnPostWallResponse {
        public void onPostCompleted(String result);

        public void onPostError(String error);
    }

    public void setListener(OnPostWallResponse listener) {
        this.listener = new WeakReference<OnPostWallResponse>(listener);
    }

    public PostOnWallTask(String token) {
        this.token = token;
    }

    @Override
    protected String doInBackground(Void... args) {
        return VkApi.postOnWall(token);
    }

    @Override
    protected void onPostExecute(String result) {
        if (listener != null) {
            final OnPostWallResponse onPostWallResponse = listener.get();
            if (onPostWallResponse != null) {
                if (result != null) {
                    onPostWallResponse.onPostCompleted(result);
                } else {
                    onPostWallResponse.onPostError(result);
                }
            }
        }
    }
}
