package com.github.sorend.bitbucketserver.webhook.context;

import com.cdancy.bitbucket.rest.BitbucketApi;

public interface EventContext {
    BitbucketApi api();
}
