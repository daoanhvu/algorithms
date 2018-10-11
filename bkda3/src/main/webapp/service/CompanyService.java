package main.webapp.service;

import java.util.List;

import main.webapp.model.Company;

public interface CompanyService {
	Company findById(int id);
	List<Company> findByName(String name);
	List<Company> loadAll();
}
