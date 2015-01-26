package com.epam.training.gamingassistant.bo.friends;


public class Friend {
    private String id;
    private String first_name;
    private String last_name;
    private String photo_100;


    public Friend(String id, String first_name, String last_name,String photo_100){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.photo_100 = photo_100;
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

    public String getPhoto_100() {
        return photo_100;
    }
}