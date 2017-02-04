package mvillalobos.presentations.spring.boot.without.ioc.implementation.dao;

import mvillalobos.presentations.spring.boot.without.ioc.api.dao.MessagesDao;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.util.List;

public class MessagesDaoImpl implements MessagesDao {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public MessagesDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public long createMessage(long usernameId, String message) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username_id", usernameId);
		parameters.addValue("message", message);
		final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		namedParameterJdbcTemplate.update(
				"INSERT INTO micro_messages(username_id, message) VALUES (:username_id, :message)",
				parameters,
				keyHolder
		);
		return keyHolder.getKey().longValue();
	}

	@Override
	public List<String> findMessagesByUsernameId(long usernameId) {

		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("username_id", usernameId);
		final List<String> messages = namedParameterJdbcTemplate.queryForList(
				"SELECT message FROM micro_messages WHERE username_id = :username_id",
				parameters, String.class
		);
		return messages;
	}
}
