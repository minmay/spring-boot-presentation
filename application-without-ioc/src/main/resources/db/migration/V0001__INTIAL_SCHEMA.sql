SET MODE POSTGRESQL;

CREATE SEQUENCE username_seq;

CREATE TABLE usernames (
	id INTEGER DEFAULT username_seq.nextval PRIMARY KEY,
	name VARCHAR(255) CONSTRAINT usernames_name_nn NOT NULL
);

CREATE SEQUENCE micro_message_seq;

CREATE TABLE micro_messages (
  id INTEGER DEFAULT micro_message_seq.nextval PRIMARY KEY,
	username_id INTEGER CONSTRAINT micro_messages_username_id_nn NOT NULL,
  message VARCHAR(255) CONSTRAINT micro_messages_message_nn NOT NULL,
	CONSTRAINT micro_messages_usernames_fk FOREIGN KEY (username_id) REFERENCES usernames (id) ON UPDATE CASCADE ON DELETE CASCADE
);