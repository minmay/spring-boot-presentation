package mvillalobos.presentations.spring.boot.kitchen.api.repositories;

import mvillalobos.presentations.spring.boot.kitchen.api.entities.PostgresMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresMessageRepository extends JpaRepository<PostgresMessage, Long> {
}
