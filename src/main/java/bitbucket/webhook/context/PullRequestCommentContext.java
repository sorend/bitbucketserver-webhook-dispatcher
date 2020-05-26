package bitbucket.webhook.context;

public interface PullRequestCommentContext extends PullRequestContext {

    int getCommentId();
    int getCommentVersion();

    default ContextCommentApi commentContext() {
        return new ContextCommentApi(api().commentsApi(), getProject(), getRepo(), getPullRequestId(), getCommentId(), getCommentVersion());
    }

}
