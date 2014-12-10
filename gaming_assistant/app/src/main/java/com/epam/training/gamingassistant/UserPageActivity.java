package com.epam.training.gamingassistant;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.epam.training.gamingassistant.auth.GoogleOAuthGetAccessToken;
import com.epam.training.gamingassistant.bo.Friend;
import com.epam.training.gamingassistant.helper.DataManager;
import com.epam.training.gamingassistant.processing.FriendArrayProcessor;
import com.epam.training.gamingassistant.source.HttpDataSource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class UserPageActivity extends ActionBarActivity implements DataManager.Callback<List<Friend>> {


    static String TOKEN = "";
    static String FRIENDS_URI ="https://www.googleapis.com/plus/v1/people/me/people/visible?access_token=";
    private ArrayAdapter mAdapter;

    private FriendArrayProcessor mFriendArrayProcessor = new FriendArrayProcessor();

    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        TOKEN = getIntent().getStringExtra("token");
        FRIENDS_URI = FRIENDS_URI+TOKEN;
       /* new executeHttpGet().execute();*/
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        final HttpDataSource dataSource = getHttpDataSource();
        final FriendArrayProcessor processor = getProcessor();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                update(dataSource, processor);
            }
        });
        update(dataSource, processor);

    }

    private FriendArrayProcessor getProcessor() {
        return mFriendArrayProcessor;
    }

    private HttpDataSource getHttpDataSource() {
        return HttpDataSource.get(UserPageActivity.this);
    }

    private void update(HttpDataSource dataSource, FriendArrayProcessor processor) {
        DataManager.loadData(UserPageActivity.this,
                FRIENDS_URI,
                dataSource,
                processor);
    }

    @Override
    public void onDataLoadStart() {
        if (!mSwipeRefreshLayout.isRefreshing()) {
            findViewById(android.R.id.progress).setVisibility(View.VISIBLE);
        }
        findViewById(android.R.id.empty).setVisibility(View.GONE);
    }

    private List<Friend> mData;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onDone(List<Friend> data) {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        findViewById(android.R.id.progress).setVisibility(View.GONE);
        if (data == null || data.isEmpty()) {
            findViewById(android.R.id.empty).setVisibility(View.VISIBLE);
        }
        AdapterView listView = (AbsListView) findViewById(android.R.id.list);
        if (mAdapter == null) {
            mData = data;
            mAdapter = new ArrayAdapter<Friend>(this, R.layout.adapter_item, android.R.id.text1, data) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = View.inflate(UserPageActivity.this, R.layout.adapter_item, null);
                    }
                    Friend item = getItem(position);
                    TextView textView1 = (TextView) convertView.findViewById(android.R.id.text1);
                    textView1.setText(item.getDisplayName());
                    TextView textView2 = (TextView) convertView.findViewById(android.R.id.text2);
                    textView2.setText(item.getUrl());
                    convertView.setTag(item.getId());
                    final ImageView imageView = (ImageView) convertView.findViewById(android.R.id.icon);
                    final String url = item.getImage();
                    imageView.setImageBitmap(null);
                    imageView.setTag(url);
                    if (!TextUtils.isEmpty(url)) {


                    }
                    return convertView;
                }

            };
            listView.setAdapter(mAdapter);
          /*  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    Friend item = (Friend) mAdapter.getItem(position);
                    NoteGsonModel note = new NoteGsonModel(item.getId(), item.getFirstName(), item.getLastName());
                    intent.putExtra("item", note);
                    startActivity(intent);
                }
            });*/
        } else {
            mData.clear();
            mData.addAll(data);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
        findViewById(android.R.id.progress).setVisibility(View.GONE);
        findViewById(android.R.id.empty).setVisibility(View.GONE);
        TextView errorView = (TextView) findViewById(R.id.error);
        errorView.setVisibility(View.VISIBLE);
        errorView.setText(errorView.getText() + "\n" + e.getMessage());
    }


  /*  private class executeHttpGet extends AsyncTask<String, String, String> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... args) {
            BufferedReader in = null;
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI("https://www.googleapis.com/plus/v1/people/me/people/visible?access_token=" + token));
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
                String page = sb.toString();
                return page;

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
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
            String empty = "I am empty";
            return empty;
        }

      /*  @Override
        protected void onPostExecute(String page) {
            TextView tView = (TextView) findViewById(R.id.token);
            tView.setText(page);

        }
    }*/


}
