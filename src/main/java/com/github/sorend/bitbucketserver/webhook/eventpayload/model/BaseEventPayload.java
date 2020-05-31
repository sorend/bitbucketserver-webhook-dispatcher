package com.github.sorend.bitbucketserver.webhook.eventpayload.model;

import java.time.ZonedDateTime;

public class BaseEventPayload {
    public String eventKey;
    public ZonedDateTime date;
    public Actor actor;
}
