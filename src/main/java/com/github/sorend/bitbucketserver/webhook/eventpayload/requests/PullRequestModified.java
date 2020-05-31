package com.github.sorend.bitbucketserver.webhook.eventpayload.requests;

import com.github.sorend.bitbucketserver.webhook.eventpayload.model.BasePullRequestEventPayload;
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.PreviousTarget;

public class PullRequestModified extends BasePullRequestEventPayload {
    public String previousTitle;
    public String previousDescription;
    public PreviousTarget previousTarget;
}
