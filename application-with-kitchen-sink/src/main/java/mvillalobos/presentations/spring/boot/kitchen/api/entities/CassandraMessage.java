package mvillalobos.presentations.spring.boot.kitchen.api.entities;


import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value = "cassandra_messages")
public class CassandraMessage {

	@PrimaryKey
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

	@Override
	public String toString() {
		return String.format("{ @type = %1$s, id = %2$s, message = %3$s }",
				getClass().getName(), getId(), getMessage());
	}
}
