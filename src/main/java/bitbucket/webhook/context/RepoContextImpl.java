package bitbucket.webhook.context;

import com.cdancy.bitbucket.rest.BitbucketApi;

import java.util.List;
import java.util.Map;

public class RepoContextImpl implements RepoContext {

    private BitbucketApi bitbucketApi;
    private Map<String, List<String>> params;
    private String project;
    private String repo;

    public RepoContextImpl(BitbucketApi bitbucketApi, Map<String, List<String>> params, String project, String repo) {
        this.bitbucketApi = bitbucketApi;
        this.params = params;
        this.project = project;
        this.repo = repo;
    }

    public BitbucketApi api() {
        return bitbucketApi;
    }

    public Map<String, List<String>> params() {
        return params;
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
