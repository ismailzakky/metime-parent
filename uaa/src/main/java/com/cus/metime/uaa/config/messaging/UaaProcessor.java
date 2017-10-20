package com.cus.metime.uaa.config.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by C-US on 10/3/2017.
 */

public interface UaaProcessor extends Sink,Source{

    @Input
    SubscribableChannel salonInput();

    @Output
    MessageChannel fileOutput();

}
