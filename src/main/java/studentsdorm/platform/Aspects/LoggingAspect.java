package studentsdorm.platform.Aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class LoggingAspect {

    @Around("@within(studentsdorm.platform.Student.*)")
    public Object log (ProceedingJoinPoint thisJoinPoint) throws Throwable {
//        log.info("CALL METHOD " + methodName + " with args " + methodArgs);
//        Object result = thisJoinPoint.proceed();
//        log.info("METHOD " + methodName + " returns " + result);
//
//        return result;
        return null;
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {
    }
}

