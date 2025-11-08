package com.example.omtechie.SpringJPAProj.service;

import com.example.omtechie.SpringJPAProj.entity.Product;
import com.example.omtechie.SpringJPAProj.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
@CacheConfig( cacheNames = "products")
public class ProductService {
	@Autowired
	private ProductRepository productRepository;


//@PostConstruct //init method will be called after the bean is initialized
//	public void initDb(){
//		List<Product> products = IntStream.rangeClosed(1, 1000).mapToObj(i -> new Product("product " + i, new Random().nextInt(5000), "desc " + i, "type " + i))
//			.collect(Collectors.toList());
//		productRepository.saveAll(products);
//
//	}
	@CachePut(cacheNames = "products",key = "#product.id")

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	@Cacheable(cacheNames = "products")
	public List<Product> getAllProducts() {
	log.info("getAllProducts() connecting to db....");
		return productRepository.findAll();
	}
	@Cacheable(cacheNames = "products",key = "#id")
	public  Product getProduct(int id) {
		log.info("getProductByID() connecting to db....");
		return productRepository.findById(id).orElse(null);
	}

	public Product getProductByName(String name) {
		return productRepository.findByName(name);
	}

	public List<Product> getProductsByType(String productType) {
		return productRepository.findByproductType(productType);
	}

	public List<Product> getProductsWithPriceAndType(double price,String productType) {
		return productRepository.findByPriceAndProductType(price,productType);
	}

	public List<Product> getProductsByPrice(double price) {
		return productRepository.getProductsByPrice(price);
	}

	@CachePut(cacheNames = "products",key = "#id")
	public Product updateProduct(int id,Product product) {
		Product existingProduct = productRepository.findById(id).orElse(null);
		if (existingProduct != null) {
			existingProduct.setName(product.getName());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setDescription(product.getDescription());
			existingProduct.setProductType(product.getProductType());
			return productRepository.save(existingProduct);
		}
		return null;
	}

	@CacheEvict(key = "#id")
	public int deleteProduct(int id) {
		  productRepository.deleteById(id);
		System.out.println("Record deleted successfully");
		return  productRepository.findAll().size();

	}

	public List<Product> getProductsByMultiplePriceValue(List<Double> priceList) {
		return productRepository.findByPriceIn(priceList); // prefix + fields + operator
	}

	public List<Product> getProductsWithinPriceRange(double startPrice,double endPrice) {
		return productRepository.findByPriceBetween(startPrice,endPrice); // prefix + fields + operator
	}

	public List<Product> getProductsGreatherThanPrice(double price) {
		return productRepository.findByPriceGreaterThan(price); // prefix + fields + operator
	}

	public List<Product> getProductsWithLike(String name) {
		return productRepository.findByNameIgnoreCaseContaining(name); // prefix + fields + operator
	}
	//sorting
	public List<Product> getProductsWithSorting(String fieldName){
		return productRepository.findAll(Sort.by(Sort.Direction.ASC,fieldName));
	}
	//pagination
	public Page<Product> getProductsWithPagination(int offset,int limit){
		return productRepository.findAll(PageRequest.of(offset,limit));
	}

	public Page<Product> getProductsWithPaginationAndSorting(int offset, int limit, String fieldName){
		return productRepository.findAll(PageRequest.of(offset,limit).withSort(Sort.by(fieldName)));
	}


}
