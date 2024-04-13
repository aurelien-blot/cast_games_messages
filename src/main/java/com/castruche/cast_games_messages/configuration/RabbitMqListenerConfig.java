package com.castruche.cast_games_messages.configuration;

import com.castruche.cast_games_messages.dto.MessageDto;
import com.castruche.cast_games_messages.service.MessageReceptionService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
public class RabbitMqListenerConfig {

    public static final String QUEUE_NAME = "messageQueue";
    public static final String EXCHANGE_NAME = "messageExchange";
    public static final String ROUTING_KEY = "messageRoutingKey";

    private MessageReceptionService messageReceptionService;

    public RabbitMqListenerConfig(MessageReceptionService messageReceptionService) {
        this.messageReceptionService = messageReceptionService;
    }

    @Bean
    Queue queue() {
        return new Queue(RabbitMqListenerConfig.QUEUE_NAME, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(RabbitMqListenerConfig.EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMqListenerConfig.ROUTING_KEY);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(@Payload MessageDto message) {
        messageReceptionService.receiveMessage(message);
    }
}
