package io.github.lh83mail.thallo.examples.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({Processor.class, CustomProcessor.class})
@EnableScheduling
public class StreamSampleApplication {
    @Autowired
    @Qualifier(Source.OUTPUT)
    private MessageChannel channel;

    public static void main(String[] args) {
        SpringApplication.run(StreamSampleApplication.class, args);
    }


    @Scheduled(fixedDelay = 2000)
    public void setMessageManual() {
        SomeObject someObject = new SomeObject("手动发送[" + System.currentTimeMillis() + "]", 12);
        this.channel.send(MessageBuilder.withPayload(someObject).build());
    }

    @StreamListener(Sink.INPUT)
    public void handle(SomeObject person) {
        System.out.println("Received: " + person);
    }

    @StreamListener(CustomProcessor.CUSTOM)
    public void handleCus(SomeObject person) {
        System.out.println("Custom Received: " + person);
    }

    @InboundChannelAdapter(channel = Source.OUTPUT, poller = @Poller(fixedRate = "5000"))
    public Message<?> generate() {
        SomeObject someObject = new SomeObject("名称" + System.currentTimeMillis(), Double.valueOf(90 * Math.random() + 1).intValue());
        System.out.println("Sending: " + someObject);
        return MessageBuilder.withPayload(someObject)
                .build();
    }
}
