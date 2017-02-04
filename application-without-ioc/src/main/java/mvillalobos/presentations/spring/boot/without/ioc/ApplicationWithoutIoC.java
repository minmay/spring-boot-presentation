package mvillalobos.presentations.spring.boot.without.ioc;

import mvillalobos.presentations.spring.boot.without.ioc.api.dao.MessagesDao;
import mvillalobos.presentations.spring.boot.without.ioc.api.dao.UsernamesDao;
import mvillalobos.presentations.spring.boot.without.ioc.api.services.MicroMessageService;
import mvillalobos.presentations.spring.boot.without.ioc.implementation.DependencyGraph;
import mvillalobos.presentations.spring.boot.without.ioc.implementation.dao.MessagesDaoImpl;
import mvillalobos.presentations.spring.boot.without.ioc.implementation.dao.UsernamesDaoImpl;
import mvillalobos.presentations.spring.boot.without.ioc.implementation.services.MicroMessageServiceImpl;
import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class ApplicationWithoutIoC implements CommandLineRunner {

	private final static Logger logger = LoggerFactory.getLogger(ApplicationWithoutIoC.class);

	public static void main(String[] args) {
		SpringApplication.run(ApplicationWithoutIoC.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		final DependencyGraph dependencyGraph = buildDependencyGraph();

		dependencyGraph.withMicroMessagesService().mergeMessage(
				"Marco",
				"Welcome to LAJUG.",
				"I hope that you enjoy this presentation on Spring Boot.", "Please learn something today."
		);

		final List<String> messages = dependencyGraph.withMicroMessagesService().findMessagesByUsername("Marco");

		for (String message : messages) {
			logger.info("Found message: {}", message);
		}
	}

	private DataSource buildDataSource() {
		final JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL("jdbc:h2:mem:testdb");
		dataSource.setUser("sa");
		return dataSource;
	}

	private DependencyGraph buildDependencyGraph() {
		final DataSource dataSource = buildDataSource();
		final NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		final UsernamesDao usernamesDao = new UsernamesDaoImpl(namedParameterJdbcTemplate);
		final MessagesDao messagesDao = new MessagesDaoImpl(namedParameterJdbcTemplate);
		final MicroMessageService microMessageService = new MicroMessageServiceImpl(usernamesDao, messagesDao);
		return new DependencyGraph(dataSource, usernamesDao, messagesDao, microMessageService);
	}
}
