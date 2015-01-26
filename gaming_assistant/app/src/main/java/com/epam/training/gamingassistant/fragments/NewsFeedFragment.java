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

import com.epam.training.gamingassistant.MainActivity;
import com.epam.training.gamingassistant.R;
import com.epam.training.gamingassistant.adapter.NewsFeedAdapter;
import com.epam.training.gamingassistant.api.VkApi;
import com.epam.training.gamingassistant.bo.newsfeed.GetNewsFeedResponse;
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
public class NewsFeedFragment extends Fragment {

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
        GetNewsFeedTask getNewsFeedTask = new GetNewsFeedTask();
        getNewsFeedTask.execute();
    }

    private class GetNewsFeedTask extends AsyncTask<Void, Void, GetNewsFeedResponse> {

        private Gson gson = new Gson();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected GetNewsFeedResponse doInBackground(Void... args) {
            BufferedReader in = null;
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(VkApi.NEWS_FEED_GET_URI + token));
                HttpResponse response = client.execute(request);
                in = new BufferedReader
                        (new InputStreamReader(response.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }
                in.close();
                JSONObject jsonObject = new JSONObject(sb.toString()).getJSONObject("response");
                GetNewsFeedResponse newsFeedResponse = gson.fromJson(jsonObject.toString(), GetNewsFeedResponse.class);
                return newsFeedResponse;

            } catch (IOException | JSONException | URISyntaxException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(GetNewsFeedResponse newsFeedResponse) {
            newsFeedAdapter = new NewsFeedAdapter(getActivity(), newsFeedResponse);
            listView.setAdapter(newsFeedAdapter);
        }

    }
}