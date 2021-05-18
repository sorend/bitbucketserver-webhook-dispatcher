package com.github.sorend.bitbucketserver.webhook

import com.cdancy.bitbucket.rest.BitbucketApi
import com.github.sorend.bitbucketserver.webhook.eventpayload.EventPayloads
import spock.lang.Specification

import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService

class WebhookDispatcherTest extends Specification {

    ExecutorService executorService
    WebhookHandler webhookHandler
    BitbucketApi bitbucketApi
    WebhookDispatcher sut

    void setup() {
        bitbucketApi = Mock()
        webhookHandler = Mock()
        executorService = Mock()
        sut = new WebhookDispatcher(bitbucketApi, EventPayloads.create(), executorService, webhookHandler)
    }

    def "test that we can use default created"() {
        given:
        String json = WebhookDispatcherTest.getResource("/repo-refs-changed.json").text

        when:
        sut.dispatch("repo:refs_changed", json)

        then:
        1 * webhookHandler.onRepoRefsChanged()
    }


}
