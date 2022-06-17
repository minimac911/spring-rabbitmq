package za.co.dvt.thomas.rabbitmqproducer.producers;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class FixedRateProducer {
    private RabbitTemplate rabbitTemplate;
    private int i = 0;

    @Autowired
    public FixedRateProducer(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Scheduled(fixedRate = 500)
    public void sendMessage(String message){
        log.info("Fixed rate message sent: %s".formatted(message));
        rabbitTemplate.convertAndSend("course.fixedrate", message);
    }
}
