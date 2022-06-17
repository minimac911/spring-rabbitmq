package za.co.dvt.thomas.rabbitmqconsumer.consumers;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class FixedRateConsumer {

    @RabbitListener(queues = "course.fixedrate")
    public void consumer(String message){
        log.info("Fixed rate message consumed: %s".formatted(message));
    }
}
