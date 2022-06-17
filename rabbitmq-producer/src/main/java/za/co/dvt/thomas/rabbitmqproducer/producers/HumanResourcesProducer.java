package za.co.dvt.thomas.rabbitmqproducer.producers;

import za.co.dvt.thomas.rabbitmqproducer.entity.Employee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanResourcesProducer {
    private RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMapper;
    @Autowired
    public HumanResourcesProducer(final RabbitTemplate rabbitTemplate, final ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(Employee employee) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(employee);

        rabbitTemplate.convertAndSend("x.hr", "", json);
    }
}
