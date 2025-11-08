package com.example.omtechie.SpringJPAProj.repository;

import com.example.omtechie.SpringJPAProj.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository  extends RevisionRepository<Product,Integer,Integer> ,JpaRepository<Product, Integer> {
	Product findByName(String name);

	List<Product> findByproductType(String type);

	List<Product> findByPriceAndProductType(double price, String productType);

	//	@Query(value = "SELECT * FROM PRODUCT_TBL WHERE price > ?1",nativeQuery = true)
//	@Query("from PRODUCT_TBL p where p.price > ?1")
	@Query("from Product p where p.price = :price") // name based parameter
	List<Product> getProductsByPrice(@Param("price") double price);

	List<Product> findByPriceIn(List<Double> priceList);

	List<Product> findByPriceBetween(double startPrice, double endPrice);

	List<Product> findByPriceGreaterThan(double price);

	List<Product> findByNameIgnoreCaseContaining(String name);
}
