package com.epam.training.gamingassistant.bo.newsfeed;

import android.provider.ContactsContract;

import com.epam.training.gamingassistant.bo.friends.Friend;

import java.util.List;

public class GetNewsFeedResponse {

    private List<News> items;
    private List<Profile> profiles;
    private List<Group> groups;
    private String next_from;


    public Profile getProfileInfoFromId(String id){
        if (profiles.size() != 0){
            for (int i=0;i<profiles.size();i++){
                if(profiles.get(i).getId().equals(id)) {
                    Profile profile;
                    return  profile = profiles.get(i);
                }
            }
        }
        return null;
    }

    public Group getGroupInfoFromId(String id){
        if (groups.size() != 0){
            for (int i=0;i<groups.size();i++){
                if(groups.get(i).getId().equals(id)){
                    Group group;
                    return group = groups.get(i);
                }
            }
        }
        return null;
    }

    public String getNext_from() {
        return next_from;
    }

    public List<News> getItems(){
        return items;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
