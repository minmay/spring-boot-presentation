package mvillalobos.presentations.spring.boot.with.ioc;

import mvillalobos.presentations.spring.boot.with.ioc.api.services.MicroMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ApplicationWithIoC implements CommandLineRunner {

	private final static Logger logger = LoggerFactory.getLogger(ApplicationWithIoC.class);

	public static void main(String[] args) {
		SpringApplication.run(ApplicationWithIoC.class, args);
	}

	private MicroMessageService microMessageService;

	@Autowired
	public void setMicroMessageService(MicroMessageService microMessageService) {
		this.microMessageService = microMessageService;
	}

	@Override
	public void run(String... strings) throws Exception {

		microMessageService.mergeMessage(
				"Marco",
				"Welcome to LAJUG.",
				"I hope that you enjoy this presentation on Spring Boot.", "Please learn something today."
		);

		final List<String> messages = microMessageService.findMessagesByUsername("Marco");

		for (String message : messages) {
			logger.info("Found message: {}", message);
		}
	}
}
