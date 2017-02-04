package mvillalobos.presentations.spring.boot.with.ioc.api.dao;

import java.util.List;

public interface MessagesDao {

	long createMessage(long usernameId, String message);

	List<String> findMessagesByUsernameId(long usernameId);
}
