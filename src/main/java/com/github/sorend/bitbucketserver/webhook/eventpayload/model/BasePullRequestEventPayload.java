package com.github.sorend.bitbucketserver.webhook.eventpayload.model;

import com.github.sorend.bitbucketserver.webhook.eventpayload.features.PullRequestAware;

public class BasePullRequestEventPayload extends BaseEventPayload implements PullRequestAware {
    public PullRequest pullRequest;

    @Override
    public PullRequest pullRequest() {
        return pullRequest;
    }
}
