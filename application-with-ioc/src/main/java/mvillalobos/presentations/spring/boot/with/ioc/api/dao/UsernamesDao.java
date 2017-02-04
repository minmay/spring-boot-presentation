package mvillalobos.presentations.spring.boot.with.ioc.api.dao;

import java.util.Optional;

public interface UsernamesDao {

	long createUsername(String name);

	Optional<Long> findIdByUsername(String name);
}
