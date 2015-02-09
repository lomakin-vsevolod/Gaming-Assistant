package com.epam.training.gamingassistant.bo.wall;

import com.epam.training.gamingassistant.bo.attachments.Attachment;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {

    @SerializedName("id")
    private Long id;
    @SerializedName("owner_id")
    private Long ownerId;
    @SerializedName("from_id")
    private Long fromId;
    @SerializedName("date")
    private String date;
    @SerializedName("text")
    private String text;
    @SerializedName("attachments")
    private List<Attachment> attachments;
    @SerializedName("copy_history")
    private List<Post> copyHistory;

    public Long getId() {
        return id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Long getFromId() {
        return fromId;
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

    public List<Post> getCopyHistory() {
        return copyHistory;
    }

    public Post(Long id, Long ownerId, Long fromId, String date, String text, List<Attachment> attachments, List<Post> copyHistory) {
        this.id = id;
        this.ownerId = ownerId;
        this.fromId = fromId;
        this.date = date;
        this.text = text;
        this.attachments = attachments;
        this.copyHistory = copyHistory;
    }
}
