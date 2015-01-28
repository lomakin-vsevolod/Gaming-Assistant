package com.epam.training.gamingassistant.fragments;

import android.annotation.TargetApi;
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
import com.epam.training.gamingassistant.adapter.FriendsAdapter;
import com.epam.training.gamingassistant.bo.friends.GetFriendsResponse;
import com.epam.training.gamingassistant.tasks.GetFriendListTask;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FriendListFragment extends Fragment implements GetFriendListTask.OnFriendListResponse {
    private String token;
    private ListView listView;
    private FriendsAdapter friendsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        token = getArguments().getString(MainActivity.TOKEN);
        View rootView = inflater.inflate(R.layout.friend_list_fragment, container,
                false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.friend_list);
    }

    @Override
    public void onResume() {
        super.onResume();
        GetFriendListTask getFriendListTask = new GetFriendListTask(token);
        getFriendListTask.setListener(this);
        getFriendListTask.execute();
    }

    @Override
    public void onCompleted(GetFriendsResponse getFriendsResponse) {
        friendsAdapter = new FriendsAdapter(getActivity(), getFriendsResponse.getItems());
        listView.setAdapter(friendsAdapter);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }


}