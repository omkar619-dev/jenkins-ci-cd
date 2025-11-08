package com.example.omtechie.SpringJPAProj;

import com.example.omtechie.SpringJPAProj.controller.ProductController;
import com.example.omtechie.SpringJPAProj.entity.Product;
import com.example.omtechie.SpringJPAProj.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

// ... inside your test method
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class SpringJpaProjApplicationTests {
	@Autowired
	private ProductController productController;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProductRepository productRepository;

	@Before("")
	public void setup(){
		this.mockMvc = MockMvcBuilders.standaloneSetup(ProductController.class).build();
	}

	@Test
	public void saveProductTest(){
		Product demoProduct = new Product("demo",1000,"demo product","sample product");
		//URL -> /products
		//Http Method -> POST
		//Request Body -> product object Product (Json String)
		//Response Body -> success message

		when(productRepository.save(any())).thenReturn(demoProduct );
	}
	@Test
	public void getAllProductsTest() throws Exception {
		//URL -> /products
		//Http Method -> GET
		//Request Body -> No
		//Response Body -> List of Products (Json String)
 when(productRepository.findAll()).thenReturn(Arrays.asList(new Product("demo",1000,"demo product","sample product")));
		mockMvc.perform(get("/products")
				.accept("application/json"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.*").exists())
			.andExpect(jsonPath("$.[0].id").value(1));
	}

	@Test
	public void stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("omtechie"); // Use a strong password in production
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		System.out.println(encryptor.encrypt("root"));
	}
	private String convertObjectAsString(Object object){
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
