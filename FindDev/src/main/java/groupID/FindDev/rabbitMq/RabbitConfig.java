package groupID.FindDev.rabbitMq;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut3", "pub-sub", "publish-subscribe"})
@Configuration
public class RabbitConfig {

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("amq.fanout");
    }

    @Profile("sender")
    @Bean
    public RabbitSender sender() {
        return new RabbitSender();
    }


}
