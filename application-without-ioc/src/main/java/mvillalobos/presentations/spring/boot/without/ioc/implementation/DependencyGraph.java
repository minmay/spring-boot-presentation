package mvillalobos.presentations.spring.boot.without.ioc.implementation;

import mvillalobos.presentations.spring.boot.without.ioc.api.dao.MessagesDao;
import mvillalobos.presentations.spring.boot.without.ioc.api.dao.UsernamesDao;
import mvillalobos.presentations.spring.boot.without.ioc.api.services.MicroMessageService;

import javax.sql.DataSource;

public class DependencyGraph {

	private final DataSource dataSource;
	private final UsernamesDao usernamesDao;
	private final MessagesDao messagesDao;
	private final MicroMessageService microMessageService;

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
