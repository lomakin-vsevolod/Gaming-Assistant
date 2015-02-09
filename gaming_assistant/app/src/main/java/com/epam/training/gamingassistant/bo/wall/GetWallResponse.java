package com.epam.training.gamingassistant.bo.wall;

import com.epam.training.gamingassistant.bo.extended.Group;
import com.epam.training.gamingassistant.bo.extended.Profile;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GetWallResponse {
    @SerializedName("items")
    private List<Post> items;
    @SerializedName("profiles")
    private List<Profile> profiles;
    @SerializedName("groups")
    private List<Group> groups;

    public List<Post> getItems() {
        return items;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public List<Group> getGroups() {
        return groups;
    }

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
}
