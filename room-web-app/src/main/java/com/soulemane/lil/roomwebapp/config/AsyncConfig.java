package com.soulemane.lil.roomwebapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.soulemane.lil.roomwebapp.async.RoomClearListener;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;



@Configuration 
public class AsyncConfig {
    private static final String QUEUE_NAME = "room-cleaner";
    private static final String EXCHANGE_NAME = "operations";

    /** Creates the queue that receives room-cleaning messages. */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    /** Creates the topic exchange that routes operation messages. */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    /** Routes room-cleaning messages from the exchange to the queue. */
    @Bean 
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("landom.room.cleaner");
    }
    
    /** Adapts the room-clear listener method to RabbitMQ messages. */
    @Bean
    public MessageListenerAdapter listenerAdapter(RoomClearListener receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
    
    /** Starts a RabbitMQ listener container for the room-cleaning queue. */
    @Bean 
    public SimpleMessageListenerContainer container(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }
}
