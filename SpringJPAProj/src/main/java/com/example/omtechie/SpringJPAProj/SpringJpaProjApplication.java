package com.example.omtechie.SpringJPAProj;

import com.example.omtechie.SpringJPAProj.common.AuditorAwareImpl;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@EnableEncryptableProperties
//@EnableCaching
public class SpringJpaProjApplication {

	@Bean
	public AuditorAware<String> auditorAware(){
	return new AuditorAwareImpl();
}


	public static void main(String[] args) {
		SpringApplication.run(SpringJpaProjApplication.class, args);
	}

}
