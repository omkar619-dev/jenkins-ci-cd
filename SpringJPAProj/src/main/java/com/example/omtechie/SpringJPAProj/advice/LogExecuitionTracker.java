package com.example.omtechie.SpringJPAProj.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogExecuitionTracker {
	@Around(value = "execution(* com.example.omtechie.SpringJPAProj..*(..))")
	public Object ExecutionTracker(ProceedingJoinPoint joinPoint) throws Throwable {
		//before
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed(); 
		//after
		long end = System.currentTimeMillis();

		log.info("Method {} executed in {} ms", joinPoint.getSignature(), (end - start));
		return proceed;

	}
}
