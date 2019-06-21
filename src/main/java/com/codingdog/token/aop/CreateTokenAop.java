package com.codingdog.token.aop;

import java.lang.annotation.*;

/**
 * @ClassName TokenAop
 * @Description TODO
 * @Author ly
 * @Date 2019-05-29 20:27
 * @Version 1.0
 */
@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateTokenAop {
}
