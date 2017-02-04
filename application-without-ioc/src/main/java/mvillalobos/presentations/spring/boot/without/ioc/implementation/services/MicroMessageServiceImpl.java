package mvillalobos.presentations.spring.boot.without.ioc.implementation.services;

import mvillalobos.presentations.spring.boot.without.ioc.api.dao.MessagesDao;
import mvillalobos.presentations.spring.boot.without.ioc.api.dao.UsernamesDao;
import mvillalobos.presentations.spring.boot.without.ioc.api.services.MicroMessageService;

import java.util.ArrayList;
import java.util.List;

public class MicroMessageServiceImpl implements MicroMessageService {

	private final UsernamesDao usernamesDao;
	private final MessagesDao messagesDao;

	public MicroMessageServiceImpl(UsernamesDao usernamesDao, MessagesDao messagesDao) {
		this.usernamesDao = usernamesDao;
		this.messagesDao = messagesDao;
	}

	@Override
	public void mergeMessage(String name, String... messages) {

		final long username_id = usernamesDao.findIdByUsername(name)
				.orElse(usernamesDao.createUsername(name));

		for (String message : messages) {
			messagesDao.createMessage(username_id, message);
		}
	}

	@Override
	public List<String> findMessagesByUsername(String name) {

		return usernamesDao.findIdByUsername(name)
				.map(messagesDao::findMessagesByUsernameId)
				.orElse(new ArrayList<>());
	}
}
