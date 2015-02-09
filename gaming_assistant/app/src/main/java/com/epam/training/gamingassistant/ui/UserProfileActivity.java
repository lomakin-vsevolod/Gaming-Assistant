package com.epam.training.gamingassistant.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.epam.training.gamingassistant.R;
import com.epam.training.gamingassistant.adapter.WallAdapter;
import com.epam.training.gamingassistant.bo.UserProfile;
import com.epam.training.gamingassistant.imageloader.ImageLoader;
import com.epam.training.gamingassistant.loaders.UserProfileLoader;
import com.epam.training.gamingassistant.tasks.PostOnWallTask;


public class UserProfileActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<UserProfile>, PostOnWallTask.OnPostWallResponse {
    private static final int PROFILE_LOADER_ID = 1;

    private String token;
    private String userId;
    private ImageView userAvatarImageView;
    private TextView userNameTextView;
    private TextView userStatusTextView;
    private ListView wallListView;
    private EditText editText;
    private WallAdapter wallAdapter;
    private Button sendButton;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    private TextView empty;
    private PostOnWallTask.OnPostWallResponse onPostWallResponse;
    private LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        token = getIntent().getStringExtra(MainActivity.TOKEN);
        userId = getIntent().getStringExtra(MainActivity.USER_ID);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        linearLayout = (LinearLayout) findViewById(R.id.user_profile_layout);
        userAvatarImageView = (ImageView) findViewById(R.id.user_avatar);
        userNameTextView = (TextView) findViewById(R.id.user_name);
        userStatusTextView = (TextView) findViewById(R.id.user_status);
        wallListView = (ListView) findViewById(R.id.wall_list);
        editText = (EditText) findViewById(R.id.wall_post_message);
        sendButton = (Button) findViewById(R.id.send_wall_post);
        empty = (TextView) findViewById(R.id.empty);
        onPostWallResponse = this;

        showProgress();
        loaderManager = getSupportLoaderManager();
        Bundle args = new Bundle();
        args.putString(MainActivity.USER_ID, userId);
        loaderManager.initLoader(PROFILE_LOADER_ID, args, this);
    }

    @Override
    public Loader<UserProfile> onCreateLoader(int id, Bundle args) {
        Loader<UserProfile> loader = null;
        if (id == PROFILE_LOADER_ID) {
            loader = new UserProfileLoader(this, args, token);
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<UserProfile> loader, UserProfile data) {
        setData(data);
        dismissProgress();
    }

    @Override
    public void onLoaderReset(Loader<UserProfile> loader) {

    }

    public void setData(UserProfile userProfile) {
        if (userProfile != null) {
            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editText.getText().toString().equals("")) {
                        onPostError("Must not empty");
                    } else {
                        PostOnWallTask postOnWallTask = new PostOnWallTask(token + "&message=" + editText.getText().toString() + "&owner_id=" + userId);
                        postOnWallTask.setListener(onPostWallResponse);
                        postOnWallTask.execute();
                    }
                }
            });
            if (userProfile.getGetUsersResponse().getResponse() != null && userProfile.getGetWallResponse().getItems() != null) {
                ImageLoader.getImageLoader().loadImage(userProfile.getGetUsersResponse().getResponse().get(0).getPhoto_100(), userAvatarImageView);
                userNameTextView.setText(userProfile.getGetUsersResponse().getResponse().get(0).getFirst_name() + " " + userProfile.getGetUsersResponse().getResponse().get(0).getLast_name());
                if (!userProfile.getGetUsersResponse().getResponse().get(0).getStatus().equals("")) {
                    userStatusTextView.setText(userProfile.getGetUsersResponse().getResponse().get(0).getStatus());
                }
                wallAdapter = new WallAdapter(this, userProfile.getGetWallResponse());
                wallListView.setAdapter(wallAdapter);
                wallListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        switch (scrollState) {
                            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                                ImageLoader.getImageLoader().start();
                                break;
                            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                                ImageLoader.getImageLoader().stop();
                                break;
                            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                                ImageLoader.getImageLoader().stop();
                                break;
                        }
                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                    }
                });
            }

        } else {
            Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            linearLayout.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onPostCompleted(String result) {
        Toast.makeText(this, "Status:Ok", Toast.LENGTH_SHORT).show();
        Bundle args = new Bundle();
        args.putString(MainActivity.USER_ID, userId);
        loaderManager.restartLoader(PROFILE_LOADER_ID, args, this);
        showProgress();
    }

    @Override
    public void onPostError(String error) {
        Toast.makeText(this, "Status:Error", Toast.LENGTH_SHORT).show();
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
    }

    public void dismissProgress() {
        progressBar.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
    }

}
