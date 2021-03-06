package io.github.lh83mail.thallo.gateway.admin.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import io.github.lh83mail.thallo.gateway.config.BusConfig;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Create At  2020/1/7 11:33
 *
 * @author: Lh
 * @version: 1.0.0
 */
@Configuration
@Slf4j
public class BusAdminConfig {
    public static final String QUEUE_NAME = "event-gateway";
    public static final String EXCHANGE_NAME = BusConfig.EXCHANGE_NAME;
    public static final String ROUTING_KEY =  BusConfig.ROUTING_KEY;

//    @Bean
//    Queue adminQueue() {
//        log.info("queue name:{}", QUEUE_NAME);
//        return new Queue(QUEUE_NAME, false);
//    }

    @Bean
    TopicExchange adminExchange() {
        log.info("exchange:{}", EXCHANGE_NAME);
        return new TopicExchange(EXCHANGE_NAME);
    }

//    @Bean
//    Binding adminBinding(Queue queue, TopicExchange exchange) {
//        log.info("binding {} to {} with {}", queue, exchange, ROUTING_KEY);
//        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
//    }

    @Bean
    public MessageConverter adminMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
    }

}
