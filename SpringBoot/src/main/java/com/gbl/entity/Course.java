package com.gbl.entity;

import lombok.*;
/**
 * @Author: Insight
 * @Description: 课程类别--实体类
 * @Date: 2024/5/1 15:29
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//课程entity
public class Course {
    private Integer id;
    private Integer fatherId; //方向id
    private Integer isDelete; //是否删除
    private Integer kidId; // 类别id
    private String courseName; //课程名称
    private String courseTime;//课程时长
    private String category;//课程类别
    private String teacher;//导师
    private int order;//排序
    private String cover; //封面
    private int rec; //推荐状态
    private String createTime;//创建时间

    public Course(Integer id, String courseName, String courseTime, String teacher, int order, String cover) {
        this.id = id;
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.teacher = teacher;
        this.order = order;
        this.cover = cover;
    }



    public Course(String courseName, String teacher, int rec) {
        this.courseName = courseName;
        this.teacher = teacher;
        this.rec = rec;
    }

    public Course(Integer fatherId, Integer kidId, String courseName, String courseTime, String category, String teacher, int order, String cover, int rec, String createTime) {
        this.fatherId = fatherId;
        this.kidId = kidId;
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.category = category;
        this.teacher = teacher;
        this.order = order;
        this.cover = cover;
        this.rec = rec;
        this.createTime = createTime;
    }

    public Course(String courseName, String courseTime, String category, String teacher, int order, String cover, int rec) {
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.category = category;
        this.teacher = teacher;
        this.order = order;
        this.cover = cover;
        this.rec = rec;
    }

    public Course(String courseName, String courseTime, String teacher, int order, int rec) {
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.teacher = teacher;
        this.order = order;
        this.rec = rec;
    }
}

