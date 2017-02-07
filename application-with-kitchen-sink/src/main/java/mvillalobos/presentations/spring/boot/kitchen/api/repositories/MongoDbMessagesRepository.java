package mvillalobos.presentations.spring.boot.kitchen.api.repositories;


import mvillalobos.presentations.spring.boot.kitchen.api.entities.MongoDbMessage;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoDbMessagesRepository extends MongoRepository<MongoDbMessage, Long> {
}
