package com.github.sorend.bitbucketserver.webhook.context;

import com.cdancy.bitbucket.rest.domain.comment.Comments;
import com.cdancy.bitbucket.rest.domain.common.RequestStatus;
import com.cdancy.bitbucket.rest.features.CommentsApi;

public class ContextCommentApi {

    private CommentsApi commentsApi;

    private String project;
    private String repo;
    private int pullRequestId;
    private int commentId;
    private int commentVersion;

    public ContextCommentApi(CommentsApi commentsApi, String project, String repo, int pullRequestId, int commentId, int commentVersion) {
        this.commentsApi = commentsApi;
        this.project = project;
        this.repo = repo;
        this.pullRequestId = pullRequestId;
        this.commentId = commentId;
        this.commentVersion = commentVersion;
    }

    public RequestStatus delete() {
        return commentsApi.delete(project, repo, pullRequestId, commentId, commentVersion);
    }

    public Comments get() {
        return commentsApi.get(project, repo, pullRequestId, commentId);
    }

}
