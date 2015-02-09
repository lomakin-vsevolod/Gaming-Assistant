package com.epam.training.gamingassistant.bo.users;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUsersResponse {
    @SerializedName("response")
    private List<User> response;

    public List<User> getResponse() {
        return response;
    }

}
