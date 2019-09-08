package com.test.entry;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Account {
    private int id;
    private String name;
    private float money;
}
