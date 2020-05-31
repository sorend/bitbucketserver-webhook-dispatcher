package com.github.sorend.bitbucketserver.webhook.context;

public interface PullRequestContext extends RepoContext {

    int getPullRequestId();
    int getPullRequestVersion();

    default ContextPullRequestApi prContext() {
        return new ContextPullRequestApi(api().pullRequestApi(), api().commentsApi(), getProject(), getRepo(), getPullRequestId(), getPullRequestVersion());
    }

}
