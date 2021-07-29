package passion.spring.environment.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Pointcut("execution(* passion.spring.environment.*ServiceImpl.*(..))")
    public void loggingPointcut() {}

    @Around("loggingPointcut()")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        // logger.info("log start - " + pjp.getSignature().getDeclaringTypeName());
        Object result = pjp.proceed();
        long end = System.nanoTime();
        // logger.info("log end - " + pjp.getSignature().getName());
        logger.info("소요시간 - " + (end - start) / 1_000_000 + " ms");
        return result;
    }
}
