package com.test.dao;

import com.test.domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
}
