package com.epam.training.gamingassistant.bo;


import com.epam.training.gamingassistant.bo.users.GetUsersResponse;
import com.epam.training.gamingassistant.bo.wall.GetWallResponse;

public class UserProfile {


    public UserProfile(GetUsersResponse getUsersResponse, GetWallResponse getWallResponse) {
        this.getUsersResponse = getUsersResponse;
        this.getWallResponse = getWallResponse;
    }

    private GetWallResponse getWallResponse;
    private GetUsersResponse getUsersResponse;

    public GetWallResponse getGetWallResponse() {
        return getWallResponse;
    }

    public GetUsersResponse getGetUsersResponse() {
        return getUsersResponse;
    }


}
