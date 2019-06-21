package com.codingdog.token.repo;

import com.codingdog.token.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserRepo
 * @Description TODO
 * @Author ly
 * @Date 2019-05-29 18:35
 * @Version 1.0
 */
public interface UserRepo extends MongoRepository<User,String> {
}
