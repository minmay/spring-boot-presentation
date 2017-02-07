package mvillalobos.presentations.spring.boot.kitchen.api.model;

public class Message {

	private final long id;
	private final String text;

	public Message(long id, String text) {
		this.id = id;
		this.text = text;
	}

	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}
}
