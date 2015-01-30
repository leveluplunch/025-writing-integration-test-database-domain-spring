package com.levelup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

			Person person = new Person();

			person.setPersonId(rs.getInt("PERSONID"));
			person.setFirstName(rs.getString("firstname"));
			person.setMiddleName(rs.getString("MIDDLENAME"));
			person.setLastName(rs.getString("LASTNAME"));
			person.setUserId(rs.getString("USERID"));
			person.setPassword(rs.getString("PASSWORD"));

			return person;
		}

	}

	public Person getPerson(int personId) {
		String sql = "SELECT * from PERSON WHERE PERSONID = " + personId;
		return jdbcTemplate.queryForObject(sql, new PersonRowMapper());
	}

	public int updatePassword(Person person) {

		String updateSql = "UPDATE PERSON SET PASSWORD = ? WHERE PERSONID = ?";

		return jdbcTemplate.update(updateSql,
				new Object[] { person.getPassword(), person.getPersonId() },
				new int[] { Types.VARCHAR, Types.INTEGER });
	}

}
