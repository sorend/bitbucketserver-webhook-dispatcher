package com.github.sorend.bitbucketserver.webhook.eventpayload.model;

public class Participant {
    public Actor user;
    public String role;
    public boolean approved;
    public String status;
    public String lastReviewedCommit;
}
