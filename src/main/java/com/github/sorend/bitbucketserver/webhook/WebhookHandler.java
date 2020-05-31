package com.github.sorend.bitbucketserver.webhook;

import com.github.sorend.bitbucketserver.webhook.context.PullRequestCommentContext;
import com.github.sorend.bitbucketserver.webhook.context.PullRequestContext;
import com.github.sorend.bitbucketserver.webhook.context.RepoContext;
import com.github.sorend.bitbucketserver.webhook.eventpayload.requests.*;

public interface WebhookHandler {

    void onRepoRefsChanged(WebhookEvent<RepoRefsChanged, RepoContext> event);

    void onRepoModified(WebhookEvent<RepoModified, RepoContext> event);

    void onRepoForked(WebhookEvent<RepoForked, RepoContext> event);

    void onRepoCommentAdded(WebhookEvent<RepoCommented, RepoContext> event);

    void onRepoCommentEdited(WebhookEvent<RepoCommented, RepoContext> event);

    void onRepoCommentDeleted(WebhookEvent<RepoCommented, RepoContext> event);

    void onPullRequestOpened(WebhookEvent<PullRequestOpenClose, PullRequestContext> event);

    void onPullRequestModified(WebhookEvent<PullRequestModified, PullRequestContext> event);

    void onPullRequestCommentAdded(WebhookEvent<PullRequestCommented, PullRequestCommentContext> event);

    void onPullRequestCommentEdited(WebhookEvent<PullRequestCommented, PullRequestCommentContext> event);

    void onPullRequestCommentDeleted(WebhookEvent<PullRequestCommented, PullRequestCommentContext> event);

    void onPullRequestFromRefUpdated(WebhookEvent<PullRequestFromRefUpdated, PullRequestContext> event);

    void onPullRequestMerged(WebhookEvent<PullRequestOpenClose, PullRequestContext> event);

    void onPullRequestDeclined(WebhookEvent<PullRequestOpenClose, PullRequestContext> event);

    void onPullRequestDeleted(WebhookEvent<PullRequestOpenClose, PullRequestContext> event);

    void onPullRequestReviewerUpdated(WebhookEvent<PullRequestReviewerUpdated, PullRequestContext> event);

    void onPullRequestReviewerApproved(WebhookEvent<PullRequestReviewerFeedback, PullRequestContext> event);

    void onPullRequestReviewerUnapproved(WebhookEvent<PullRequestReviewerFeedback, PullRequestContext> event);

    void onPullRequestReviewerNeedsWork(WebhookEvent<PullRequestReviewerFeedback, PullRequestContext> event);
}
