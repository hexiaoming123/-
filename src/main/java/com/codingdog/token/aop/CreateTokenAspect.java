package com.codingdog.token.aop;

import com.codingdog.token.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName TokenAspect
 * @Description TODO
 * @Author ly
 * @Date 2019-05-29 20:29
 * @Version 1.0
 */
@Slf4j
@Aspect
@Component
public class CreateTokenAspect {

    @Autowired
    TokenUtils tokenUtils;

    /**
     * 对直接进行切面判定, 可以换成指定的类.
     */
    @Pointcut("@annotation(com.codingdog.token.aop.CreateTokenAop)")
    public void aop() {
    }

    /**
     * 环绕通知
     *
     * @return
     * @throws Throwable
     */
    @Before("aop()")
    public void doBefore() throws Throwable {
        System.out.println("dobefore");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        request.setAttribute("token", tokenUtils.getToken());
    }
}
