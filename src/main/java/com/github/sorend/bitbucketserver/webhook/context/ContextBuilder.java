package com.github.sorend.bitbucketserver.webhook.context;

import com.cdancy.bitbucket.rest.BitbucketApi;
import com.github.sorend.bitbucketserver.webhook.eventpayload.features.CommentAware;
import com.github.sorend.bitbucketserver.webhook.eventpayload.features.PullRequestAware;
import com.github.sorend.bitbucketserver.webhook.eventpayload.features.RepositoryAware;

public class ContextBuilder {

    public static ContextBuilder create(BitbucketApi api) {
        return new ContextBuilder(api);
    }

    private BitbucketApi api;
    private String project;
    private String repo;
    private int pullRequestId;
    private int pullRequestVersion;
    private int commentId;
    private int commentVersion;

    private ContextBuilder(BitbucketApi api) {
        this.api = api;
    }

    public ContextBuilder fromRepo(RepositoryAware r) {
        project = r.projectKey();
        repo = r.slug();
        return this;
    }

    public ContextBuilder fromPR(PullRequestAware pr) {
        project = pr.projectKey();
        repo = pr.slug();
        pullRequestId = pr.pullRequestId();
        pullRequestVersion = pr.pullRequestVersion();
        return this;
    }

    public ContextBuilder fromComment(CommentAware c) {
        commentId = c.commentId();
        commentVersion = c.commentVersion();
        return this;
    }

    public RepoContext buildRepo() {
        return new RepoContextImpl(api, project, repo);
    }

    public PullRequestContext buildPR() {
        return new PullRequestContextImpl(api, project, repo, pullRequestId, pullRequestVersion);
    }

    public PullRequestCommentContext buildPRC() {
        return new PullRequestCommentContextImpl(api, project, repo, pullRequestId, pullRequestVersion, commentId, commentVersion);
    }
}

