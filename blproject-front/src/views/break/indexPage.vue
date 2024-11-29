<template>
    <div>
        <el-container>
            <el-header>
                <div style="display: flex; flex-direction: row;">
                    <div style="flex: 1;">
                        <h1>哥布林小课堂后台管理</h1>
                    </div>
                    <div class="info" >
                            <el-dropdown style="float: right; margin-right: 20px; height: 60px;">
                                <!-- 图片头像 -->
                                <div class="avatar" v-if="userInfo.imageUrl">
                                    <img :src="userInfo.imageUrl" alt="User userInfo" class=""
                                        style="width: 80px; height: 80px;border-radius: 100%;" />
                                </div>
                                <div class="avatar" v-else>
                                    <el-avatar :size="60" :src="circleUrl">
                                        <i class="el-icon-user" style="font-size: 20px"></i>
                                    </el-avatar>
                                </div>

                                <el-dropdown-menu slot="dropdown">
                                    <el-dropdown-item>
                                        <el-link @click="open()" target="_blank">个人信息</el-link>

                                    </el-dropdown-item>
                                    <el-dropdown-item divided>
                                        <span @click="outLog">退出登录</span></el-dropdown-item>
                                </el-dropdown-menu>
                            </el-dropdown>
                        </div>
                </div>
            </el-header>
            <el-container>
                <el-aside width="200px">
                        <!-- 头像 -->
                        <!-- 菜单 -->
                        <el-col :span="24">
                            <el-menu default-active="2" class="el-menu-vertical-demo">
                                <el-submenu index="1">
                                    <template slot="title">
                                        <span>功能</span>
                                    </template>
                                    <el-menu-item-group>
                                        <template slot="title"></template>
                                        <router-link to="/indexPage" v-if="this.userInfo.roleId == 2">
                                            <el-menu-item index="1-1">首页</el-menu-item>
                                        </router-link>
                                    </el-menu-item-group>
                                    <el-menu-item-group title="" v-if="this.userInfo.roleId == 2">
                                        <router-link to="/userPage">
                                            <el-menu-item index="1-1">用户管理</el-menu-item>
                                        </router-link>
                                    </el-menu-item-group>
                                    <el-menu-item-group title="" v-if="this.userInfo.roleId == 1">
                                        <router-link to="/role">
                                            <el-menu-item index="1-1">角色管理</el-menu-item>
                                        </router-link>
                                    </el-menu-item-group>
                                    <el-menu-item-group title="">
                                        <router-link to="/categoryPage" v-if="this.userInfo.roleId == 2">
                                            <el-menu-item index="1-1">分类管理</el-menu-item>
                                        </router-link>
                                    </el-menu-item-group>
                                    <el-menu-item-group title="" v-if="this.userInfo.roleId == 2">
                                        <router-link to="/askPage">
                                            <el-menu-item index="1-1">问答管理</el-menu-item>
                                        </router-link>
                                    </el-menu-item-group>
                                    <el-menu-item-group title="" v-if="this.userInfo.roleId == 2">
                                        <router-link to="/learnPage">
                                            <el-menu-item index="1-1">学习记录</el-menu-item>
                                        </router-link>
                                    </el-menu-item-group>
                                    <el-menu-item-group title="" v-if="this.userInfo.roleId == 2">
                                        <router-link to="/coursePage">
                                            <el-menu-item index="1-1">课程管理</el-menu-item>
                                        </router-link>
                                    </el-menu-item-group>
                                    <el-menu-item-group title="" v-if="this.userInfo.roleId == 1">
                                        <router-link to="/coursePage">
                                            <el-menu-item index="1-1">课程管理</el-menu-item>
                                        </router-link>
                                    </el-menu-item-group>
                                    <el-menu-item-group title="" v-if="this.userInfo.roleId == 1">
                                        <router-link to="/courseRecycle">
                                            <el-menu-item index="1-1">回收站</el-menu-item>
                                        </router-link>
                                    </el-menu-item-group>
                                </el-submenu>
                            </el-menu>
                        </el-col>
                </el-aside>
                <el-main>
                    <div style="width: 100%; height: 100vh;">
                        <router-view></router-view>
                    </div>
                    
                </el-main>
            </el-container>
        </el-container>
            <!-- 个人信息弹窗 -->
            <el-dialog :visible.sync="dialogVisible" title="" style="width: 80%;  margin: 0 auto;">
                <el-card class="userInfocard">
                    <div style="float: right; width: 100%;">
                        <el-tabs v-model="user">
                            <el-tab-pane label="个人信息" name="first">
                                <el-form :model="user">
                                    <el-form-item label="姓名">
                                        <el-input v-model="user.userName" placeholder=""></el-input>
                                    </el-form-item>
                                    <el-form-item label="性别">
                                        <el-radio v-model="user.sex" label="男">男</el-radio>
                                        <el-radio v-model="user.sex" label="女">女</el-radio>
                                    </el-form-item>
                                    <el-form-item label="电子邮箱">
                                        <el-input v-model="user.email" placeholder=""></el-input>
                                    </el-form-item>
                                    <el-form-item label="电话号码">
                                        <el-input v-model="user.phone" placeholder=""></el-input>
                                    </el-form-item>
                                    <el-form-item label="备注">
                                        <el-input v-model="user.remake" type="textarea" placeholder=""></el-input>
                                    </el-form-item>
                                    <el-form-item label="">
                                        <el-button type="primary" round> <i
                                                class="el-icon-edit-outline"></i>保存</el-button>
                                    </el-form-item>
                                </el-form>

                            </el-tab-pane>
                            <el-tab-pane label="收藏课程" name="second" @click="courseList()">收藏课程
                                <el-table :data="collectCourse" height="250" border style="width: 100%">
                                    <el-table-column prop="id" label="#" width="50">
                                    </el-table-column>
                                    <el-table-column prop="courseName" label="课程名称" width="180">
                                    </el-table-column>
                                    <el-table-column prop="courseTime" label="课程时长">
                                    </el-table-column>
                                    <el-table-column prop="learnTime" label="学习时长">
                                    </el-table-column>
                                    <el-table-column prop="askNum" label="提问次数">
                                    </el-table-column>
                                    <el-table-column prop="finishTime" label="完成率">
                                    </el-table-column>
                                </el-table>
                            </el-tab-pane>
                            <el-tab-pane label="提问列表" name="third">提问列表
                                <el-table :data="questionByUser" height="250" border style="width: 100%">
                                    <el-table-column prop="id" label="#" width="50">
                                    </el-table-column>
                                    <el-table-column prop="questions" label="问题" width="180">
                                    </el-table-column>
                                    <el-table-column prop="courseName" label="课程名称">
                                    </el-table-column>
                                    <el-table-column prop="status" label="状态">
                                    </el-table-column>
                                    <el-table-column prop="createTime" label="提问时间">
                                    </el-table-column>
                                </el-table>


                            </el-tab-pane>

                        </el-tabs>
                    </div>
                </el-card>

            </el-dialog>
        
    </div>
