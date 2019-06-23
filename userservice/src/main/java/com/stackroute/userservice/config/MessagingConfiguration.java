package com.stackroute.userservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {
    private String userExchange = "user_exchange";
    private String userQueue = "user_queue";

    @Bean
    public DirectExchange userExhange(){
        return new DirectExchange(userExchange);
    }

    @Bean
    public Queue userQueue(){
        return new Queue(userQueue, false);
    }

    @Bean
    public Binding userBinding(Queue userQueue, DirectExchange exchange){
        return BindingBuilder.bind(userQueue).to(exchange).with("user_routing");
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, MessageConverter messageConverter){
        System.out.println("Rabbit Template -> Connection Factory -> " + connectionFactory);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    /*@Bean
    public AmqpAdmin amqpAdmin(final ConnectionFactory connectionFactory, DirectExchange exchange, Queue queue, Binding binding){

        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);
        return rabbitAdmin;
        //return new RabbitAdmin(connectionFactory);
    }*/

}
