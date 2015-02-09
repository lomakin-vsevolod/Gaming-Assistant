package com.epam.training.gamingassistant.api;


import com.epam.training.gamingassistant.bo.friends.GetFriendsResponse;
import com.epam.training.gamingassistant.bo.newsfeed.GetNewsFeedResponse;
import com.epam.training.gamingassistant.bo.users.GetUsersResponse;
import com.epam.training.gamingassistant.bo.wall.GetWallResponse;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class VkApi {
    private static Gson gson = new Gson();

    private VkApi() {
    }

    private static String doApiRequest(String url) {
        BufferedReader bufferedReader = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet();
            httpGet.setURI(new URI(url));
            HttpResponse httpResponse = httpClient.execute(httpGet);
            bufferedReader = new BufferedReader
                    (new InputStreamReader(httpResponse.getEntity().getContent()));
            StringBuilder stringBuilder = new StringBuilder("");
            String line = "";
            String lineSeparator = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + lineSeparator);
            }
            return stringBuilder.toString();

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();

        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static GetFriendsResponse getFriendsList(String token) {
        try {
            String response = doApiRequest(VkApiConstants.FRIENDS_GET_URI + token);
            JSONObject jsonObject = new JSONObject();
            if (response != null) {
                jsonObject = new JSONObject(response).getJSONObject("response");
            }
            GetFriendsResponse getFriendsResponse = gson.fromJson(jsonObject.toString(), GetFriendsResponse.class);
            return getFriendsResponse;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static GetNewsFeedResponse getFeed(String token) {
        try {
            String response = doApiRequest(VkApiConstants.NEWS_FEED_GET_URI + token);
            JSONObject jsonObject = new JSONObject();
            if (response != null) {
                jsonObject = new JSONObject(response).getJSONObject("response");
            }
            GetNewsFeedResponse newsFeedResponse = gson.fromJson(jsonObject.toString(), GetNewsFeedResponse.class);
            return newsFeedResponse;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static GetUsersResponse getUsersResponse(String token) {
        String response = doApiRequest(VkApiConstants.USERS_GET_URI + token);
        GetUsersResponse usersResponse = gson.fromJson(response, GetUsersResponse.class);
        return usersResponse;
    }

    public static GetWallResponse getWallResponse(String token) {
        try {
            String response = doApiRequest(VkApiConstants.WALL_GET_URI + token);
            JSONObject jsonObject = new JSONObject();
            if (response != null) {
                jsonObject = new JSONObject(response).getJSONObject("response");
            }
            GetWallResponse wallResponse = gson.fromJson(jsonObject.toString(), GetWallResponse.class);
            return wallResponse;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String postOnWall(String token) {
        try {
            return doApiRequest(VkApiConstants.WALL_POST_URI + token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
