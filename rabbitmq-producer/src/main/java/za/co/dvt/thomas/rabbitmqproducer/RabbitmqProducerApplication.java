package za.co.dvt.thomas.rabbitmqproducer;

import za.co.dvt.thomas.rabbitmqproducer.producers.RabbitmqProducer;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Log4j2
@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner {
	private RabbitmqProducer producer;

	@Autowired
	public RabbitmqProducerApplication(final RabbitmqProducer producer) {
		this.producer = producer;
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		String message = "Thomas "+Math.random();
		log.info("Send message: %s".formatted(message));
		producer.sendHelloMessage(message);
	}
}
