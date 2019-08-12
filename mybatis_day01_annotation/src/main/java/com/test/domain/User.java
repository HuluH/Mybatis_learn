package com.test.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class User {
    private int id;
    private String username;
    private Date birthday;
    private char sex;
    private String address;
}
