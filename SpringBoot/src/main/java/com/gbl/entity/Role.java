package com.gbl.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    private int roleId;
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
