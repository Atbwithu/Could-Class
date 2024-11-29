<template>
  <div class="nav">
    <div>
      <el-carousel :interval="5000" arrow="always" type="card" style="margin: 0 auto;width:1080px">
        <el-carousel-item v-for="(item, index) in newCourse" :key="index">
          <img width="100%" height="100%" :src="item.cover" :alt="`Image ${index + 1}`" />
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="body">
      <div class="box1">
        <el-card class="content">
          <h3>热门课程</h3>
          <div style="max-height: 400px; overflow-y: auto;">
            <ul>
              <li v-for="course in hotCourse" :key="course.id" style="padding-right: 20px;">
                <div class="course-item">
                  <!-- 课程图片 -->
                  <img :src="course.cover" style="width: 200px; height: 120px;" alt="Course Cover" class="course-image">

                  <!-- 课程标题 -->
                  <h3><el-link @click="toChapter(sub.id)" :underline="false">{{ course.courseName }}</el-link></h3>

                  <!-- 课程描述 -->
                  <p>{{ course.description }}</p>

                  <!-- 时长与讲师信息 -->
                  <p>时长：{{ course.courseTime }} 小时</p>
                  <p>讲师：{{ course.teacher }}</p>
                </div>
              </li>
            </ul>
          </div>
        </el-card>
      </div>
      <div class="box2">
        <el-card class="function">
          <h3>最新课程</h3>
          <div style="max-height: 400px; overflow-y: auto;">
            <ul>
              <li v-for="course in newCourse" :key="course.id" style="padding-right: 20px;">
                <div class="course-item">
                  <!-- 课程图片 -->
                  <img :src="course.cover" style="width: 200px; height: 120px;" alt="Course Cover" class="course-image">

                  <!-- 课程标题 -->
                  <h3><el-link @click="toChapter(sub.id)" :underline="false">{{ course.courseName }}</el-link></h3>

                  <!-- 课程描述 -->
                  <p>{{ course.description }}</p>

                  <!-- 时长与讲师信息 -->
                  <p>时长：{{ course.courseTime }} 小时</p>
                  <p>讲师：{{ course.teacher }}</p>

                </div>
              </li>
            </ul>
          </div>
        </el-card>
      </div>

    </div>
  </div>
</template>

<script>
import axios from "axios";
import Cookie from "vue-cookie";
export default {
  name: 'HomeContent',
  data() {
    return {
      src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
      typeList: [],
      hotCourse: [],
      questionByUser: [],
      collectCourse: [],
      newCourse: [],
      activeIndex: '1',
      imgList: [
        require('@/assets/OIP-C.jpg'),
        require('@/assets/cxk.jpg'),
        require('@/assets/kun.jpg'),
      ],
    }
  },
  methods: {

    async res1() {
      try {
        const resp = await axios.get(this.$url + "/course/newCourse");
        this.newCourse = resp.data;
      } catch (error) {
        console.error('获取分类列表出错:', error);
      }
    },
    async res2() {
      try {
        const resp = await axios.get(this.$url + "/course/hotCourse");
        this.hotCourse = resp.data;
      } catch (error) {
        console.error('获取分类列表出错:', error);
      }
      console.log(this.hotCourse);
    },
    // 进入章节课程
    async toChapter(id) {

      Cookie.set("courseId", id)
      console.log(id);
      this.$router.push('/chapterPage')
    },


  },
  created() {
    this.res1()
    this.res2()
    localStorage.getItem("userInfo");
  }
}
</script>

<style scoped>
/* 全局样式调整 */
body,
html {
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
  /* 选择一个常见的无衬线字体 */
  background-color: #f5f5f5;
  /* 浅灰色背景 */
}

/* 导航栏样式 */
.nav {
  background-color: #fff;
  padding: 20px 0;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  /* 添加阴影效果 */
}

/* 走马灯样式 */
.el-carousel {
  max-width: 100%;
  margin: 0 auto;
}

.el-carousel__item img {
  border-radius: 8px;
  /* 为图片添加圆角 */
  object-fit: cover;
  /* 保持图片比例填充容器 */
}

/* 热门课程和内容区域样式 */
.body {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 20px;
}

.content,
.function {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  margin-right: 30px;
  width: 95%;
  height: 60vh;
}

.course-item {
  border: 1px solid #eee;
  border-radius: 8px;
  padding: 10px;
  margin-bottom: 20px;
  transition: box-shadow 0.3s ease;
}

.course-item:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.course-image {
  width: 100px;
  height: 60px;
  object-fit: cover;
  border-radius: 4px;
  float: left;
  margin-right: 10px;
}

/* 表格样式 */
.el-table {
  width: 100%;
  margin-top: 20px;
}

.el-table th,
.el-table td {
  text-align: left;
  padding: 8px;
}

/* 链接样式 */
.el-link {
  color: #007bff;
  text-decoration: none;
  transition: color 0.3s ease;
}

.el-link:hover {
  color: #0056b3;
}

/* 响应式设计（可选） */
@media (max-width: 768px) {
  .body {
    flex-direction: column;
  }

  .content,
  .function {
    width: 100%;
    margin-bottom: 20px;
  }
}

ul {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  list-style-type: none;
}
.box1{
  flex: 1;
}
.box2{
  flex: 1;
}
</style>