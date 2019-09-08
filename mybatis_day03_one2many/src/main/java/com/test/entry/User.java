package com.test.entry;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {
    private int id;
    private String username;
    private Date birthday;
    private char sex;
    private String address;
    private List<Account> accounts;
}