package com.bkda.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bkda.model.Media;

@Repository
public interface MediaDAO extends CrudRepository<Media, Long>, JpaSpecificationExecutor<Media> {
	@Query("from Media where id = ?1")
	Media findOne(long id);
}
