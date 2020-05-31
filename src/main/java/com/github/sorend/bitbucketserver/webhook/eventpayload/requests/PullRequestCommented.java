package com.github.sorend.bitbucketserver.webhook.eventpayload.requests;

import com.github.sorend.bitbucketserver.webhook.eventpayload.features.CommentAware;
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.BasePullRequestEventPayload;
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.Comment;

public class PullRequestCommented extends BasePullRequestEventPayload implements CommentAware {
    public Comment comment;
    public int commentParentId;
    public String previousComment;

    @Override
    public Comment comment() {
        return comment;
    }
}
