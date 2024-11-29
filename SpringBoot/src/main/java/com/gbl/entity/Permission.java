package com.gbl.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Permission {
    private int permissionId;
    private String name;
    private String parentFeature;//父功能
    private String location;//位置
    private int sort;//排序
    private String featureDescription;//功能说明

    public Permission(String name, String parentFeature, String location, int sort, String featureDescription) {
        this.name = name;
        this.parentFeature = parentFeature;
        this.location = location;
        this.sort = sort;
        this.featureDescription = featureDescription;
    }
}
