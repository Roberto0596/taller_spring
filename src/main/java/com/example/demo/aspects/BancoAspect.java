package com.example.demo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.example.demo.beans.OperationTO;

@Aspect
@Component
public class BancoAspect {
	private static final Logger log = LoggerFactory.getLogger(BancoAspect.class);	
	
	@Around("within (com.example.demo.controller..*)")
	public Object doAspect(ProceedingJoinPoint join) throws Throwable {
		log.info("Entre  aspecto y lo ejecutaré");
		return join.proceed();
	}
}
