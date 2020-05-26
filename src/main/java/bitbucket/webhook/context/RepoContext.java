package bitbucket.webhook.context;

import bitbucket.webhook.context.EventContext;
import com.cdancy.bitbucket.rest.domain.repository.Repository;

public interface RepoContext extends EventContext {

    String getProject();
    String getRepo();

    default Repository repoGet() {
        return api().repositoryApi().get(getProject(), getRepo());
    }
}
