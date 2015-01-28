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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epam.training.gamingassistant.MainActivity;
import com.epam.training.gamingassistant.R;
import com.epam.training.gamingassistant.adapter.WallAdapter;
import com.epam.training.gamingassistant.api.VkApi;
import com.epam.training.gamingassistant.api.VkApiConstants;
import com.epam.training.gamingassistant.bo.UserProfile;
import com.epam.training.gamingassistant.bo.users.GetUsersResponse;
import com.epam.training.gamingassistant.tasks.BitmapLoadTask;
import com.epam.training.gamingassistant.tasks.GetUserProfileTask;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ProfileFragment extends Fragment implements GetUserProfileTask.OnUserProfile {

    private String token;
    private ImageView userAvatarImageView;
    private TextView userNameTextView;
    private TextView userNetworkStatusTextView;
    private ListView wallListView;
    private WallAdapter wallAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        token = getArguments().getString(MainActivity.TOKEN);
        View rootView = inflater.inflate(R.layout.profile_fragment, container,
                false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userAvatarImageView = (ImageView) view.findViewById(R.id.user_avatar);
        userNameTextView = (TextView) view.findViewById(R.id.user_name);
        userNetworkStatusTextView = (TextView) view.findViewById(R.id.user_network_status);
        wallListView = (ListView) view.findViewById(R.id.wall_list);
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

        BitmapLoadTask bitmapLoadTask =  new BitmapLoadTask(userAvatarImageView);
        bitmapLoadTask.execute(userProfile.getGetUsersResponse().getResponse().get(0).getPhoto_100());
        userNameTextView.setText(userProfile.getGetUsersResponse().getResponse().get(0).getFirst_name() + " " + userProfile.getGetUsersResponse().getResponse().get(0).getLast_name());
        if (userProfile.getGetUsersResponse().getResponse().get(0).getOnline() == 1){
            userNetworkStatusTextView.setText("Online");
        } else {
            userNetworkStatusTextView.setText("Offline");
        }
        wallAdapter = new WallAdapter(getActivity(), userProfile.getGetWallResponse());
        wallListView.setAdapter(wallAdapter);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }



}