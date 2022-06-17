package za.co.dvt.thomas.rabbitmqproducer;

import za.co.dvt.thomas.rabbitmqproducer.entity.Employee;
import za.co.dvt.thomas.rabbitmqproducer.entity.Picture;
import za.co.dvt.thomas.rabbitmqproducer.producers.HumanResourcesProducer;
import za.co.dvt.thomas.rabbitmqproducer.producers.PictureProducer;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class RabbitmqProducerApplication implements CommandLineRunner {
	private HumanResourcesProducer humanResourcesProducer;
	private PictureProducer pictureProducer;
	private final List<String> SOURCES = List.of("mobile", "web");
	private final List<String> TYPES = List.of("jpg", "png", "svg");

	@Autowired
	public RabbitmqProducerApplication(final HumanResourcesProducer humanResourcesProducer, final PictureProducer pictureProducer) {
		this.humanResourcesProducer = humanResourcesProducer;
		this.pictureProducer = pictureProducer;
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		// human resources
		for (int i = 0; i < 5; i++) {
			Employee employee = new Employee("emp"+i,"Employee "+i, LocalDate.now());
			humanResourcesProducer.sendMessage(employee);
			log.info("Sent employee: %s".formatted(employee.toString()));
		}

		// picture
		for (int i = 0; i < 10; i++) {
			Picture picture = new Picture();
			picture.setName("Picture "+i);
			picture.setSize(ThreadLocalRandom.current().nextLong(1,10000));
			picture.setSource(SOURCES.get(i % SOURCES.size()));
			picture.setType(TYPES.get(i % TYPES.size()));

			pictureProducer.sendMessage(picture);
			log.info("Sent picture: %s".formatted(picture.toString()));
		}
	}
}
