package bitbucket.webhook.context;

import com.cdancy.bitbucket.rest.domain.activities.ActivitiesPage;
import com.cdancy.bitbucket.rest.domain.comment.Comments;
import com.cdancy.bitbucket.rest.domain.commit.CommitPage;
import com.cdancy.bitbucket.rest.domain.common.RequestStatus;
import com.cdancy.bitbucket.rest.domain.participants.Participants;
import com.cdancy.bitbucket.rest.domain.pullrequest.ChangePage;
import com.cdancy.bitbucket.rest.domain.pullrequest.MergeStatus;
import com.cdancy.bitbucket.rest.domain.pullrequest.PullRequest;
import com.cdancy.bitbucket.rest.features.CommentsApi;
import com.cdancy.bitbucket.rest.features.PullRequestApi;
import com.cdancy.bitbucket.rest.options.CreateComment;
import com.cdancy.bitbucket.rest.options.CreateParticipants;

public class ContextPullRequestApi {

    PullRequestApi api;
    CommentsApi commentsApi;

    String project;
    String repo;
    int pullRequestId;
    int version;

    public ContextPullRequestApi(PullRequestApi api, CommentsApi commentsApi, String project, String repo, int pullRequestId, int version) {
        this.api = api;
        this.commentsApi = commentsApi;
        this.project = project;
        this.repo = repo;
        this.pullRequestId = pullRequestId;
        this.version = version;
    }

    public Participants addParticipant(String userSlug, CreateParticipants createParticipants) {
        return api.addParticipant(project, repo, pullRequestId, userSlug, createParticipants);
    }

    public Participants assignParticipant(CreateParticipants createParticipants) {
        return api.assignParticipant(project, repo, pullRequestId, createParticipants);
    }

    public MergeStatus canMerge() {
        return api.canMerge(project, repo, pullRequestId);
    }

    public ChangePage changes(Boolean withComments, Integer limit, Integer start) {
        return api.changes(project, repo, pullRequestId, withComments, limit, start);
    }

    public CommitPage commits(Boolean withCounts, Integer limit, Integer start) {
        return api.commits(project, repo, pullRequestId, withCounts, limit, start);
    }

    public PullRequest decline() {
        return api.decline(project, repo, pullRequestId, version);
    }

    public RequestStatus delete() {
        return api.delete(project, repo, pullRequestId, version);
    }

    public RequestStatus deleteParticipant(String userSlug) {
        return api.deleteParticipant(project, repo, pullRequestId, userSlug);
    }

    public PullRequest get() {
        return api.get(project, repo, pullRequestId);
    }

    public ActivitiesPage listActivities(Integer limit, Integer start) {
        return api.listActivities(project, repo, pullRequestId, limit, start);
    }

    public PullRequest merge() {
        return api.merge(project, repo, pullRequestId, version);
    }

    public PullRequest reopen() {
        return api.reopen(project, repo, pullRequestId, version);
    }

    public Comments comment(String text) {
        return commentsApi.comment(project, repo, pullRequestId, text);
    }

    public Comments comment(CreateComment createComment) {
        return commentsApi.create(project, repo, pullRequestId, createComment);
    }
}
