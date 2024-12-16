package com.bytes.intern.assessment.service;


import java.util.List;

import com.bytes.intern.assessment.dto.ProductDto;
import com.bytes.intern.assessment.dto.TransactionDebitDto;
import com.bytes.intern.assessment.model.Cart;
import com.bytes.intern.assessment.model.Product;

public interface ProductService {

	public Product addProduct(ProductDto productDto);

	public List<Product> getProductList();

	public long getProductCount();

	public void deleteProduct(Long merchId);
	
	public void addToCart(TransactionDebitDto transactionDebitDto);

	public List<Cart> getMyCartList(Long employeeId);

	public void deleteCartOrder(Long orderId);
}
