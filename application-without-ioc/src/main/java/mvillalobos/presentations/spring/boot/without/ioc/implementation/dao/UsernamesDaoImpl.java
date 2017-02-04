package mvillalobos.presentations.spring.boot.without.ioc.implementation.dao;

import mvillalobos.presentations.spring.boot.without.ioc.api.dao.UsernamesDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.util.Optional;

public class UsernamesDaoImpl implements UsernamesDao {

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public UsernamesDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public long createUsername(String name) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", name);
		final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		namedParameterJdbcTemplate.update("INSERT INTO usernames(name) VALUES (:name)", parameters, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public Optional<Long> findIdByUsername(String name) {
		final MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", name);

		Optional<Long> result;

		try {
			final Long id = namedParameterJdbcTemplate.queryForObject(
					"SELECT id FROM usernames WHERE name = :name",
					parameters,
					Long.class
			);
			result = Optional.of(id);
		} catch (EmptyResultDataAccessException e) {
			result = Optional.empty();
		}

		return result;
	}

}
