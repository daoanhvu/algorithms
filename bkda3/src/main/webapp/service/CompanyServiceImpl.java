package main.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.webapp.dao.CompanyDAO;
import main.webapp.model.Company;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
	private CompanyDAO companyDAO;
	
	@Autowired
	public void setCompanyDAO(CompanyDAO compDao) {
		this.companyDAO = compDao;
	}

	@Override
	public Company findById(int id) {
		return companyDAO.findById(id);
	}

	@Override
	public List<Company> findByName(String name) {
		return companyDAO.searchByName(name);
	}

	@Override
	public List<Company> loadAll() {
		return companyDAO.getAllCompanies();
	}
}
