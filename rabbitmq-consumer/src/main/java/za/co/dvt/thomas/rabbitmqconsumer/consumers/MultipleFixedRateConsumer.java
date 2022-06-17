package za.co.dvt.thomas.rabbitmqconsumer.consumers;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MultipleFixedRateConsumer {
    @RabbitListener(queues = "course.fixedrate", concurrency = "3-7")
    public void consume(String message) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(1000,2000));
        log.info("%s : Consuming %s".formatted(Thread.currentThread().getName(), message));
    }
}
