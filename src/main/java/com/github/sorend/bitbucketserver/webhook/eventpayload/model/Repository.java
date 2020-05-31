package com.github.sorend.bitbucketserver.webhook.eventpayload.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Repository {
    public String slug;
    public int id;
    public String name;
    public String scmId;
    public String state;
    public String statusMessage;
    public boolean forkable;
    public Project project;
    @SerializedName("public")
    public boolean public_;
    // used in forking events
    public Project origin;
    // used in mirrorsync
    public Map<String, List<Link>> links;
}
