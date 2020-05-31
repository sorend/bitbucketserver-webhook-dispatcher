package com.github.sorend.bitbucketserver.webhook.eventpayload.features;

import com.github.sorend.bitbucketserver.webhook.eventpayload.model.Comment;

public interface CommentAware {

    Comment comment();

    default int commentId() {
        return comment().id;
    }

    default int commentVersion() {
        return comment().version;
    }
}
