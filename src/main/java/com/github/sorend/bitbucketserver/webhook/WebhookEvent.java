package com.github.sorend.bitbucketserver.webhook;

public class WebhookEvent<P, C> {
    public final P payload;
    public final C context;
    public WebhookEvent(P payload, C context) {
        this.payload = payload;
        this.context = context;
    }
}
