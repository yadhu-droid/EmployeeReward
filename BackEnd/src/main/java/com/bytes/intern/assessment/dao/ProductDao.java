package com.bytes.intern.assessment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bytes.intern.assessment.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {

	@Modifying
	@Query(value="UPDATE product_table SET ismerch_deleted='true' where merch_id=:merchId", nativeQuery = true) //?1 is a positional parameter plaeholder indicating the first parameter
	void deleteStatusById(Long merchId);

	List<Product> findByIsmerchDeletedFalse(); //this sends only those rows in which ismerch_deleted=false
	
	@Modifying
	@Query(value="UPDATE product_table SET merch_quantity = merch_quantity - 1, merch_status = CASE WHEN (merch_quantity - 1) <= 0 THEN 'out-of-stock' ELSE merch_status END WHERE merch_id=:merchendiseId", nativeQuery = true)
	void updateStatus(long merchendiseId);

}
