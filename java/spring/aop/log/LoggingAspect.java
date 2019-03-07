package com.oryam.howto.common.util.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    @SuppressWarnings("squid:UnusedProtectedMethod")
    @Before("@annotation(com.oryam.howto.common.util.aspect.LogName)")
    private void logBefore(final JoinPoint joinPoint) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Calling method {}", joinPoint.getSignature().toShortString());
        }
    }

    @SuppressWarnings("squid:UnusedProtectedMethod")
    @Around("@annotation(com.oryam.howto.common.util.aspect.LogTime)")
    private Object logExecutionTime(final ProceedingJoinPoint joinPoint) throws Throwable {
        final StopWatch watch = new StopWatch();
        watch.start();

        final Object proceed = joinPoint.proceed();

        watch.stop();
        if (LOG.isTraceEnabled()) {
            LOG.trace("Executed method {} in {}ms",
                      // joinPoint.getTarget().getClass().getSimpleName(),
                      joinPoint.getSignature().toShortString(),
                      watch.getTotalTimeMillis());
        }
        return proceed;

    }

}
