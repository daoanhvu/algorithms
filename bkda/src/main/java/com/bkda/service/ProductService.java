package com.bkda.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bkda.dao.ProductDAO;
import com.bkda.dto.ProductDTO;
import com.bkda.exception.BKDAServiceException;
import com.bkda.model.Company;
import com.bkda.model.Product;
import com.bkda.dto.ErrorContent;

@Service
public class ProductService {
	
	private Logger log = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductDAO productDAO;
	
	@Value("${storage.mediaFolderPath}")
	private String mediaFolderPath;
	
	public Product addProduct(ProductDTO productDto) {
		List<ErrorContent> errors = new ArrayList<>();
		
		if(StringUtils.isBlank(productDto.getName())) {
			errors.add(new ErrorContent(1700, "Product's name missing"));
		}
		
		if(errors.size() > 0) {
			throw new BKDAServiceException(1000, "Bad request");
		}
		
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product = productDAO.save(product);
		return product;
	}
	
	// TODO: To be implemented
	public List<Product> fakeSearch(String name) {
		List<Product> fakeList = new ArrayList<>();
		Company company = new Company();
		company.setName("Cty TNHH ABC");
		for(int i=0; i<16; i++) {
			Product p = new Product();
			p.setCompany(company);
			p.setDescription("This is a example product " + i );
			p.setName("Product " + i);
			fakeList.add(p);
		}
		
		return fakeList;
	}
	
	public Page<Product> search(String name, String company, Long userId, Pageable pageable) {
		return productDAO.search(name, company, userId, pageable);
	}
}
