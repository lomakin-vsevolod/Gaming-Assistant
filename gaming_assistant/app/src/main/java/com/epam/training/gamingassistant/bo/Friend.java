package com.epam.training.gamingassistant.bo;


public class Friend {
    private String id;
    private String displayName;
    private String url;

    public Friend(String id, String displayName, String url){
        this.id = id;
        this.displayName=displayName;
        this.url=url;
    }


    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUrl() {
        return url;
    }
}
