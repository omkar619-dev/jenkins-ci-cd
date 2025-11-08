package com.example.omtechie.SpringJPAProj.advice;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MetricsRegistryAdvcie {
	@Autowired
	private ObservationRegistry registry;
	@After(value = "execution(* com.example.omtechie.SpringJPAProj.controller.ProductController.*(..))")
	public void sendMetruics(JoinPoint joinPoint) {
		log.info("collectng metrics application");
		Observation.createNotStarted(joinPoint.getSignature().getName(),registry)
			.observe(()->joinPoint.getArgs());
		log.info("metrics collected successfully");
	}
}
