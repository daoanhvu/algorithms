package com.bkda.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.bkda.exception.BKDAServiceException;
import com.bkda.model.Product;

@Repository
public class ProductDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Product findOne(long id) {
		return this.entityManager.find(Product.class, id);
	}
	
	public Product save(Product prd) {
		this.entityManager.persist(prd);
		return prd;
	}
	
	public Page<Product> search(String name, String companyName, Long userId, Pageable paging) {
		String selectQueryStr = "from Product p";
		String countQueryStr = "select count(p) from Product p";
		String whereClause = " where ((:name IS NULL) or (p.name like CONCAT('%',:name,'%'))) "
				+ "and ((:companyName IS NULL) or (p.company.name like CONCAT('%',:companyName,'%')))";
		Query countQuery = this.entityManager.createQuery(countQueryStr + whereClause);
		countQuery.setParameter("name", name);
		countQuery.setParameter("companyName", companyName);
		Long totalElements = (Long)countQuery.getSingleResult();
		
		TypedQuery<Product> selectQuery = this.entityManager.createQuery(selectQueryStr + whereClause, Product.class);
		selectQuery.setParameter("name", name);
		selectQuery.setParameter("companyName", companyName);
		selectQuery.setFirstResult(paging.getOffset());
		selectQuery.setMaxResults(paging.getPageSize());
		List<Product> result = selectQuery.getResultList();
		return new PageImpl<Product>(result, paging, totalElements);
	}
	
	Page<Product> recommend(Long userId, Pageable pageable) {
		throw new BKDAServiceException(2000, "NOT IMPLEMENTED YET!!!");
	}
}
