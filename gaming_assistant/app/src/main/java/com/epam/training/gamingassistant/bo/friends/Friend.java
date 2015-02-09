package com.epam.training.gamingassistant.bo.friends;


import com.google.gson.annotations.SerializedName;

public class Friend {
    @SerializedName("id")
    private Long id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("photo_100")
    private String photo100;

    public Friend(Long id, String firstName, String lastName, String photo100) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo100 = photo100;
    }


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoto100() {
        return photo100;
    }
}
