package me.jics.main;

import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class FunctionServiceImpl implements FunctionService {
    private final SimpleClient simpleClient;

    public FunctionServiceImpl(SimpleClient simpleClient) {
        this.simpleClient = simpleClient;
    }

    @Override
    public int process(SampleInputMessage inputMessage) {
        int messageId = this.simpleClient.sent(inputMessage);
        log.info(String.valueOf(messageId));
        return messageId;
    }
}
