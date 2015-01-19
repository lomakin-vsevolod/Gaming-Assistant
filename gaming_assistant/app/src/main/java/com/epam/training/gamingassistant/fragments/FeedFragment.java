package com.epam.training.gamingassistant.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epam.training.gamingassistant.R;

/**
 * Created by NuclearOK on 14.01.2015.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FeedFragment extends Fragment {

    public FeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.screen_three, container,
                false);

        return rootView;
    }

}