package mvillalobos.presentations.spring.boot.kitchen.implementation.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mvillalobos.presentations.spring.boot.kitchen.api.entities.CassandraMessage;
import mvillalobos.presentations.spring.boot.kitchen.api.entities.MongoDbMessage;
import mvillalobos.presentations.spring.boot.kitchen.api.entities.PostgresMessage;
import mvillalobos.presentations.spring.boot.kitchen.api.entities.RedisMessage;
import mvillalobos.presentations.spring.boot.kitchen.api.model.Message;
import mvillalobos.presentations.spring.boot.kitchen.api.repositories.CassandraMessagesRepository;
import mvillalobos.presentations.spring.boot.kitchen.api.repositories.MongoDbMessagesRepository;
import mvillalobos.presentations.spring.boot.kitchen.api.repositories.PostgresMessageRepository;
import mvillalobos.presentations.spring.boot.kitchen.api.repositories.RedisMessagesRepository;
import mvillalobos.presentations.spring.boot.kitchen.api.services.MessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagesServiceImpl implements MessageService {

	private final CassandraMessagesRepository cassandraMessagesRepository;

	private final MongoDbMessagesRepository mongoDbMessagesRepository;

	private final PostgresMessageRepository postgresMessageRepository;

	private final RedisMessagesRepository redisMessagesRepository;

	private final RabbitTemplate rabbitTemplate;

	private final ObjectMapper objectMapper;

	@Autowired
	public MessagesServiceImpl(
			CassandraMessagesRepository cassandraMessagesRepository,
			MongoDbMessagesRepository mongoDbMessagesRepository,
			PostgresMessageRepository postgresMessageRepository,
			RedisMessagesRepository redisMessagesRepository,
			RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
		this.cassandraMessagesRepository = cassandraMessagesRepository;
		this.mongoDbMessagesRepository = mongoDbMessagesRepository;
		this.postgresMessageRepository = postgresMessageRepository;
		this.redisMessagesRepository = redisMessagesRepository;
		this.rabbitTemplate = rabbitTemplate;
		this.objectMapper = objectMapper;
	}

	@Override
	public Message createOneMessage(String message) {

		try {
			final PostgresMessage postgresMessage = new PostgresMessage();
			postgresMessage.setMessage(message);

			postgresMessageRepository.save(postgresMessage);
			final long id = postgresMessage.getId();

			final MongoDbMessage mongoDbMessage = new MongoDbMessage();
			mongoDbMessage.setId(id);
			mongoDbMessage.setMessage(message);

			final CassandraMessage cassandraMessage = new CassandraMessage();
			cassandraMessage.setId(id);
			cassandraMessage.setMessage(message);

			final RedisMessage redisMessage = new RedisMessage();
			redisMessage.setId(id);
			redisMessage.setMessage(message);

			mongoDbMessagesRepository.save(mongoDbMessage);
			cassandraMessagesRepository.save(cassandraMessage);
			redisMessagesRepository.save(redisMessage);

			final Message response = new Message(id, message);

			rabbitTemplate.convertAndSend("lajug", objectMapper.writeValueAsBytes(response));


			return response;
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Cannot convert object to JSON.");
		}
	}
}
