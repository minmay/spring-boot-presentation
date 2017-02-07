CREATE SEQUENCE postgres_messages_seq;

CREATE TABLE postgres_messages (
	id INTEGER PRIMARY KEY DEFAULT nextval('postgres_messages_seq'),
	message VARCHAR(255) CONSTRAINT usernames_name_nn NOT NULL
);

ALTER SEQUENCE postgres_messages_seq OWNED BY postgres_messages.id;