</template>
<!-- js -->
<script>
import axios from 'axios';
export default {

    data() {
        return {
            disabled: true,
            dialogVisible: false,
            drawer: false,
            direction: 'ltr',
            user: {},
            collectCourse: [],//收藏课程
            questionByUser: [],  //收藏课程
            userInfo: {},//用户信息
        };
    },
    methods: {

        outLog() {
            if (confirm("确定退出吗")) {
                axios({
                    url: this.$url + "/user/outLog",
                    method: "post",
                }).then((resp) => {
                    localStorage.removeItem("userInfo");
                    this.$router.push("/");
                });
            }
        },
        open() {
            this.dialogVisible = true
            let users = localStorage.getItem("userInfo");

            this.UserInfo = JSON.parse(users);
            this.user = this.UserInfo
            console.log(this.user);

        },
        enableInput() {
            this.$refs.input.disabled = false;
        },
        courseList() {
            let user = localStorage.getItem("userInfo");
            this.UserInfo = JSON.parse(user);
            axios({
                url: this.$url + "/study/userStudy?id=" + this.UserInfo["id"],
                method: "get"
            }).then((resp) => {
                this.collectCourse = resp.data
            })
        },
        userQuestion() {
            let user = localStorage.getItem("userInfo");
            this.UserInfo = JSON.parse(user);
            axios({
                url: this.$url + "/ask/userQuestions?id=" + this.UserInfo["id"],
                method: "get"
            }).then((resp) => {
                this.questionByUser = resp.data
            })
        }
    },
    created() {
        const userInfoStr = localStorage.getItem("userInfo");
        const userInfoObj = JSON.parse(userInfoStr);
        this.userInfo = userInfoObj
        this.courseList()
        this.userQuestion()

    }
};
</script>
<!-- 样式 -->
<style>
.el-header {
    background-color: rgb(5, 138, 27);
}




body>.el-container {
    height: 100vh;
    margin-bottom: 140px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
    line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
    line-height: 320px;
}

.info {
    width: 100%;
    height: 100px;
    flex: 1;
    
}

/* 用户弹窗样式 */
.userInfocard {
    width: 100%;
    height: 700px;
}
router-link{
    text-decoration: none;
}
</style>