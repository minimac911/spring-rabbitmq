package za.co.dvt.thomas.rabbitmqproducer;

import za.co.dvt.thomas.rabbitmqproducer.entity.Employee;
import za.co.dvt.thomas.rabbitmqproducer.producers.EmployeeJsonProducer;

import java.time.LocalDate;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class RabbitmqProducerApplication implements CommandLineRunner {
	private EmployeeJsonProducer producer;

	@Autowired
	public RabbitmqProducerApplication(final EmployeeJsonProducer producer) {
		this.producer = producer;
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		for (int i = 0; i < 5; i++) {
			Employee employee = new Employee("emp"+i,"Employee "+i, LocalDate.now());
			producer.sendMessage(employee);
			log.info("Sent employee: %s".formatted(employee.toString()));
		}
	}
}
