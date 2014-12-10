package com.epam.training.gamingassistant.bo;


import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Friend extends JSONObjectWrapper {

    private static final String URL = "url";
    private static final String DISPLAY_NAME = "name";
    private static final String IMAGE = "image";
    private static final String ID = "id";


    public static final Parcelable.Creator<Friend> CREATOR
            = new Parcelable.Creator<Friend>() {
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

    public Friend(String jsonObject) {
        super(jsonObject);
    }

    public Friend(JSONObject jsonObject) {
        super(jsonObject);
    }

    protected Friend(Parcel in) {
        super(in);
    }

    public String getUrl() {
        return getString(URL);
    }

    public String getDisplayName() {
        return getString(DISPLAY_NAME);
    }

    public String getImage() {
        return getString(IMAGE);
    }

    public Long getId() {
        return getLong(ID);
    }

}

