package com.github.sorend.bitbucketserver.webhook.eventpayload.model;

public class Change {
    public Ref ref;
    public String refId;
    public String fromHash;
    public String toHash;
    public String type;
}
