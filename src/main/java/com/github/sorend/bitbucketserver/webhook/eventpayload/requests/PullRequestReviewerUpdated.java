package com.github.sorend.bitbucketserver.webhook.eventpayload.requests;

import com.github.sorend.bitbucketserver.webhook.eventpayload.model.Actor;
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.BasePullRequestEventPayload;

import java.util.List;

public class PullRequestReviewerUpdated extends BasePullRequestEventPayload {
    public List<Actor> addedReviewers;
    public List<Actor> removedReviewers;
}
