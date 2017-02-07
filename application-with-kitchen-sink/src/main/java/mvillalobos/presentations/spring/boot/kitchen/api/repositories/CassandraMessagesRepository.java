package mvillalobos.presentations.spring.boot.kitchen.api.repositories;

import mvillalobos.presentations.spring.boot.kitchen.api.entities.CassandraMessage;
import org.springframework.data.repository.CrudRepository;

public interface CassandraMessagesRepository extends CrudRepository<CassandraMessage, Long> {
}
