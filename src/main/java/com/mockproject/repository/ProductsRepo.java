package com.mockproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.entity.Products;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {
	
	List<Products> findByProductType_Id(Long typeId);
	Products findBySlug(String slug);
	List<Products> findByIsDeletedAndQuantityGreaterThan(Boolean isDeleted, Integer quantity);
}
