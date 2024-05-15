package com.bytes.intern.assessment.serviceimpl;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytes.intern.assessment.dao.CartDao;
import com.bytes.intern.assessment.dao.EmployeeDao;
import com.bytes.intern.assessment.dao.ProductDao;
import com.bytes.intern.assessment.dao.TransactionDebitDao;
import com.bytes.intern.assessment.dto.ProductDto;
import com.bytes.intern.assessment.dto.TransactionDebitDto;
import com.bytes.intern.assessment.model.Cart;
import com.bytes.intern.assessment.model.Employee;
import com.bytes.intern.assessment.model.Product;
import com.bytes.intern.assessment.service.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	
	@Autowired
	TransactionDebitDao transactionDebitDao;
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	CartDao cartDao;

	@Override
	public Product addProduct(ProductDto productDto) {
		Product product = new Product();
        product.setMerchName(productDto.getMerchName());
        product.setMerchDescription(productDto.getMerchDescription());
        product.setMerchCategory(productDto.getMerchCategory());
        product.setMerchManufacturer(productDto.getMerchManufacturer());
        product.setMerchQuantity(productDto.getMerchQuantity());
        product.setMerchCost(productDto.getMerchPoints());
        product.setMerchRedemptionConditions(productDto.getMerchRedemptionConditions());
        product.setMerchTermsAndConditions(productDto.getMerchTermsAndConditions());
        product.setMerchDeleted(false);
        product.setMerchStatus(productDto.getMerchStatus());
        product.setMerchExpiryDate(productDto.getMerchExpiryDate());
        String base64DataWithoutPrefix = productDto.getBase64Image().replaceFirst("data:image/png;base64,", "");
    
        byte[] decodedImage = Base64.getMimeDecoder().decode(base64DataWithoutPrefix);
        product.setMerchImage(decodedImage);
        product.setMerchStatus("In Stock");
		return productDao.save(product);
	}

	@Override
	public List<Product> getProductList() {
		return productDao.findByIsmerchDeletedFalse();
	}

	@Override
	public long getProductCount() {
		return transactionDebitDao.count();
	}

	@Override
	@Transactional
	public void deleteProduct(Long merchId) {
		productDao.deleteStatusById(merchId);
	}
	
	@Override
	public void addToCart(TransactionDebitDto transactionDebitDto) {
		
		// Fetch the associated Employee entity to know if employee is present or not and also to store that employee in employeeId of transaction (foreign key)
        Employee employee = employeeDao.findById(transactionDebitDto.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + transactionDebitDto.getEmployeeId()));
        
     // Fetch the associated Product entity to know if employee is present or not and also to store that employee in employeeId of transaction (foreign key)
        Product product = productDao.findById(transactionDebitDto.getMerchendiseId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + transactionDebitDto.getMerchendiseId()));
        
        Cart cart = new Cart();
        cart.setEmployee(employee);
        cart.setProduct(product);
        cart.setMerchCost(transactionDebitDto.getMerchendiseCost());
        cart.setActive(true);
        
		cartDao.save(cart);
	}

	@Override
	public List<Cart> getMyCartList(Long employeeId) {
		return cartDao.getMyCart(employeeId);
	}

	@Override
	@Transactional
	public void deleteCartOrder(Long orderId) {
		cartDao.deleteCartOrder(orderId);
	}
	

}
