package com.github.sorend.bitbucketserver.webhook.eventpayload.requests;

import com.github.sorend.bitbucketserver.webhook.eventpayload.features.RepositoryAware;
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.Change;
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.MirrorServer;
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.Repository;

import java.time.ZonedDateTime;
import java.util.List;

public class MirrorRepoSynchronized implements RepositoryAware {
    public String eventKey;
    public ZonedDateTime date;
    public MirrorServer mirrorServer;
    public String syncType;
    public boolean refLimitExceeded;
    public Repository repository;
    public List<Change> changes;

    @Override
    public Repository repository() {
        return repository;
    }
}
