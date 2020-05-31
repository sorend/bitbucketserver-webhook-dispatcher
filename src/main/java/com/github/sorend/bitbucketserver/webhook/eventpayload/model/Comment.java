package com.github.sorend.bitbucketserver.webhook.eventpayload.model;

import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public class Comment {
    @SerializedName("properties")
    public Map<String, Object> properties_;
    public int id;
    public int version;
    public String text;
    public Actor author;
    public ZonedDateTime createdDate;
    public ZonedDateTime updatedDate;
    public List<Comment> comments;
    public List tasks;
    public PermittedOperations permittedOperations;
}
