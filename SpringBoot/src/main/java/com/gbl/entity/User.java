package com.gbl.entity;

import lombok.*;
import org.springframework.data.annotation.Transient;

/**
 * @Author: Insight
 * @Description: 用户--实体类
 * @Date: 2024/5/1 15:29
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String userName; //用户姓名
    private String roleName; //用户姓名
    private String account; //账号
    private String password;
    private String passwordSalt;
    private String sex;
    private String phone;
    private String imageUrl;
    private String remake;
    private String email;
    private int status;
    private Long createTime;

    @Transient
    private int roleId;

    @Transient
    private String token;

}
