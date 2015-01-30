package com.epam.training.gamingassistant.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epam.training.gamingassistant.MainActivity;
import com.epam.training.gamingassistant.R;
import com.epam.training.gamingassistant.adapter.WallAdapter;
import com.epam.training.gamingassistant.bo.UserProfile;
import com.epam.training.gamingassistant.imageloader.ImageLoader;
import com.epam.training.gamingassistant.tasks.BitmapLoadTask;
import com.epam.training.gamingassistant.tasks.GetUserProfileTask;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ProfileFragment extends Fragment implements GetUserProfileTask.OnUserProfile {

    private String token;
    private String user_id;
    private ImageView userAvatarImageView;
    private TextView userNameTextView;
    private TextView userStatusTextView;
    private ListView wallListView;
    private WallAdapter wallAdapter;
    private Button sendButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        token = getArguments().getString(MainActivity.TOKEN);
        user_id = getArguments().getString(MainActivity.USER_ID);
        View rootView = inflater.inflate(R.layout.profile_fragment, container,
                false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userAvatarImageView = (ImageView) view.findViewById(R.id.user_avatar);
        userNameTextView = (TextView) view.findViewById(R.id.user_name);
        userStatusTextView = (TextView) view.findViewById(R.id.user_status);
        wallListView = (ListView) view.findViewById(R.id.wall_list);
        sendButton = (Button) view.findViewById(R.id.send_wall_post);
    }

    @Override
    public void onResume() {
        super.onResume();
        GetUserProfileTask getUserProfileTask = new GetUserProfileTask(token);
        getUserProfileTask.setListener(this);
        getUserProfileTask.execute();
    }

    @Override
    public void onCompleted(UserProfile userProfile) {

        BitmapLoadTask bitmapLoadTask = new BitmapLoadTask(userAvatarImageView);
        bitmapLoadTask.execute(userProfile.getGetUsersResponse().getResponse().get(0).getPhoto_100());
        userNameTextView.setText(userProfile.getGetUsersResponse().getResponse().get(0).getFirst_name() + " " + userProfile.getGetUsersResponse().getResponse().get(0).getLast_name());
        if (!userProfile.getGetUsersResponse().getResponse().get(0).getStatus().equals("")) {
            userStatusTextView.setText(userProfile.getGetUsersResponse().getResponse().get(0).getStatus());
        }
        wallAdapter = new WallAdapter(getActivity(), userProfile.getGetWallResponse());
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

    @Override
    public void onError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }


}