package com.github.sorend.bitbucketserver.webhook.eventpayload.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Project {
    public String key;
    public int id;
    public String name;
    @SerializedName("public")
    public boolean public_;
    public String type;
    public Actor owner;
    public Map<String, List<Link>> links;
}
