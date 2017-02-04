package mvillalobos.presentations.spring.boot.with.ioc.api.services;

import java.util.List;

public interface MicroMessageService {

	void mergeMessage(String name, String... messages);

	List<String> findMessagesByUsername(String name);
}
