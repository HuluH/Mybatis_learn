package com.test.dao;

import com.test.entry.User;

import java.util.List;

public interface IUserDao {
    List<User> findUserAndAccount();

}
