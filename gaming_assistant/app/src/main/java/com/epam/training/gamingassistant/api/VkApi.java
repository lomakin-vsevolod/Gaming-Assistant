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
        BufferedReader in = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            in = new BufferedReader
                    (new InputStreamReader(response.getEntity().getContent()));
            StringBuilder sb = new StringBuilder("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            return sb.toString();

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
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
            JSONObject jsonObject = new JSONObject(response).getJSONObject("response");
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
            JSONObject jsonObject = new JSONObject(response).getJSONObject("response");
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
            JSONObject jsonObject = new JSONObject(response).getJSONObject("response");
            GetWallResponse wallResponse = gson.fromJson(jsonObject.toString(), GetWallResponse.class);
            return wallResponse;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Void postOnWall(String token) {
        try {
            String response = doApiRequest(VkApiConstants.WALL_POST_URI + token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
