package com.epam.training.gamingassistant.bo.friends;


import com.google.gson.annotations.SerializedName;

public class Friend {
    private String id;
    @SerializedName("first_name")
    private String firstName;
    private String last_name;
    private String photo_100;


    public Friend(String id, String first_name, String last_name, String photo_100) {
        this.id = id;
        this.firstName = first_name;
        this.last_name = last_name;
        this.photo_100 = photo_100;
    }


    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhoto_100() {
        return photo_100;
    }
}
