package za.co.dvt.thomas.rabbitmqconsumer.consumers;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RabbitmqConsumer {

    @RabbitListener(queues = "course.hello")
    public void consume(String message){
        log.info("Consuming: %s".formatted(message));
    }
}
