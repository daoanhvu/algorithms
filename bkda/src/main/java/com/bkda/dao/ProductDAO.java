package com.bkda.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bkda.model.Product;

@Repository
public interface ProductDAO extends CrudRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	
	@Query("from Product where id = ?1")
	Product findOne(long id);
	
	@Query(value = "from Product where name like ?1 and company like ?2",
			countQuery = "select count(*) from Product where name like ?1 and company like ?2")
	Page<Product> search(String name, String company, Long userId, Pageable pageable);
}
