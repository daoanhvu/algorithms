package main.webapp.dao;

import java.util.List;

import main.webapp.model.Company;

public interface CompanyDAO {
	Company findById(int id);
	List<Company> getAllCompanies();
	List<Company> searchByName(String name);
}
