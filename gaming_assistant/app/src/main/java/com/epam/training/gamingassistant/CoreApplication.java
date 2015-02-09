package com.epam.training.gamingassistant;

import android.app.Application;
import android.content.Context;

import com.epam.training.gamingassistant.db.FriendsDbHelper;


public class CoreApplication extends Application {
    private FriendsDbHelper friendsDbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        friendsDbHelper = new FriendsDbHelper(this);
    }

    @Override
    public Object getSystemService(String name) {
        if (FriendsDbHelper.KEY.equals(name)) {

            if (friendsDbHelper == null) {
                friendsDbHelper = new FriendsDbHelper(this);
            }
            return friendsDbHelper;
        }
        return super.getSystemService(name);
    }

    public static <T> T get(Context context, String key) {
        if (context == null || key == null) {
            throw new IllegalArgumentException("Context and key must not be null");
        }
        T systemService = (T) context.getSystemService(key);
        if (systemService == null) {
            context = context.getApplicationContext();
            systemService = (T) context.getSystemService(key);
        }
        if (systemService == null) {
            throw new IllegalStateException(key + " not available");
        }
        return systemService;
    }


}
