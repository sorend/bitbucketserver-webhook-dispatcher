package com.github.sorend.bitbucketserver.webhook.eventpayload.requests;

import com.github.sorend.bitbucketserver.webhook.eventpayload.model.BaseRepoEventPayload;
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.Change;

import java.util.List;

public class RepoRefsChanged extends BaseRepoEventPayload {
    public List<Change> changes;
}
