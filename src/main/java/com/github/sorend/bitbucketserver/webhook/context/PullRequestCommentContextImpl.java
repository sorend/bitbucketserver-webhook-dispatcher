package com.github.sorend.bitbucketserver.webhook.context;

import com.cdancy.bitbucket.rest.BitbucketApi;

import java.util.List;
import java.util.Map;

public class PullRequestCommentContextImpl extends PullRequestContextImpl implements PullRequestCommentContext {

    private int commentId;
    private int commentVersion;

    public PullRequestCommentContextImpl(BitbucketApi bitbucketApi, Map<String, List<String>> params, String project, String repo, int pullRequestId, int pullRequestVersion, int commentId, int commentVersion) {
        super(bitbucketApi, params, project, repo, pullRequestId, pullRequestVersion);
        this.commentId = commentId;
        this.commentVersion = commentVersion;
    }

    @Override
    public int getCommentId() {
        return commentId;
    }

    @Override
    public int getCommentVersion() {
        return commentVersion;
    }
}
