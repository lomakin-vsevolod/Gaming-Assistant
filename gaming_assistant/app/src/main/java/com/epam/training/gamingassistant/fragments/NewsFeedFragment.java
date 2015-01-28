package com.epam.training.gamingassistant.fragments;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.epam.training.gamingassistant.MainActivity;
import com.epam.training.gamingassistant.R;
import com.epam.training.gamingassistant.adapter.NewsFeedAdapter;
import com.epam.training.gamingassistant.api.VkApi;
import com.epam.training.gamingassistant.api.VkApiConstants;
import com.epam.training.gamingassistant.bo.newsfeed.GetNewsFeedResponse;
import com.epam.training.gamingassistant.tasks.GetNewsFeedListTask;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

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
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }


}