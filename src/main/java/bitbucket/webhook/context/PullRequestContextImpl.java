package bitbucket.webhook.context;

import com.cdancy.bitbucket.rest.BitbucketApi;

import java.util.List;
import java.util.Map;

public class PullRequestContextImpl extends RepoContextImpl implements PullRequestContext {

    private int pullRequestId;
    private int pullRequestVersion;

    public PullRequestContextImpl(BitbucketApi bitbucketApi, Map<String, List<String>> params, String project, String repo, int pullRequestId, int pullRequestVersion) {
        super(bitbucketApi, params, project, repo);
        this.pullRequestId = pullRequestId;
        this.pullRequestVersion = pullRequestVersion;
    }

    @Override
    public int getPullRequestId() {
        return pullRequestId;
    }

    @Override
    public int getPullRequestVersion() {
        return pullRequestVersion;
    }
}
