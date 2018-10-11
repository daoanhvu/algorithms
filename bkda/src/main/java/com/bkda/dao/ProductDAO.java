package com.bkda.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.bkda.model.Product;

public interface ProductDAO extends CrudRepository<Product, Long>, JpaSpecificationExecutor<Product> {

}
