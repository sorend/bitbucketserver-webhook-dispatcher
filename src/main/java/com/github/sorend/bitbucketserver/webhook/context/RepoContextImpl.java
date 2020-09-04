package com.github.sorend.bitbucketserver.webhook.context;

import com.cdancy.bitbucket.rest.BitbucketApi;

public class RepoContextImpl implements RepoContext {

    private BitbucketApi bitbucketApi;
    private String project;
    private String repo;

    public RepoContextImpl(BitbucketApi bitbucketApi, String project, String repo) {
        this.bitbucketApi = bitbucketApi;
        this.project = project;
        this.repo = repo;
    }

    public BitbucketApi api() {
        return bitbucketApi;
    }

    @Override
    public String getProject() {
        return project;
    }

    @Override
    public String getRepo() {
        return repo;
    }
}
