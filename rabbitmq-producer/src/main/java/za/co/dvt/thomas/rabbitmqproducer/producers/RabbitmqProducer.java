package za.co.dvt.thomas.rabbitmqproducer.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqProducer {
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitmqProducer(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendHelloMessage(String name){
        rabbitTemplate.convertAndSend("course.hello", "Hello %s".formatted(name));
    }
}
