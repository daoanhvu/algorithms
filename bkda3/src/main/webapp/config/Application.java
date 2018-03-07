package main.webapp.config;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import main.webapp.model.Company;
import main.webapp.service.CompanyService;

/**
 * 
 * @author davu
 *
 */
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	private static volatile Application INSTANCE = null;
	
	/**
	 * Hide the default constructor and do nothing here
	 */
	private Application() {
	}
	
	public static Application getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Application();
			INSTANCE.initialize();
		}
		
		return INSTANCE;
	}
	/****************************************************/
	private HashMap<Integer, Company> companies = new HashMap<>();
	
	private void initialize() {
		ApplicationContext appContext = ApplicationContextUtil.getApplicationContext();
		CompanyService companyService = (CompanyService) appContext.getBean("companyService");
		List<Company> listComp = companyService.loadAll();
		
		for(Company comp: listComp) {
			companies.put(Integer.valueOf(comp.getId()), comp);
		}
		
		logger.info("[Application] Application initializing done!!!!!");
	}
	
	public void addCompany(Company company) {
		companies.put(Integer.valueOf(company.getId()), company);
	}
	
	public Company getCompanyById(int id) {
		return companies.get(Integer.valueOf(id));
	}
}
