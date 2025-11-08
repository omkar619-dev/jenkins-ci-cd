package com.example.omtechie.SpringJPAProj.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {
	@Pointcut("execution(* com.example.omtechie.SpringJPAProj.service.*.*(..))")
	private void logPointcut() {

	}


	@Before(value = "logPointcut()")
	public void logBefore(JoinPoint joinPoint) {
		log.info("method name" +joinPoint.getSignature().getName()+joinPoint.getTarget().toString());
		System.out.println("Logging before method execution");
	}

	@AfterReturning(value = "execution(* com.example.omtechie.SpringJPAProj.controller.*.*(..))")
	public void logAfter(JoinPoint joinPoint,Object result) throws JsonProcessingException {
		log.info("method name" +joinPoint.getSignature().getName()+joinPoint.getTarget().toString());
		log.info("Loggingadvice::logretsponse respoosne sbody {}", new ObjectMapper().writeValueAsString(result));
		System.out.println("Logging after method execution");
	}
	@After(value = "execution(* com.example.omtechie.SpringJPAProj.controller.*.*(..))")
	public void LogREspiosneAfter(Object result) throws JsonProcessingException {
		log.info("Loggingadvice::logretsponse respoosne sbody {}", new ObjectMapper().writeValueAsString(result));
		System.out.println("Logging after method execution");
	}

}
