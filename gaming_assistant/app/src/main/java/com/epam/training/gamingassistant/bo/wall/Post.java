package com.epam.training.gamingassistant.bo.wall;

import com.epam.training.gamingassistant.bo.attachments.Attachment;

import java.util.List;

public class Post {

    public Post(String id, String owner_id, String from_id, String date, String text, List<Attachment> attachments, List<Post> copy_history) {
        this.id = id;
        this.owner_id = owner_id;
        this.from_id = from_id;
        this.date = date;
        this.text = text;
        this.attachments = attachments;
        this.copy_history = copy_history;
    }

    public String getId() {
        return id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public String getFrom_id() {
        return from_id;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public List<Post> getCopy_history() {
        return copy_history;
    }

    private String id;
    private String owner_id;
    private String from_id;
    private String date;
    private String text;
    private List<Attachment> attachments;
    private List<Post> copy_history;
}
