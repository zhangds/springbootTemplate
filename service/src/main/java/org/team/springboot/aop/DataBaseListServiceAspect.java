package org.team.springboot.aop;

import com.alibaba.fastjson2.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author zhangds
 * @date 2024/7/10
 * @Notes
 **/

@Aspect
@Component
public class DataBaseListServiceAspect {

    // 开始执行
    @Before("execution(* org.team.springboot.service.DataBaseListService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before " + joinPoint.getSignature().getName() + " method call");
    }

    // 方法执行完
    @After("execution(* org.team.springboot.service.DataBaseListService.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After " + joinPoint.getSignature().getName() + " method call");
    }

    // 结果获取
    @AfterReturning(pointcut = "execution(* org.team.springboot.service.DataBaseListService.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Method " + joinPoint.getSignature().getName() + " returned: " + JSON.toJSONString(result));
    }

    // 修改参数并执行,可以阻断执行
    //@Around("execution(* org.team.springboot.service.DataBaseListService.*(..))")
    public Object logAndModifyParams(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取原始参数
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof String) {
            String originalInput = (String) args[0];
            // 修改参数
            System.out.println("Original1 input: " + originalInput);
            String modifiedInput = originalInput.toUpperCase();
            args[0] = modifiedInput;
        }

        // 执行目标方法
        Object result = joinPoint.proceed(args);

        // 输出日志
        System.out.println("Original input: " + args[0]);
        System.out.println("Result: " + result);

        return result;
    }

    // 结果修改
    @Around("execution(* org.team.springboot.service.DataBaseListService.*(..))")
    public Object logAndModifyResult(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed(); // 执行目标方法
        String r = JSON.toJSONString(result);
        System.out.println("Original result: " + r);
        // 修改返回值
        if (r instanceof String) {
            r = r.toUpperCase();
        }

        System.out.println("Modified result: " + r);
        return result;
    }

}
