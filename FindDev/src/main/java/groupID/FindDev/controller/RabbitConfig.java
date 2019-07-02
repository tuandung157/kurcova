package groupID.FindDev.controller;

import groupID.FindDev.rabbitMq.RabbitSender;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class RabbitConfig {

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("amq.fanout");
    }

//    @Profile("sender")
    @Bean
    public RabbitSender sender() {
        return new RabbitSender();
    }


}
