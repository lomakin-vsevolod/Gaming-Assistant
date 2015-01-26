package com.epam.training.gamingassistant.bo.newsfeed;

public class Group {
    private String id;
    private String name;
    private String screen_name;
    private String photo_50;

    public Group(String id, String name, String screen_name, String photo_50) {
        this.id = id;
        this.name = name;
        this.screen_name = screen_name;
        this.photo_50 = photo_50;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getPhoto_50() {
        return photo_50;
    }

}
