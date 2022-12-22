package com.alltech.offre.aspect.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Log {
	static java.util.logging.Logger log = java.util.logging.Logger.getLogger("Aspect Logger");

	@Around("@annotation(com.alltech.offre.aspect.log.Logger)")
	public Object logMethodData(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		// Log the name of the method that is being called
		log.info("Calling method: " + joinPoint.getSignature().getName());

		// Log the method arguments
		log.info("Method arguments: " + Arrays.toString(joinPoint.getArgs()));

		// Get the start time for the method call
		long startTime = System.currentTimeMillis();
		try {
			result = joinPoint.proceed();
			log.info("Method return value: " + result);

		} catch (Throwable t) {
			// log the exception here
			log.info("Exception is:" + t.getMessage());
			throw t;

		} finally {
			long endTime = System.currentTimeMillis();
			long duration = endTime - startTime;
			// log the duration here
			log.info("Method processing time: " + duration + " ms");

		}
		// Return the method return value
		return result;
	}
}
