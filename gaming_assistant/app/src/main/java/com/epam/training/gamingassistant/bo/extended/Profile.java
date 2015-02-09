package com.epam.training.gamingassistant.bo.extended;

import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("id")
    private Long id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("photo_50")
    private String photo50;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoto50() {
        return photo50;
    }

    public Profile(Long id, String firstName, String lastName, String photo50) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo50 = photo50;
    }
}
