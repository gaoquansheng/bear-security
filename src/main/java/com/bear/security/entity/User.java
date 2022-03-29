package com.bear.security.entity;

import lombok.Data;

import java.util.List;

/**
 * 普通用户实体
 */
@Data
public class User {

    private String id;

    private String username;

    private String password;

    private Boolean enabled;

    private String address;

    private String phone;

    private List<String> authority;
}
