package com.example.demo.aspect;

import com.example.demo.model.SalesTransaction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterReturning(pointcut = "execution(* com.example.demo.service.SalesService.updateSales(..)) && args(id, salesDetails)", returning = "result")
    public void logAfterUpdateSales(JoinPoint joinPoint, Long id, com.example.demo.model.Sales salesDetails, Object result) {
        logger.info("Updated Sales ID: {}", id);
        for (SalesTransaction transaction : salesDetails.getTransactions()) {
            logger.info("Updated Transaction - Product: {}, Quantity: {}, Price: {}", transaction.getProduct(), transaction.getQuantity(), transaction.getPrice());
        }
    }
}
