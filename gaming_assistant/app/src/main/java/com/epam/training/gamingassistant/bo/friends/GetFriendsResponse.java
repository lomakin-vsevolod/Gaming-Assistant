package com.epam.training.gamingassistant.bo.friends;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFriendsResponse {
    @SerializedName("count")
    private int count;
    @SerializedName("items")
    private List<Friend> items;

    public List<Friend> getItems() {
        return items;
    }

    public int getCount() {
        return count;
    }
}
