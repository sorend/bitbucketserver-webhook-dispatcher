package com.github.sorend.bitbucketserver.webhook.eventpayload

import com.github.sorend.bitbucketserver.webhook.eventpayload.helper.GsonHelper
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.Comment
import com.github.sorend.bitbucketserver.webhook.eventpayload.requests.*
import com.google.gson.Gson
import spock.lang.Specification
import spock.lang.Unroll

class EventPayloadsTest extends Specification {

    Gson gson
    EventPayloads sut

    void setup() {
        gson = GsonHelper.configure();
        sut = new EventPayloads(gson)
    }

    def "parse repo:refs_changed example"() {
        given:
        String json = EventPayloadsTest.getResource("/repo-refs-changed.json").text
        println json
        when:
        def res = sut.repoRefsChanged(json)
        then:
        res instanceof RepoRefsChanged
    }

    def "parse repo:modified example"() {
        given:
        String json = EventPayloadsTest.getResource("/repo-modified.json").text
        println json
        when:
        def res = sut.repoModified(json)
        then:
        res instanceof RepoModified
        res.new_
        res.old
    }

    def "parse repo:forked example"() {
        given:
        String json = EventPayloadsTest.getResource("/repo-forked.json").text
        println json
        when:
        def res = sut.repoForked(json)
        then:
        res instanceof RepoForked
        res.repository.origin
        res.actor
    }

    def "parse repo:comment:added example"() {
        given:
        String json = EventPayloadsTest.getResource("/repo-comment-added.json").text
        println json
        when:
        def res = sut.repoCommented(json)
        println "updatedDate = ${res.comment.createdDate} + ${res.comment.permittedOperations.editable} + ${res.comment.properties_}"
        then:
        res instanceof RepoCommented
        res.actor
        res.repository
        res.comment
        res.commit
    }

    def "parse repo:comment:edited example"() {
        given:
        String json = EventPayloadsTest.getResource("/repo-comment-edited.json").text
        println json
        when:
        def res = sut.repoCommented(json)
        println "updatedDate = ${res.comment.createdDate} + ${res.comment.permittedOperations.editable} + ${res.previousComment}"
        then:
        res instanceof RepoCommented
        res.actor
        res.repository
        res.comment
        res.previousComment
        res.commit
    }

    //
    def "parse repo:comment:deleted example"() {
        given:
        String json = EventPayloadsTest.getResource("/repo-comment-deleted.json").text
        println json
        when:
        def res = sut.repoCommented(json)
        println "updatedDate = ${res.comment.createdDate} + ${res.comment} + ${res}"
        then:
        res instanceof RepoCommented
        res.actor
        res.repository
        res.comment instanceof Comment
        res.commit
    }

    //
    def "parse mirror:repo_synchronized example"() {
        given:
        String json = EventPayloadsTest.getResource("/mirror-repo-synchronized.json").text
        println json
        when:
        def res = sut.mirrorRepoSynchronized(json)
        then:
        res instanceof MirrorRepoSynchronized
        res.repository
        res.repository.links.size() == 2
        res.repository.links.any { it.key == "clone" && it.value.size() == 2 }
        res.repository.links.any { it.key == "self" && it.value.size() == 1 }
        res.changes.size() == 1
        res.mirrorServer.id
    }

    //
    def "parse pr:opened example"() {
        given:
        String json = EventPayloadsTest.getResource("/pr-opened.json").text
        println json
        when:
        def res = sut.pullRequestOpenClose(json)
        then:
        res instanceof PullRequestOpenClose
        res.pullRequest
        res.pullRequest.toRef.repository.project.name
        res.pullRequest.fromRef.repository.project.name
        res.pullRequest.author.user.name
    }

    def "parse pr:from_ref_updated example"() {
        given:
        String json = EventPayloadsTest.getResource("/pr-from-ref-updated.json").text
        println json
        when:
        def res = sut.pullRequestFromRefUpdated(json)
        then:
        res instanceof PullRequestFromRefUpdated
        res.pullRequest
        res.pullRequest.toRef.repository.project.name
        res.pullRequest.fromRef.repository.project.name
        res.pullRequest.author.user.name
        res.previousFromHash
    }

    def "parse pr:modified example"() {
        given:
        String json = EventPayloadsTest.getResource("/pr-modified.json").text
        println json
        when:
        def res = sut.pullRequestModified(json)
        then:
        res instanceof PullRequestModified
        res.pullRequest
        res.previousDescription
        res.previousTarget
        res.previousTarget.latestCommit
        res.pullRequest.reviewers.size() == 1
        res.pullRequest.reviewers.any { it.user.id == 36303 }
    }

    def "parse pr:reviewer:updated example"() {
        given:
        String json = EventPayloadsTest.getResource("/pr-reviewer-updated.json").text
        println json
        when:
        def res = sut.pullRequestReviewerUpdated(json)
        then:
        res instanceof PullRequestReviewerUpdated
        res.pullRequest
        res.addedReviewers.size() == 1
        res.addedReviewers[0].emailAddress == "user2@atlassian.com"
        res.removedReviewers.size() == 1
        res.removedReviewers[0].slug == "user"
    }

    @Unroll
    def "parse pr:reviewer:approved/unapproved/needs_work example #r"() {
        given:
        String json = EventPayloadsTest.getResource(fn).text
        println json
        when:
        def res = sut.pullRequestReviewerFeedback(json)
        then:
        res instanceof PullRequestReviewerFeedback
        res.pullRequest
        res.previousStatus
        res.participant
        res.participant.user.displayName == "User"
        res.participant.lastReviewedCommit == "ef8755f06ee4b28c96a847a95cb8ec8ed6ddd1ca"
        res.participant.status == r
        where:
        fn                             || r
        "/pr-reviewer-approved.json"   || "APPROVED"
        "/pr-reviewer-unapproved.json" || "UNAPPROVED"
        "/pr-reviewer-needs_work.json" || "NEEDS_WORK"
    }

    def "parse pr:merged example"() {
        given:
        String json = EventPayloadsTest.getResource("/pr-merged.json").text
        println json
        when:
        def res = sut.pullRequestOpenClose(json)
        then:
        res instanceof PullRequestOpenClose
        res.pullRequest
        res.pullRequest.participants.size() == 1
        res.pullRequest.participants[0].role == "PARTICIPANT"
        !res.pullRequest.participants[0].lastReviewedCommit
        res.pullRequest.properties_.mergeCommit.displayId
    }

    @Unroll
    def "parse pr:declined/deleted example #fn"() {
        given:
        String json = EventPayloadsTest.getResource(fn).text
        println json
        when:
        def res = sut.pullRequestOpenClose(json)
        then:
        res instanceof PullRequestOpenClose
        res.pullRequest
        res.eventKey == e
        where:
        fn                  || e
        "/pr-declined.json" || "pr:declined"
        "/pr-deleted.json"  || "pr:deleted"
    }

    @Unroll
    def "parse pr:comment:added #fn example"() {
        given:
        String json = EventPayloadsTest.getResource(fn).text
        println json
        when:
        def res = sut.pullRequestCommented(json)
        then:
        res instanceof PullRequestCommented
        res.pullRequest
        res.comment.text
        res.commentParentId == 43
        res.previousComment == pc
        res.eventKey == e
        where:
        fn                         | e                    | pc
        "/pr-comment-added.json"   | "pr:comment:added"   | null
        "/pr-comment-edited.json"  | "pr:comment:edited"  | "I am a PR comment"
        "/pr-comment-deleted.json" | "pr:comment:deleted" | null
    }

}
