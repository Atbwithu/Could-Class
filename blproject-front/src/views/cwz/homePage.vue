<template>
  <div class="homePage">
    <div class="nav">
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" style="margin: 0 auto;width:1080px">
        <el-menu-item index="1" @click="$router.push('/homePage')">首页</el-menu-item>
        <el-menu-item index="2" @click="$router.push('/subjectPage')">课程列表</el-menu-item>
        <!-- 头像区域 -->
        <!-- 默认头像 -->
        <div class="avatar" v-if="userInfo.imageUrl" style="float: right;">
            <el-dropdown>
              <img :src="userInfo.imageUrl" alt="User userInfo" class="" />
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item icon="el-icon-close">
                  <router-link to="/PersonalCenter">个人中心</router-link>
                </el-dropdown-item>
                <div @click="outLog()">
                  <el-dropdown-item icon="el-icon-close">退出登录</el-dropdown-item>
                </div>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        <!-- 自定义头像 -->
        <div class="avatar" v-else style="float: right;">
            <el-dropdown>
              <el-avatar :size="40" :src="circleUrl">
                <i class="el-icon-user" style="font-size: 20px"></i>
              </el-avatar>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item icon="el-icon-close">
                  <router-link to="/PersonalCenter">个人中心</router-link>
                </el-dropdown-item>
                <div @click="outLog()">
                  <el-dropdown-item icon="el-icon-close">退出登录</el-dropdown-item>
                </div>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
      </el-menu>
    </div>
    <router-view></router-view>
  </div>
    
</template>

<script>
import axios from 'axios';
export default {
  name: 'homePage',
  data () {
    return {
      activeIndex:'1',
      userInfo:[],

    }
  },
  methods:{
    handleSelect(key, keyPath) {
      
    },
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
  },
  created(){
    const userInfoStr = localStorage.getItem("userInfo");
    const userInfoObj = JSON.parse(userInfoStr);
    this.userInfo=userInfoObj
  }
}
</script>

<style scoped>
.nickname {  
  /* 昵称的样式 */  
  font-size: 16px;  
} 
.avatar {
    margin: 0px;
    height: 40px;
    width: 40px;
    line-height: 40px;
    margin-top: 10px;
}
.avatar img {
  width: 100%;
  border-radius: 100%;
}
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
/* 走马灯样式开始 */
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
/* 走马灯样式结束 */
.intro-img{
  margin-top: 20px;
  width: 100%;
}

.nav{
  width: 100%;
  background-color: #fff;
}

.body{
  width: 1080px;
  margin: 20px auto;
  display: flex;
  justify-content: space-between;
}
.content{
  width: 700px;
}
.function{
  width: 360px;
  height: 300px;
}
</style>
