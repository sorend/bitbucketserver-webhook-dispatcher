package com.github.sorend.bitbucketserver.webhook.eventpayload;

import com.github.sorend.bitbucketserver.webhook.eventpayload.requests.*;
import com.google.gson.Gson;

/**
 * Factory for loading event payloads based on event type
 */
public class EventPayloads {

    private Gson gson;

    public EventPayloads(Gson gson) {
        this.gson = gson;
    }

    public RepoRefsChanged repoRefsChanged(String json) {
        return parse(json, RepoRefsChanged.class);
    }

    public RepoModified repoModified(String json) {
        return parse(json, RepoModified.class);
    }

    public RepoForked repoForked(String json) {
        return parse(json, RepoForked.class);
    }

    public RepoCommented repoCommented(String json) {
        return parse(json, RepoCommented.class);
    }

    public MirrorRepoSynchronized mirrorRepoSynchronized(String json) {
        return parse(json, MirrorRepoSynchronized.class);
    }

    public PullRequestOpenClose pullRequestOpenClose(String json) {
        return parse(json, PullRequestOpenClose.class);
    }

    public PullRequestFromRefUpdated pullRequestFromRefUpdated(String json) {
        return parse(json, PullRequestFromRefUpdated.class);
    }

    public PullRequestModified pullRequestModified(String json) {
        return parse(json, PullRequestModified.class);
    }

    public PullRequestReviewerUpdated pullRequestReviewerUpdated(String json) {
        return parse(json, PullRequestReviewerUpdated.class);
    }

    public PullRequestReviewerFeedback pullRequestReviewerFeedback(String json) {
        return parse(json, PullRequestReviewerFeedback.class);
    }

    public PullRequestCommented pullRequestCommented(String json) {
        return parse(json, PullRequestCommented.class);
    }

    private <T> T parse(String json, Class<T> clz) {
        return gson.fromJson(json, clz);
    }
}
