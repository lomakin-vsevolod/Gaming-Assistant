package com.epam.training.gamingassistant.bo.friends;

import com.epam.training.gamingassistant.bo.friends.Friend;

import java.util.List;

public class GetFriendsResponse {

    private int count;
    private List<Friend> items;

    public List<Friend> getItems(){
        return items;
    }
    public int getCount(){
        return count;
    }
}
