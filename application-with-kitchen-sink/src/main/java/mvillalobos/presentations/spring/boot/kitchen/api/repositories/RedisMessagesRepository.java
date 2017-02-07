package mvillalobos.presentations.spring.boot.kitchen.api.repositories;

import mvillalobos.presentations.spring.boot.kitchen.api.entities.RedisMessage;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RedisMessagesRepository extends PagingAndSortingRepository<RedisMessage, Long> {
}
