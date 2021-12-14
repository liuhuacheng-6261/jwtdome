package com.nbury.jwtdemo.entity;

import lombok.Data;

/**
 * @author liuhuacheng
 * @version 1.0
 * @description com.nbury.jwtdemo.entity
 * @date 2021/12/14
 */
@Data
public class User {

    private Integer id;
    private String username;
    private String password;

}
