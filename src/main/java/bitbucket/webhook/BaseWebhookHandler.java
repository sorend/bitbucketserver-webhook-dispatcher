package bitbucket.webhook;

import bitbucket.webhook.context.PullRequestCommentContext;
import bitbucket.webhook.context.PullRequestContext;
import bitbucket.webhook.context.RepoContext;
import com.github.sorend.bitbucketserver.webhook.eventpayload.requests.*;

public abstract class BaseWebhookHandler implements WebhookHandler {

    @Override
    public void onRepoRefsChanged(WebhookEvent<RepoRefsChanged, RepoContext> event) {

    }

    @Override
    public void onRepoModified(WebhookEvent<RepoModified, RepoContext> event) {

    }

    @Override
    public void onRepoForked(WebhookEvent<RepoForked, RepoContext> event) {

    }

    @Override
    public void onRepoCommentAdded(WebhookEvent<RepoCommented, RepoContext> event) {

    }

    @Override
    public void onRepoCommentEdited(WebhookEvent<RepoCommented, RepoContext> event) {

    }

    @Override
    public void onRepoCommentDeleted(WebhookEvent<RepoCommented, RepoContext> event) {

    }

    @Override
    public void onPullRequestOpened(WebhookEvent<PullRequestOpenClose, PullRequestContext> event) {

    }

    @Override
    public void onPullRequestModified(WebhookEvent<PullRequestModified, PullRequestContext> event) {

    }

    @Override
    public void onPullRequestCommentAdded(WebhookEvent<PullRequestCommented, PullRequestCommentContext> event) {

    }

    @Override
    public void onPullRequestCommentEdited(WebhookEvent<PullRequestCommented, PullRequestCommentContext> event) {

    }

    @Override
    public void onPullRequestCommentDeleted(WebhookEvent<PullRequestCommented, PullRequestCommentContext> event) {

    }

    @Override
    public void onPullRequestFromRefUpdated(WebhookEvent<PullRequestFromRefUpdated, PullRequestContext> event) {

    }

    @Override
    public void onPullRequestMerged(WebhookEvent<PullRequestOpenClose, PullRequestContext> event) {

    }

    @Override
    public void onPullRequestDeclined(WebhookEvent<PullRequestOpenClose, PullRequestContext> event) {

    }

    @Override
    public void onPullRequestDeleted(WebhookEvent<PullRequestOpenClose, PullRequestContext> event) {

    }

    @Override
    public void onPullRequestReviewerUpdated(WebhookEvent<PullRequestReviewerUpdated, PullRequestContext> event) {

    }

    @Override
    public void onPullRequestReviewerApproved(WebhookEvent<PullRequestReviewerFeedback, PullRequestContext> event) {

    }

    @Override
    public void onPullRequestReviewerUnapproved(WebhookEvent<PullRequestReviewerFeedback, PullRequestContext> event) {

    }

    @Override
    public void onPullRequestReviewerNeedsWork(WebhookEvent<PullRequestReviewerFeedback, PullRequestContext> event) {

    }
}
