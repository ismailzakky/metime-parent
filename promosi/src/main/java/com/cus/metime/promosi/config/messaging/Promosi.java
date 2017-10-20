package com.cus.metime.promosi.config.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by C-US on 9/25/2017.
 */
public interface Promosi extends Sink,Source {

    @Output
    MessageChannel fileOutput();

    @Output
    MessageChannel indexOutput();

    @Input
    SubscribableChannel fileInput();

    @Input
    SubscribableChannel indexInput();

}
