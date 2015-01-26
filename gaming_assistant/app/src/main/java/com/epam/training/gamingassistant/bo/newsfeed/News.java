package com.epam.training.gamingassistant.bo.newsfeed;

import com.epam.training.gamingassistant.bo.attachments.Attachment;
import com.epam.training.gamingassistant.bo.wall.Post;

import java.util.List;

/**
 * Created by NuclearOK on 23.01.2015.
 */
public class News {

    public News(String type, String final_post, String text, String can_edit, String can_delete, List<Attachment> attachments, List<Post> copy_history, String source_id, String date, String post_id, String post_type) {
        this.type = type;
        this.final_post = final_post;
        this.text = text;
        this.can_edit = can_edit;
        this.can_delete = can_delete;
        this.attachments = attachments;
        this.copy_history = copy_history;
        this.source_id = source_id;
        this.date = date;
        this.post_id = post_id;
        this.post_type = post_type;
    }

    private String type;
    private String final_post;
    private String text;
    private String can_edit;
    private String can_delete;
    private List<Attachment> attachments;
    private List<Post> copy_history;
    private String source_id;
    private String date;
    private String post_id;
    private String post_type;


    public List<Post> getCopy_history() {
        return copy_history;
    }

    public String getType() {
        return type;
    }

    public String getSource_id() {
        return source_id;
    }

    public String getDate() {
        return date;
    }

    public String getPost_id() {
        return post_id;
    }

    public String getPost_type() {
        return post_type;
    }

    public String getFinal_post() {
        return final_post;
    }

    public String getText() {
        return text;
    }

    public String getCan_edit() {
        return can_edit;
    }

    public String getCan_delete() {
        return can_delete;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

}
