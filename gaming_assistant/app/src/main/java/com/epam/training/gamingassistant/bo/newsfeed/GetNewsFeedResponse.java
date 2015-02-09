package com.epam.training.gamingassistant.bo.newsfeed;

import com.epam.training.gamingassistant.bo.extended.Group;
import com.epam.training.gamingassistant.bo.extended.Profile;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetNewsFeedResponse {
    @SerializedName("items")
    private List<News> items;
    @SerializedName("profiles")
    private List<Profile> profiles;
    @SerializedName("groups")
    private List<Group> groups;
    @SerializedName("next_from")
    private String nextFrom;


    public Profile getProfileInfoFromId(Long id) {
        if (profiles.size() != 0) {
            for (int i = 0; i < profiles.size(); i++) {
                if (profiles.get(i).getId().equals(id)) {
                    Profile profile;
                    return profile = profiles.get(i);
                }
            }
        }
        return null;
    }

    public Group getGroupInfoFromId(Long id) {
        Long groupId = Math.abs(id);
        if (groups.size() != 0) {
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).getId().equals(groupId)) {
                    Group group;
                    return group = groups.get(i);
                }
            }
        }
        return null;
    }

    public String getNextFrom() {
        return nextFrom;
    }

    public List<News> getItems() {
        return items;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
