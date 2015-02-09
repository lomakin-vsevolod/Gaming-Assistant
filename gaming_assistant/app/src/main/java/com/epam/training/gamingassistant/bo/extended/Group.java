package com.epam.training.gamingassistant.bo.extended;

import com.google.gson.annotations.SerializedName;

public class Group {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("screen_name")
    private String screenName;
    @SerializedName("photo_50")
    private String photo50;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getPhoto50() {
        return photo50;
    }

    public Group(Long id, String name, String screenName, String photo50) {
        this.id = id;
        this.name = name;
        this.screenName = screenName;
        this.photo50 = photo50;
    }
}
