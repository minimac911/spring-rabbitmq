package za.co.dvt.thomas.rabbitmqconsumer.consumers;

import za.co.dvt.thomas.rabbitmqconsumer.entity.Picture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PictureVectorConsumer {
    private ObjectMapper objectMapper;

    @Autowired
    public PictureVectorConsumer(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "q.picture.vector")
    public void consume(String message) throws JsonProcessingException {
        Picture picture = objectMapper.readValue(message, Picture.class);
        log.info("Picture Vector: %s".formatted(picture.toString()));
    }
}
