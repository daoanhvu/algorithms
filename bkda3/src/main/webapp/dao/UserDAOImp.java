package main.webapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import main.webapp.config.Application;
import main.webapp.model.Company;
import main.webapp.model.User;

@Repository
public class UserDAOImp implements UserDAO {
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public User findById(long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		User result = null;
		params.put("id", id);
		
		//raw sql query
		String sql = "SELECT * FROM users WHERE id=:id";
		
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
		}catch(EmptyResultDataAccessException e) {
			//Do nothing here!
		}
		
		return result;
	}

	@Override
	public boolean save(User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO USERS(username, firstname, lastname, email, sex) "
				+ "VALUES(:username, :firstname, :lastname, :email, :sex)";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user), keyHolder);
		user.setId(keyHolder.getKey().longValue());
		return true;
	}
	
	@Override
	public boolean update(User user) {
		String sql = "UPDATE USERS SET name=:name, email=:email, sex=:sex WHERE id=:id";
		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));
		return true;
	}
	
	public List<User> getAll() {
		String sql = "SELECT * FROM users";
		List<User> result = namedParameterJdbcTemplate.query(sql, new UserMapper());

		return result;
	}
	
	private SqlParameterSource getSqlParameterByModel(User user) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		paramSource.addValue("id", user.getId());
		paramSource.addValue("username", user.getUsername());
		paramSource.addValue("firstname", user.getFirstname());
		paramSource.addValue("lastname", user.getLastname());
		paramSource.addValue("phonenumber", user.getPhoneNumber());
		paramSource.addValue("email", user.getEmail());
		paramSource.addValue("sex", user.getSex());
		paramSource.addValue("company", user.getCompany()==null?0:user.getCompany().getId());
		return paramSource;
	}
	
	static final class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowIdx) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setUsername(rs.getString("username"));
			user.setFirstname(rs.getString("firstname"));
			user.setLastname(rs.getString("lastname"));
			user.setPhoneNumber("phonenumber");
			user.setEmail(rs.getString("email"));
			user.setSex(rs.getString("sex"));
			
			int comId = rs.getInt("company");
			Company comp = Application.getInstance().getCompanyById(comId);
			user.setCompany(comp);
			
			return user;
		}
		
	}
}
