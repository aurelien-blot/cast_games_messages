package com.castruche.cast_games_messages.configuration;

import com.castruche.cast_games_messages.dto.MessageReceptionDto;
import com.castruche.cast_games_messages.service.ConversationService;
import com.castruche.cast_games_messages.service.MessageReceptionService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
public class RabbitMqListenerConfig {

    public static final String MESSAGE_QUEUE_NAME = "messageQueue";
    public static final String MESSAGE_EXCHANGE_NAME = "messageExchange";
    public static final String MESSAGE_ROUTING_KEY = "messageRoutingKey";
    public static final String CONVERSATION_QUEUE_NAME = "conversationQueue";
    public static final String CONVERSATION_EXCHANGE_NAME = "conversationExchange";
    public static final String CONVERSATION_ROUTING_KEY = "conversationRoutingKey";

    private MessageReceptionService messageReceptionService;
    private ConversationService conversationService;

    public RabbitMqListenerConfig(MessageReceptionService messageReceptionService,
                                  ConversationService conversationService) {
        this.messageReceptionService = messageReceptionService;
        this.conversationService = conversationService;
    }

    @Bean
    Queue messageQueue() {
        return new Queue(MESSAGE_QUEUE_NAME, true);
    }

    @Bean
    Queue conversationQueue() {
        return new Queue(CONVERSATION_QUEUE_NAME, true);
    }

    @Bean
    DirectExchange messageExchange() {
        return new DirectExchange(MESSAGE_EXCHANGE_NAME);
    }

    @Bean
    DirectExchange conversationExchange() {
        return new DirectExchange(CONVERSATION_EXCHANGE_NAME);
    }

    @Bean
    Binding messageBinding(Queue messageQueue, DirectExchange messageExchange) {
        return BindingBuilder.bind(messageQueue).to(messageExchange).with(MESSAGE_ROUTING_KEY);
    }

    @Bean
    Binding conversationBinding(Queue conversationQueue, DirectExchange conversationExchange) {
        return BindingBuilder.bind(conversationQueue).to(conversationExchange).with(CONVERSATION_ROUTING_KEY);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAdviceChain(RetryInterceptorBuilder
                .stateless()
                .maxAttempts(5)
                .backOffOptions(1000, 2.0, 10000) // Délai initial, multiplicateur, délai max
                .build());
        return factory;
    }

    @RabbitListener(queues = MESSAGE_QUEUE_NAME)
    public void receiveMessageSendingRequest(@Payload MessageReceptionDto message) {
        messageReceptionService.receiveMessage(message);
    }

    @RabbitListener(queues = CONVERSATION_QUEUE_NAME)
    public void receiveConversationListRequest(@Payload Long playerId) {
        conversationService.getConversationListForPlayer(playerId);
    }
}
