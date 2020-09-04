package com.github.sorend.bitbucketserver.webhook.context;

import com.cdancy.bitbucket.rest.BitbucketApi;

public class PullRequestContextImpl extends RepoContextImpl implements PullRequestContext {

    private int pullRequestId;
    private int pullRequestVersion;

    public PullRequestContextImpl(BitbucketApi bitbucketApi, String project, String repo, int pullRequestId, int pullRequestVersion) {
        super(bitbucketApi, project, repo);
        this.pullRequestId = pullRequestId;
        this.pullRequestVersion = pullRequestVersion;
    }

    @Override
    public int getPullRequestId() {
        return pullRequestId;
    }

    @Override
    public int getPullRequestVersion() {
        return pullRequestVersion;
    }
}
