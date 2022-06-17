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
public class EmployeeJsonConsumer {
    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeJsonConsumer(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "course.employee")
    public void consumeEmployee(String message) throws JsonProcessingException {
        var employee = objectMapper.readValue(message, Employee.class);
        log.info("Employee is: %s".formatted(employee.toString()));
    }
}
