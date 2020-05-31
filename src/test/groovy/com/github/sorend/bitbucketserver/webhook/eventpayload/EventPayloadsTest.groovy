package com.github.sorend.bitbucketserver.webhook.eventpayload

import com.github.sorend.bitbucketserver.webhook.eventpayload.model.Comment
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.MirrorRepoSynchronized
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.PullRequestFromRefUpdated
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.PullRequestOpened
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.RepoCommentAdded
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.RepoCommentDeleted
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.RepoCommentEdited
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.RepoForked
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.RepoModified
import com.github.sorend.bitbucketserver.webhook.eventpayload.model.RepoRefsChanged
import com.google.gson.Gson
import spock.lang.Specification

class EventPayloadsTest extends Specification {

    Gson gson
    EventPayloads sut

    void setup() {
        gson = com.github.sorend.bitbucketserver.webhook.eventpayload.helper.GsonHelper.configure();
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
        def res = sut.repoCommentAdded(json)
        println "updatedDate = ${res.comment.createdDate} + ${res.comment.permittedOperations.editable} + ${res.comment.properties_}"
        then:
        res instanceof RepoCommentAdded
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
        def res = sut.repoCommentEdited(json)
        println "updatedDate = ${res.comment.createdDate} + ${res.comment.permittedOperations.editable} + ${res.previousComment}"
        then:
        res instanceof RepoCommentEdited
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
        def res = sut.repoCommentDeleted(json)
        println "updatedDate = ${res.comment.createdDate} + ${res.comment} + ${res}"
        then:
        res instanceof RepoCommentDeleted
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
        def res = sut.pullRequestOpened(json)
        then:
        res instanceof PullRequestOpened
        res.pullRequest
        res.pullRequest.toRef.repository.project.name
        res.pullRequest.fromRef.repository.project.name
        res.pullRequest.author.user.name
    }

    //
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



}
