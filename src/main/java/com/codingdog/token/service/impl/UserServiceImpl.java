package com.codingdog.token.service.impl;

import com.codingdog.token.bean.User;
import com.codingdog.token.repo.UserRepo;
import com.codingdog.token.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author ly
 * @Date 2019-05-29 18:36
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User addUser() {
        User user = User.builder()
                .id(System.currentTimeMillis() + "")
                .age(12)
                .gender("1231")
                .name("hahah")
                .build();
        User s = userRepo.save(user);
        return s;
    }
}
