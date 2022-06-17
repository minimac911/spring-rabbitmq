package za.co.dvt.thomas.rabbitmqproducer.producers;

import za.co.dvt.thomas.rabbitmqproducer.entity.Employee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeJsonProducer {
    private RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMappers;

    @Autowired
    public EmployeeJsonProducer(final RabbitTemplate rabbitTemplate, final ObjectMapper objectMappers) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMappers = objectMappers;
    }

    public void sendMessage(Employee employee) throws JsonProcessingException {
        String json = objectMappers.writeValueAsString(employee);
        rabbitTemplate.convertAndSend("course.employee", json);
    }
}
