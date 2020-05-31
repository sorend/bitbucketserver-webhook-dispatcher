package com.github.sorend.bitbucketserver.webhook.eventpayload.requests;

import com.github.sorend.bitbucketserver.webhook.eventpayload.features.CommentAware;
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.BaseRepoEventPayload;
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.Comment;

public class RepoCommented extends BaseRepoEventPayload implements CommentAware {
    public Comment comment;
    public String commit;
    public String previousComment;

    @Override
    public Comment comment() {
        return comment;
    }
}
