# bitbucket server webhook dispatcher

Classes for implementing bitbucket-server "stash" webhook services in any framework :-)

There are two parts:
* Mapping payloads into Java counterparts.
* Framework for mapping payloads into method calls.

## Usage

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

