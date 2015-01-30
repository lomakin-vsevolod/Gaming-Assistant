package com.epam.training.gamingassistant.bo.wall;

import com.epam.training.gamingassistant.bo.extended.Group;
import com.epam.training.gamingassistant.bo.extended.Profile;

import java.util.List;


public class GetWallResponse {
    private List<Post> items;
    private List<Profile> profiles;
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

    public Profile getProfileInfoFromId(String id) {
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

    public Group getGroupInfoFromId(String id) {
        if (groups.size() != 0) {
            for (int i = 0; i < groups.size(); i++) {
                if (groups.get(i).getId().equals(id)) {
                    Group group;
                    return group = groups.get(i);
                }
            }
        }
        return null;
    }
}
