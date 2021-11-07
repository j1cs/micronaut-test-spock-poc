package me.jics.main;

import io.micronaut.gcp.pubsub.annotation.PubSubClient;
import io.micronaut.gcp.pubsub.annotation.Topic;

@PubSubClient
public interface SimpleClient {
    @Topic("projects/test-project/topics/sample")
    int sent(SampleInputMessage inputMessage);
}
