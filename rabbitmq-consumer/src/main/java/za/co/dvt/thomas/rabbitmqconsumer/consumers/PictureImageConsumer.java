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
public class PictureImageConsumer {
    private ObjectMapper objectMapper;

    @Autowired
    public PictureImageConsumer(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "q.picture.image")
    public void consume(String message) throws JsonProcessingException {
        Picture picture = objectMapper.readValue(message, Picture.class);
        log.info("Picture Image: %s".formatted(picture.toString()));
    }
}
