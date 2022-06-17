package za.co.dvt.thomas.rabbitmqconsumer.consumers;

import za.co.dvt.thomas.rabbitmqconsumer.entity.Employee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MarketingConsumer {
    private ObjectMapper objectMapper;

    @Autowired
    public MarketingConsumer(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "q.hr.marketing")
    public void consume(String message) throws JsonProcessingException {
        Employee employee = objectMapper.readValue(message, Employee.class);

        log.info("Employee for marketing is: %s".formatted(employee.toString()));
    }
}
