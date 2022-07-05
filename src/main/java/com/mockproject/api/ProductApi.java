package com.mockproject.api;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.entity.Products;
import com.mockproject.service.ProductsService;

@RestController
@RequestMapping("/api/products")
public class ProductApi {
	
	@Autowired
	private ProductsService productsService;
	
	// localhost:8080/api/products
	@GetMapping("")
	public ResponseEntity<?> doGetAll() {
		List<Products> products = productsService.findAll();
		return ResponseEntity.ok(products);
	}
	
	// localhost:8080/api/products/slug/{....}
	@GetMapping("/slug/{slug}")
	public ResponseEntity<?> doGetBySlug(@PathVariable("slug") String slug) {
		Products product = productsService.findBySlug(slug);
		if (ObjectUtils.isEmpty(product)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
		} 
		return ResponseEntity.ok(product);
	}
}
