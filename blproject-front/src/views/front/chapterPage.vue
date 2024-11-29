<!-- 章节页 -->breadcrumb
<template>
  <div class="box01">
    <!-- 面包屑头 -->
    <div style="background-color: gray; width: 60%; margin: 0 auto; border-radius: 5px 5px 5px 5px;">
      <div style="padding-top: 10px; margin-bottom: 20px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>课程列表</el-breadcrumb-item>
          <el-breadcrumb-item>课程章节</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
    </div>
    <!-- 课程详情 -->
    <div class="box2">
      <el-card class="box-card">
        <!-- 图片 -->
        <div style="float: left; padding-left: 100px;">
          <el-image :src="subjectInfo.cover" style="width: 300px;  height: 165px; border-radius: 5px;"></el-image>
        </div>
        <div style="float: right;width: 350px; height: 100px;" class="info">
          <el-card style="width: 100%; height: 165px;">
            <!-- 标题 -->
            <span>
              <h1 style="float: left;">{{ subjectInfo.courseName }}</h1>
            </span>
            <span style="float: right;">
              <el-button type="warning" icon="el-icon-star-off" circle size="mini" @click="collectCourse()"></el-button>
              <el-button type="primary" icon="el-icon-back" circle size="mini" @click="goBack"></el-button>
            </span>
            <br>
            <br>
            <hr>
            <!-- 简介 -->
            <div>
              <span style="float: left;">
                讲师：{{ subjectInfo.teacher }}<br>
              </span>
              <span style="float: right ;">
                学时：{{ subjectInfo.courseTime }}<br>
              </span>
            </div>
          </el-card>
        </div>
      </el-card>
    </div>
    <!-- 章节目录 -->
    <div class="box3">
      <el-card id="lin" class="box-card2">
        <!-- 章节标题 -->
        <el-page-header @back="goBack" content="章节目录"></el-page-header>
        <hr>
        <!-- 章节列表 -->
        <el-collapse v-model="activeName" accordion>
          <el-collapse-item v-for="(chapter, index) in chapterList" :key="index" :name="`panel-${index}`"
            :title="chapter.name" @click.native="handleChange(chapter.id)">
            
            <el-link type="primary" style="padding-left: 40px;" v-for="(item, index) in videoList" :key="index">
              <el-link :underline="false" type="primary" 
                @click="toVideo(item.id,chapter.id)">{{ item.videoName }}</el-link>
              <i class="el-icon-video-camera"></i>
              <i class="el-icon-caret-right"></i>
            </el-link>
          </el-collapse-item>
        </el-collapse>

      </el-card>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import Cookie from "vue-cookie";
export default {

  data() {
    return {
      count: 0,
      activeName: 'first',
      subjectInfo: {
        
      },
      // 章节数组
      chapterList: [],
      videoList: []
    }
  },

  methods: {
    goBack(){
      this.$router.go(-1)
    },
    //收藏课程
    collectCourse() {
      let user = localStorage.getItem("userInfo");
      this.UserInfo = JSON.parse(user);
      axios({
        method: 'get',
        url: this.$url + "/course/collect?uid=" + this.UserInfo["id"] + "&cid=" + Cookie.get("courseId"),

      }).then((resp) => {
        const i = resp.data
        if (i == 0) {
          console.log();
          this.$message.success('收藏成功');
        } else {
          this.$message.success('您已收藏该课程');
        }
      })
    },
    //章节列表
    selChapter() {
      let id = Cookie.get("courseId")
      axios({
        url: this.$url + '/chapter/chapterName?id=' + id,
        method: "post",
      }).then((resp) => {
        this.chapterList = resp.data
      })

    },
    async toVideo(id,cid) {
      Cookie.set("videoId",id)
      Cookie.set("chapterId",cid)
      this.$router.push('/videoPage')
    },
    // 视频列表
    handleChange(id) {
      axios({
        url: this.$url + '/chapter/findVideo?id=' + id,
        method: "post",
      }).then((resp) => {
        this.videoList = resp.data
        
      })
    },
    //查询章节信息
    findCourseInfo(){
      axios({
        method:'get',
        url: this.$url +"/course/findCourseById?id=" + Cookie.get("courseId"),
      }).then((resp=>{
        this.subjectInfo=resp.data
      }))
    }
  },
  created() {
    this.selChapter()
    this.findCourseInfo()
    console.log(this.chapterList);
    // this.chapterUrl()
  }
};
</script>
<style>
.el-breadcrumb {
  width: 60%;
  height: 25px;
  margin: 0 auto;
}

.el-breadcrumb-item {
  background-color: rgb(184, 181, 181);
}

.box2 {
  width: 60%;
  margin: 0 auto;

}

.box-card {

  width: 100%;
  height: 200px;
  margin: 0 auto;
  overflow-y: auto;
  /* 启用垂直滚动 */

}

.box-card2 {

  width: 100%;
  height: 400px;
  margin: 0 auto;
  overflow-y: auto;
  /* 启用垂直滚动 */

}

.ul1 li {
  padding: 15px;
  float: left;
  display: inline-block;
}

/* .ul2 li{
    padding: 15px;
    float: left;
    display: inline-block;
  } */
.box3 {
  padding-top: 50px;
  width: 60%;
  height: 800px;
  margin: 0 auto;


}


#lin {
  width: 100%;
  height: 600px;
}

.litleBox {
  margin: 30px;
  display: inline-block;
  width: 160px;
  height: 160px;
  border: 1px solid;
}

/* 视频详情 */
</style>