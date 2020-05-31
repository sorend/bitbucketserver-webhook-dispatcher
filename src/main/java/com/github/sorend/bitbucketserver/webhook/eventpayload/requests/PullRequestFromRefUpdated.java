package com.github.sorend.bitbucketserver.webhook.eventpayload.requests;

import com.github.sorend.bitbucketserver.webhook.eventpayload.model.BasePullRequestEventPayload;

public class PullRequestFromRefUpdated extends BasePullRequestEventPayload {
    public String previousFromHash;
}
