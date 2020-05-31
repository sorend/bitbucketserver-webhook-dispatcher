package com.github.sorend.bitbucketserver.webhook.eventpayload.model;

import java.util.List;
import java.util.Map;

public class Actor {
    public String name;
    public String emailAddress;
    public int id;
    public String displayName;
    public boolean active;
    public String slug;
    public String type;
    public Map<String, List<Link>> links;
}
