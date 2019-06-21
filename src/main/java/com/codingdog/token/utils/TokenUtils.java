package com.codingdog.token.utils;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName TokenUtils
 * @Description 1. 用户调用获取token方法, 得到一个有效期为 1小时 的 token, token 保证全局唯一 <br>
 * 2. 验证token, 如果没有找到token,返回false, 如果找到了, 返回true,同时删除这个token.
 * @Author ly
 * @Date 2019-05-29 20:06
 * @Version 1.0
 */
@Component
public class TokenUtils {

    private Map<String, String> map = new ConcurrentHashMap<>();

    /**
     * 得到token
     * 注意线程安全问题
     *
     * @return
     */
    public String getToken() {
        String token = "token" + UUID.randomUUID().toString();
        map.put(token, token);
        return token;
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public boolean checkToken(String token) {
        if (map.containsKey(token)) {
            map.remove(token);
            return true;
        }
        return false;
    }

}
