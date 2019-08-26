package com.test.dao;

import com.test.entry.User;

import java.util.List;

public interface IUserDao {

    List<User> findAll();

    User findOne(int id);

    void add(User user);

    void update(User user);

    void delete(int id);

    List<User> findHalf(String username);
}
