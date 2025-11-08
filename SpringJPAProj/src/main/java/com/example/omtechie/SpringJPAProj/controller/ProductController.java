package com.example.omtechie.SpringJPAProj.controller;

import com.example.omtechie.SpringJPAProj.entity.Product;
import com.example.omtechie.SpringJPAProj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping
	public Product addProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}
	@GetMapping
	public List<Product> getProducts() {
		return productService.getAllProducts();
	}
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable int id) {
		return productService.getProduct(id);
	}
	@GetMapping("/name/{name}")
	public Product getProductByName(@PathVariable String name) {
		return productService.getProductByName(name);
	}
	@GetMapping("/price/{price}/productType/{productType}")
	private List<Product> getProductsWithPriceAndType(@PathVariable double price,@PathVariable String productType) {
		return productService.getProductsWithPriceAndType(price, productType);
	}

	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
		return "Product deleted successfully";}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable int id,@RequestBody Product product) {
		return productService.updateProduct(id,product);}

	@PostMapping("/search")
	public List<Product> getProductsByMultiplePriceValue(@RequestBody List<Double> priceList) {
		return productService.getProductsByMultiplePriceValue(priceList); // prefix + fields + operator
	}
	@GetMapping("/min/{startPrice}/max/{endPrice}")
	public List<Product> getProductsWithinPriceRange(@PathVariable double startPrice,@PathVariable double endPrice) {
		return productService.getProductsWithinPriceRange(startPrice,endPrice); // prefix + fields + operator
	}
	@GetMapping("/greater-than/{price}")
	public List<Product> getProductsGreatherThanPrice(double price) {
		return productService.getProductsGreatherThanPrice(price); // prefix + fields + operator
	}
@GetMapping("like/{name}")
	public List<Product> getProductsWithLike(@PathVariable String name) {
		return productService.getProductsWithLike(name); // prefix + fields + operator
	}

	@GetMapping("/sort/{fieldName}")
	public List<Product> getProductsWithSorting(@PathVariable String fieldName){
		return productService.getProductsWithSorting(fieldName);
	}
	//pagination
	@GetMapping("/page/{offset}/size/{limit}")
	public Page<Product> getProductsWithPagination(@PathVariable int offset, @PathVariable int limit){
		return productService.getProductsWithPagination(offset, limit);
	}
	@GetMapping("/pageWithSort/{fieldName}/{offset}/{limit}")
	public Page<Product> getProductsWithPaginationAndSorting(@PathVariable int offset,@PathVariable int limit, @PathVariable String fieldName){
		return productService.getProductsWithPaginationAndSorting(offset, limit, fieldName);
	}


}
