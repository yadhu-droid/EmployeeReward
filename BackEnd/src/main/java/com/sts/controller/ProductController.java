package com.bytes.intern.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytes.intern.assessment.dto.ProductDto;
import com.bytes.intern.assessment.dto.TransactionDebitDto;
import com.bytes.intern.assessment.model.Cart;
import com.bytes.intern.assessment.model.Product;
import com.bytes.intern.assessment.model.ResponseHandler;
import com.bytes.intern.assessment.service.ProductService;
import com.bytes.intern.assessment.service.TransactionService;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	TransactionService transactionService;

	@PostMapping("/add")
	public Product addProduct(@RequestBody ProductDto productDto) {
		return productService.addProduct(productDto);
	}

	@GetMapping("/getproducts")
	public List<Product> getProductList() {
		return productService.getProductList();
	}
	
	@PostMapping("/submitorder")
	public ResponseEntity<Object> addTransactionDebit(@RequestBody TransactionDebitDto transactionDebitDto) {
		transactionService.addTransactionDebit(transactionDebitDto);
		 return ResponseHandler.generateResponse("Successfully added to order!", HttpStatus.OK, null);
	}
	
	@GetMapping("/getproductcount")
	public long getProductCount() {
		return productService.getProductCount();
	}
	
	@DeleteMapping("/delete/{merchId}")
	public ResponseEntity<Object> deleteProduct(@PathVariable Long merchId) {
	    productService.deleteProduct(merchId);
	    return ResponseHandler.generateResponse("Successfully deleted the product!", HttpStatus.OK, null);
	}
	
	@PostMapping("/addtocart")
	public ResponseEntity<Object> addToCart(@RequestBody TransactionDebitDto transactionDebitDto) {
		productService.addToCart(transactionDebitDto);
		 return ResponseHandler.generateResponse("Successfully added to cart!", HttpStatus.OK, null);
	}
	
	@GetMapping("/getmycart/{employeeId}")
	public List<Cart> getMyCartList(@PathVariable Long employeeId) {
		return productService.getMyCartList(employeeId);
	}
	
	@DeleteMapping("/deletecartid/{orderId}")
	public ResponseEntity<Object> deleteCartOrder(@PathVariable long orderId) {
	    productService.deleteCartOrder(orderId);
	    return ResponseHandler.generateResponse("Successfully deleted the order ID!", HttpStatus.OK, null);
	}
	
	@PostMapping("/bulkproducts")
	public ResponseEntity<Object> buyBulkProducts(@RequestBody List<TransactionDebitDto> transactionDebitDtoList) { //its a list because of bulk allocations
		transactionService.buyBulkProducts(transactionDebitDtoList);
		 return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, null);
	}
	
}
