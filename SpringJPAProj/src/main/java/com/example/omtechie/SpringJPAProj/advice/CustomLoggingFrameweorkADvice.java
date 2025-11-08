package com.example.omtechie.SpringJPAProj.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CustomLoggingFrameweorkADvice {

	@Around(value = "execution(* com.example.omtechie.SpringJPAProj.controller.*.*(..))")
	public Object CaptureLoggingDetails(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("Loggingadvice::CaptureLoggingDetails {}", joinPoint.getSignature());
		log.info("Loggingadvice::CaptureLoggingDetails {}", new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
		Object obj = joinPoint.proceed();
		log.info("Loggingadvice::CaptureLoggingDetails {}", joinPoint.getSignature());
		log.info("Loggingadvice::CaptureLoggingDetails {}", new ObjectMapper().writeValueAsString(obj));
		log.info("Logging Aspect Called");
		return obj;
	}
}
