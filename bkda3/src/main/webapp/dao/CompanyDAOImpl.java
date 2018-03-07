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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import main.webapp.model.Company;

@Repository
public class CompanyDAOImpl implements CompanyDAO {
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Company findById(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		Company result = null;
		params.put("id", id);
		
		//raw sql query
		String sql = "SELECT * FROM companies WHERE id=:id";
		
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new CompanyMapper());
		}catch(EmptyResultDataAccessException e) {
			//Do nothing here!
		}
		
		return result;
	}

	@Override
	public List<Company> getAllCompanies() {
		String sql = "SELECT * FROM companies";
		List<Company> result = namedParameterJdbcTemplate.query(sql, new CompanyMapper());

		return result;
	}

	@Override
	public List<Company> searchByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	static final class CompanyMapper implements RowMapper<Company> {
		@Override
		public Company mapRow(ResultSet rs, int rowIdx) throws SQLException {
			Company company = new Company();
			company.setId(rs.getInt("id"));
			company.setName(rs.getString("name"));
			company.setAddress(rs.getString("address"));
			company.setPhoneNumber1("phone1");
			company.setPhoneNumber2("phone2");
			company.setFaxNumber("fax");
			return company;
		}
		
	}
}
