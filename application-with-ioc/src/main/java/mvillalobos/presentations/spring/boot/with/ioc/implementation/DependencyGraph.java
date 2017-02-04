package mvillalobos.presentations.spring.boot.with.ioc.implementation;

import mvillalobos.presentations.spring.boot.with.ioc.api.dao.MessagesDao;
import mvillalobos.presentations.spring.boot.with.ioc.api.dao.UsernamesDao;
import mvillalobos.presentations.spring.boot.with.ioc.api.services.MicroMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DependencyGraph {

	private final DataSource dataSource;
	private final UsernamesDao usernamesDao;
	private final MessagesDao messagesDao;
	private final MicroMessageService microMessageService;

	@Autowired
	public DependencyGraph(
			DataSource dataSource,
			UsernamesDao usernamesDao,
			MessagesDao messagesDao,
			MicroMessageService microMessageService
	) {
		this.dataSource = dataSource;
		this.usernamesDao = usernamesDao;
		this.messagesDao = messagesDao;
		this.microMessageService = microMessageService;
	}

	public MicroMessageService withMicroMessagesService() {
		return microMessageService;
	}
}
