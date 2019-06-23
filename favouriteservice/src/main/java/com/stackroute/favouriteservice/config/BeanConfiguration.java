package com.stackroute.favouriteservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    private String playerExchange = "player_exchange";
    private String playerQueue = "player_queue";

    @Bean
    public MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange playerExchange(){
        return new DirectExchange(playerExchange);
    }

    @Bean
    public Queue playerQueue(){
        return new Queue(playerQueue, false);
    }

    @Bean
    public Binding playerBinding(Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("player_routing");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, MessageConverter converter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

   /* @Bean
    public AmqpAdmin admin(final ConnectionFactory connectionFactory, DirectExchange directExchange, Queue queue, Binding binding){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareExchange(directExchange);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);
        return rabbitAdmin;
    }*/

}
