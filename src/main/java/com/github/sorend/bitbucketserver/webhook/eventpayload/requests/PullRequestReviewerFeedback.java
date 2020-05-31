package com.github.sorend.bitbucketserver.webhook.eventpayload.requests;

import com.github.sorend.bitbucketserver.webhook.eventpayload.model.BasePullRequestEventPayload;
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.Participant;

public class PullRequestReviewerFeedback extends BasePullRequestEventPayload {
    public Participant participant;
    public String previousStatus;
}
