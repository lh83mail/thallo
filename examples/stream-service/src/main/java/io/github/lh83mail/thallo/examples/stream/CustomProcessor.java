package io.github.lh83mail.thallo.examples.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomProcessor  {
    String CUSTOM = "myinput";

    @Input(CUSTOM)
    SubscribableChannel input();
}
