package com.epam.training.gamingassistant.auth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.epam.training.gamingassistant.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by NuclearOK on 20.01.2015.
 */
public class VkOAuthHelper {

    private static final String OAUTH_URL = "https://oauth.vk.com/authorize";
    private static final String APP_ID = "4740668";
    private static final String PERMISSIONS = "friends,video,offline,wall,photos,status";
    private static final String REDIRECT_URI = "https://oauth.vk.com/blank.html";
    private static final String DISPLAY = "touch";
    private static final String API_VERSION = "5.27";
    private static final String RESPONSE_TYPE = "token";
    public static final String AUTHORIZATION_URI = OAUTH_URL + "?client_id=" + APP_ID + "&scope=" + PERMISSIONS + "&redirect_uri=" + REDIRECT_URI + "&display=" + DISPLAY + "&v=" + API_VERSION + "&response_type=" + RESPONSE_TYPE;

    public static boolean proceedRedirectURL(Activity activity, String url) {
        if (url.startsWith(REDIRECT_URI)) {
            Uri uri = Uri.parse(url);
            String fragment = uri.getFragment();
            if (fragment != null)
             {
                uri = Uri.parse("?"+fragment);
                String authToken = uri.getQueryParameter("access_token");
                String authUserId= uri.getQueryParameter("user_id");
                Intent intent = new Intent();
                intent.putExtra(MainActivity.TOKEN, authToken);
                intent.putExtra(MainActivity.USER_ID, authUserId);
                activity.setResult(Activity.RESULT_OK, intent);
                activity.finish();
                return true;
            } else  {
                Log.d("", "ACCESS_DENIED_HERE");
                String errorDescription = uri.getQueryParameter("error_description");
                Intent intent = new Intent();
                intent.putExtra(MainActivity.ERROR, errorDescription);
                activity.setResult(Activity.RESULT_CANCELED, intent);
                activity.finish();
            }
            return false;
        }
        return false;

    }
}