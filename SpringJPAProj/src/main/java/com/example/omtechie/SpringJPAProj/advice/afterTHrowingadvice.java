package com.example.omtechie.SpringJPAProj.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class afterTHrowingadvice {
	@Pointcut("execution(* com.example.omtechie.SpringJPAProj.controller.*.*(..))")
	public void alertFor(){

	}

	@AfterThrowing(value = "alertFor()",throwing = "exception")
	public void captureExceprtionAlerts(JoinPoint joinPoint,Exception exception) {
		log.error("Exception occured in the application, " +joinPoint.getSignature() + "please check once");
		log.error("Exception occured in the application {}",exception.getMessage());
		if(exception instanceof IllegalArgumentException){
			log.error("This is an IllegalArgumentException, please check the arguments passed");
		}
		if(exception instanceof RuntimeException){
			log.error("This is an RuntimeException, please check the application flow");
		}
	}
}
