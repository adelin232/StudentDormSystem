package studentsdorm.platform.Aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class LoggingAspect {

    @Around("execution(* studentsdorm.platform.Student.StudentService.createStudent(..))")
    public void logAroundCreateStudent(ProceedingJoinPoint joinPoint) {

    }
}

