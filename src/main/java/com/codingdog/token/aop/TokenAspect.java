package com.codingdog.token.aop;

import com.codingdog.token.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
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
import java.util.Arrays;

/***
 * description  TokenAspect
 * @author heliming
 * @date 2019/6/22 0022 上午 12:47
 **/
@Slf4j
@Aspect
@Component
public class TokenAspect {

    @Autowired
    TokenUtils tokenUtils;

    /**
     * 对直接进行切面判定, 可以换成指定的类.
     */
    @Pointcut("@annotation(com.codingdog.token.aop.TokenAop)")
    public void aop() {
    }

    /**
     * 环绕通知
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("aop()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取request,response
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        // 得到请求头是否有 token ,没有就从参数找
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        if (StringUtils.isEmpty(token)) {
            responseResult(response, 500, "没有token");
            return null;
        }
        //
        boolean b = tokenUtils.checkToken(token);
        if (!b) {
            responseResult(response, 500, "请勿重复提交");
            return null;
        }
        /*这个是后来加的如果必须手动刷新页面就去掉这行*/
        responseResult(response, 200, "添加成功");
        return joinPoint.proceed();
    }

    /**
     * 自定义响应结果
     *
     * @param response 响应
     * @param code     响应码
     * @param message  响应信息
     */
    private void responseResult(HttpServletResponse response, Integer code, String message) {
        response.resetBuffer();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println("{\"code\":" + code + " ,\"message\" :\"" + message + "\"}");
            response.flushBuffer();
        } catch (IOException e) {
            log.error(" 输入响应出错 e = {}", e.getMessage(), e);
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }
}
