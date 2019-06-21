package com.codingdog.token.controller;

import com.codingdog.token.aop.CreateTokenAop;
import com.codingdog.token.aop.TokenAop;
import com.codingdog.token.bean.User;
import com.codingdog.token.service.IUserService;
import com.codingdog.token.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author ly
 * @Date 2019-05-29 18:37
 * @Version 1.0
 */
@Controller
@CrossOrigin(origins = "*", allowCredentials = "true")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @ResponseBody
    @RequestMapping("/token")
    public String getToken(){
        String token =  tokenUtils.getToken();
        return token;
    }


    /**
     * 暂时没用到。如果是登录的话可以考虑这种,这种是返回model的
     *
     * 使用aop,在前置通知的时候, 就自动返回 token, 避免每次都手动 getToken
     * @return
     */
    //@ResponseBody
    @RequestMapping("/")
    @CreateTokenAop
    public String hello(){
        return "index";
    }

    /**
     * 环绕通知, 判定是否有token
     * @return
     */
    @ResponseBody
    @RequestMapping("/addUser")
    @TokenAop
    public String addUser(){
        User user = userService.addUser();
        return user.toString();
    }
}
