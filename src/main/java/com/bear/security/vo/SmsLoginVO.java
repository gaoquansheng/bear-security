package com.bear.security.vo;

import lombok.Data;

@Data
public class SmsLoginVO {

    private String phone;
    private String code;
}
