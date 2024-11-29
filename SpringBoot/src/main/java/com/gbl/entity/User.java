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
@Getter
@Setter
public class User {
    private int id;
    private String userName; //用户姓名
    private String roleName; //用户姓名
    private String account; //账号
    private String password;
    private String sex;
    private String phone;
    private String imageUrl;
    private String remake;
    private String email;
    private int status;
    private String createTime;

    @Transient
    private int roleId;

    @Transient
    private String token;

    public User(String userName, String account, String password, String sex, String phone, String imageUrl, String remake, String email, int status, String createTime, int roleId, String token) {
        this.userName = userName;
        this.account = account;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.remake = remake;
        this.email = email;
        this.status = status;
        this.createTime = createTime;
        this.roleId = roleId;
        this.token = token;
    }

    public User(String account, String password) {
    }

}
