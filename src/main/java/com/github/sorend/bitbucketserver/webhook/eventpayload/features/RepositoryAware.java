package com.github.sorend.bitbucketserver.webhook.eventpayload.features;

import com.github.sorend.bitbucketserver.webhook.eventpayload.model.Repository;

public interface RepositoryAware {
    Repository repository();

    default String slug() {
        return repository().slug;
    }

    default String projectKey() {
        return repository().project.key;
    }
}
