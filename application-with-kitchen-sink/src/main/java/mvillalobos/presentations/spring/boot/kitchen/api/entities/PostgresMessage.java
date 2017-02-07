package mvillalobos.presentations.spring.boot.kitchen.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "postgres_messages")
public class PostgresMessage {

	@Id
	@SequenceGenerator(name="postgres_messages_seq",
			sequenceName="postgres_messages_seq",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator="postgres_messages_seq")
	private long id;

	private String message;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
