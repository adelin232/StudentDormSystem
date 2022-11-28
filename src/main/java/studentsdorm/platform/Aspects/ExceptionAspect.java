package studentsdorm.platform.Aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class ExceptionAspect {

    @AfterThrowing(pointcut="execution(* *(..))", throwing="exception")
    public void processException(RuntimeException exception) {
        log.info(exception.getMessage());
    }
}
