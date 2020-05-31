package com.github.sorend.bitbucketserver.webhook.eventpayload.model;

import com.github.sorend.bitbucketserver.webhook.eventpayload.features.RepositoryAware;

public class BaseRepoEventPayload extends BaseEventPayload implements RepositoryAware {
    public Repository repository;

    @Override
    public Repository repository() {
        return repository;
    }
}
