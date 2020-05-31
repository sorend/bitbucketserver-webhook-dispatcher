package com.github.sorend.bitbucketserver.webhook.eventpayload.features;

import com.github.sorend.bitbucketserver.webhook.eventpayload.model.PullRequest;
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.Repository;

public interface PullRequestAware extends RepositoryAware {
    PullRequest pullRequest();

    default int pullRequestId() {
        return pullRequest().id;
    }

    default int pullRequestVersion() {
        return pullRequest().version;
    }

    default String commit() { return pullRequest().fromRef.latestCommit; }

    default Repository repository() {
        return pullRequest().toRef.repository;
    }

}
