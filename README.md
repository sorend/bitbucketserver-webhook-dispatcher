# bitbucket server webhook dispatcher

[![Release](https://jitpack.io/v/sorend/bitbucketserver-webhook-dispatcher.svg)](https://jitpack.io/#sorend/bitbucketserver-webhook-dispatcher)
[![Build status](https://github.com/sorend/bitbucketserver-webhook-dispatcher/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/sorend/bitbucketserver-webhook-dispatcher/actions/?query=branch%3Amain)
[![CodeQL](https://github.com/sorend/bitbucketserver-webhook-dispatcher/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/sorend/bitbucketserver-webhook-dispatcher/actions/workflows/codeql-analysis.yml)
[![Codecov](https://codecov.io/gh/sorend/bitbucketserver-webhook-dispatcher/branch/main/graph/badge.svg)](https://codecov.io/gh/sorend/bitbucketserver-webhook-dispatcher)

Tooling for implementing bitbucket-server "stash" webhook services in any Java framework :-)

Features:
* Map BitbucketServer native webhook payloads into Java counterparts.
* Framework for mapping payloads into method calls.

## Usage

First provide a [WebhookHandler](src/main/java/com/github/sorend/bitbucketserver/webhook/WebhookHandler.java), you can
extend the empty implementation found in [BaseWebhookHandler](src/main/java/com/github/sorend/bitbucketserver/webhook/BaseWebhookHandler.java).

```java

public class MyWebhookHandler extends BaseWebhookHandler {
    @Override
    public void onRepoRefsChanged(WebhookEvent<RepoRefsChanged, RepoContext> event) {
        // do the work when repo refs changed
        System.out.println("repo = " + event.payload.repository().slug);
    }
}
```

The event contains:
* payload -- details of the webhook payload, such as who did what and where.
* context -- depending on the event, the context allows further actions, for example on a PR-comment event, you can
  use the context to delete the comment immediately or other actions.


When the webhook handler is ready, the [WebhookDispatcher](src/main/java/com/github/sorend/bitbucketserver/webhook/WebhookDispatcher.java)
is used to wire raw payloads up with the WebhookHandler.

```java

// setup dispatcher
WebhookHandler myHandler = ...;
WebhookDispatcher dispatcher = WebhookDispatcher.create(null, myHandler);

// get the event key and the payload body, this depends on what service framework used.
String xEventKey = request.getHeader("X-Event-Key");
String payloadJson = request.getBody();

dispatcher.dispatch(xEventKey, payloadJson);
```

In the default configuration. WebhookDispatcher has a single thread ExecutorService for processing the dispatches.
The WebhookHandler will be evaluated on this thread.

## Getting

[![Release](https://jitpack.io/v/sorend/bitbucketserver-webhook-dispatcher.svg)](https://jitpack.io/#sorend/bitbucketserver-webhook-dispatcher)

The library is available through Jitpack. Add the following to your `build.gradle`:
```groovy
repositories {
    mavenCentral()
    maven { url "https://jitpack.io" }
}
dependencies {
    implementation 'com.github.sorend:bitbucketserver-webhook-dispatcher:VERSION'
}
```


## About

Developed at [Bankdata.dk](https://bankdata.dk/) for implementation of GitOps services that react to actions in
an on-prem Bitbucket Server installation.
