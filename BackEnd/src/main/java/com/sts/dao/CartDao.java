package com.bytes.intern.assessment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bytes.intern.assessment.model.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Long> {
	
	@Query(value="SELECT * FROM cart_table where employee_id=:employeeId", nativeQuery = true)
	List<Cart> getMyCart(Long employeeId);
	
	@Modifying
	@Query(value="UPDATE cart_table SET is_active = false where cart_id=:orderId",nativeQuery = true)
	void deleteCartOrder(Long orderId);

	@Modifying
	@Query(value="UPDATE cart_table SET is_active = false", nativeQuery=true)
	void updateStatusInactive();
}
