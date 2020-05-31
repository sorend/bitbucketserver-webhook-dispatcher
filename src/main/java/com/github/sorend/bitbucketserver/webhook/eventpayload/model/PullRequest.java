package com.github.sorend.bitbucketserver.webhook.eventpayload.model;

import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public class PullRequest {
    public int id;
    public int version;
    public String title;
    public String state;
    public boolean open;
    public boolean closed;
    public ZonedDateTime createdDate;
    public ZonedDateTime updatedDate;
    public PullRequestRef fromRef;
    public PullRequestRef toRef;
    public boolean locked;
    public Participant author;
    public List<Participant> reviewers;
    public List<Participant> participants;
    public Map<String, List<Link>> links;
    @SerializedName("properties")
    public Map<String, Object> properties_;
}
