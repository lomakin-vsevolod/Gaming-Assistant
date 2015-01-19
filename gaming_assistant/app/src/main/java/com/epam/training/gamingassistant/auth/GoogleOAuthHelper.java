package com.epam.training.gamingassistant.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class GoogleOAuthHelper {
    private static String CLIENT_ID = "42965839176-fnbm1lbt42e2dofu5em2ui9otve1lfgl.apps.googleusercontent.com";
    private static String REDIRECT_URI = "http://localhost";
    private static String OAUTH_URL = "https://accounts.google.com/o/oauth2/auth";
    private static String OAUTH_SCOPE = "https://www.googleapis.com/auth/plus.login";
    public static String AUTORIZATION_URL = OAUTH_URL + "?redirect_uri=" + REDIRECT_URI + "&response_type=code&client_id=" + CLIENT_ID + "&scope=" + OAUTH_SCOPE;
    private static String GRANT_TYPE = "authorization_code";
    private static String TOKEN_URL = "https://accounts.google.com/o/oauth2/token";
    private static String CLIENT_SECRET = "UWbrpUz0TG9jtDoZUlTjvM6Z";
    private static String CODE;
    private static JSONObject temp;
    private boolean authComplete = false;
    private static Intent resultIntent = new Intent();
    private String authCode;
    private static Activity act;

    public static boolean proceedRedirectURL(Activity activity, String url) {
        act = activity;
        boolean authComplete = false;
        Intent resultIntent = new Intent();
        String authCode;
        if (url.contains("?code=") && authComplete != true) {
            Uri uri = Uri.parse(url);
            authCode = uri.getQueryParameter("code");
            CODE = authCode;
            Log.i("", "CODE : " + authCode);

            authComplete = true;

            new TokenGet().execute();


            return true;

        } else if (url.contains("error=access_denied")) {
            Log.i("", "ACCESS_DENIED_HERE");
            resultIntent.putExtra("token", "Acces Denied");
            authComplete = true;
            activity.setResult(Activity.RESULT_OK, resultIntent);
            activity.finish();

        }
        return false;
    }

    public static void sendResult() throws JSONException {


        resultIntent.putExtra("token", temp.getString("access_token"));
        resultIntent.putExtra("expires", temp.getString("expires_in"));
        resultIntent.putExtra("refresh", temp.getString("refresh_token"));
        act.setResult(Activity.RESULT_OK, resultIntent);
        act.finish();
    }

    private static class TokenGet extends AsyncTask<String, String, JSONObject> {

        String Code;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected JSONObject doInBackground(String... args) {
            GoogleOAuthGetAccessToken jParser = new GoogleOAuthGetAccessToken();
            JSONObject json = jParser.gettoken(TOKEN_URL, CODE, CLIENT_ID, CLIENT_SECRET, REDIRECT_URI, GRANT_TYPE);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {

            if (json != null) {
                try {
                    temp = json;
                    sendResult();

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                Log.d("Error", "NetworkError");

            }
        }
    }

}
