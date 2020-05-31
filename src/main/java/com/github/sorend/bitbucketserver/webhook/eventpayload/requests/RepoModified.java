package com.github.sorend.bitbucketserver.webhook.eventpayload.requests;

import com.github.sorend.bitbucketserver.webhook.eventpayload.features.RepositoryAware;
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.BaseEventPayload;
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.Repository;
import com.google.gson.annotations.SerializedName;

public class RepoModified extends BaseEventPayload implements RepositoryAware {
    public Repository old;
    @SerializedName("new")
    public Repository new_;

    @Override
    public Repository repository() {
        return new_;
    }
}
