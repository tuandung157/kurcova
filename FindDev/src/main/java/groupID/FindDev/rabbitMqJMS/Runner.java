package groupID.FindDev.rabbitMqJMS;

import groupID.FindDev.FindDevApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class Runner implements CommandLineRunner{

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }
    @Override
    public void run(String... args) throws Exception{
        System.out.println("Sending message ...");
        rabbitTemplate.convertAndSend(FindDevApplication.topicExchangeName,"foo.bar.baz","hello from rabbitMQ!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
