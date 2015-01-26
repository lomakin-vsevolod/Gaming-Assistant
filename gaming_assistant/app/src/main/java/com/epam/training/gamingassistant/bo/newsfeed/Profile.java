package com.epam.training.gamingassistant.bo.newsfeed;

public class Profile {
    private String id;
    private String first_name;
    private String last_name;
    private String photo_50;

    public Profile(String id, String first_name, String last_name, String photo_50) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.photo_50 = photo_50;
    }

    public String getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhoto_50() {
        return photo_50;
    }


}
