package com.epam.training.gamingassistant;

import android.app.Application;
import android.content.Context;

import com.epam.training.gamingassistant.source.HttpDataSource;

/**
 * Created by NuclearOK on 10.12.2014.
 */
public class CoreApplication extends Application {

    private HttpDataSource mHttpDataSource;


    @Override
    public void onCreate() {
        super.onCreate();
        mHttpDataSource = new HttpDataSource();


    }

    @Override
    public Object getSystemService(String name) {
        if (HttpDataSource.KEY.equals(name)) {
            //for android kitkat +
            if (mHttpDataSource == null) {
                mHttpDataSource = new HttpDataSource();
            }
            return mHttpDataSource;
        }


        return super.getSystemService(name);
    }

    public static <T> T get(Context context, String key) {
        if (context == null || key == null){
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