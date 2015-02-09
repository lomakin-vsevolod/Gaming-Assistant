package com.epam.training.gamingassistant.bo.newsfeed;

import com.epam.training.gamingassistant.bo.attachments.Attachment;
import com.epam.training.gamingassistant.bo.wall.Post;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {

    public News(String type, String finalPost, String text, String canEdit, String canDelete, List<Attachment> attachments, List<Post> copyHistory, Long sourceId, String date, String postId, String postType) {
        this.type = type;
        this.finalPost = finalPost;
        this.text = text;
        this.canEdit = canEdit;
        this.canDelete = canDelete;
        this.attachments = attachments;
        this.copyHistory = copyHistory;
        this.sourceId = sourceId;
        this.date = date;
        this.postId = postId;
        this.postType = postType;
    }

    @SerializedName("type")
    private String type;
    @SerializedName("final_post")
    private String finalPost;
    @SerializedName("text")
    private String text;
    @SerializedName("can_edit")
    private String canEdit;
    @SerializedName("can_delete")
    private String canDelete;
    @SerializedName("attachments")
    private List<Attachment> attachments;
    @SerializedName("copy_history")
    private List<Post> copyHistory;
    @SerializedName("source_id")
    private Long sourceId;
    @SerializedName("date")
    private String date;
    @SerializedName("post_id")
    private String postId;
    @SerializedName("post_type")
    private String postType;

    public String getType() {
        return type;
    }

    public String getFinalPost() {
        return finalPost;
    }

    public String getText() {
        return text;
    }

    public String getCanEdit() {
        return canEdit;
    }

    public String getCanDelete() {
        return canDelete;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public List<Post> getCopyHistory() {
        return copyHistory;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public String getDate() {
        return date;
    }

    public String getPostId() {
        return postId;
    }

    public String getPostType() {
        return postType;
    }
}
