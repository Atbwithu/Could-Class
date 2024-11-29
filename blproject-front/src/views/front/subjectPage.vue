<template>
  <div>
    <!-- 面包屑头 -->
    <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>课程列表</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <!-- 课程列表 -->
    <div class="box2">
      <el-card class="box-card">
        <div class="text item">
          <!-- 标题搜索框 -->
          <div>
            <span style="float: left;">
              <h3>课程列表</h3>
            </span>
            <ElForm :model="findSubject">
              <ElFormItem label="">
                <span style="float: right;">
                  <el-input placeholder="搜索感兴趣的课程" size="mini" v-model="findSubject.courseName" clearable
                    style="width: 200px; padding-right: 10px;">
                  </el-input><el-button size="mini" @click="find()" icon="el-icon-search" circle></el-button>
                </span>
              </ElFormItem>
            </ElForm>
          </div>
          <!-- 分类选项 -->
          <div>

            <el-link type="success" :disable="true" :underline="false">方向：</el-link>
            <el-link id="type" @click="selectAll" :underline="false">全部</el-link>
            <el-link id="type" :underline="false" type="primary" v-for="(item, index) in typeList" :key="index"
              @click="selectKind(item.id)">{{ item.name }}</el-link>
          </div>
          <el-divider></el-divider>
          <div>
            <el-link type="success" :disable="true" :underline="false">类别</el-link>
            <el-link id="type" :underline="false" @click="selectAll">全部</el-link>
            <el-link id="type" :underline="false" type="primary" v-for="(item, index) in typeChildren" :key="index"
              @click="findByChildren(item.id)">{{ item.cname }}</el-link>
          </div>
        </div>
      </el-card>
    </div>
    <!-- 展示列表 -->
    <div class="box3">
      <el-scrollbar scrollbar-y="true">
        <el-card id="lin" class="box-card2">
          <!-- 最热最新标签 -->
          <span>
            <el-button type="danger" size="mini" @click="hot()">最热</el-button>
            <el-button type="primary" size="mini" @click="news()">最新</el-button>
          </span>
          <!-- 列表 -->
          <div  style="max-height:600px; overflow-y: auto;">
            <!-- 课程 -->
            <ul>
              <li v-for="(sub, index) in subject" :key="index" style="margin: 20px 0;">
                <el-card style="padding-bottom: 20px" class="content" v-if="sub.isDelete!=1">
                  <img :src="sub.cover" style="width:250px; height:140px;" />

                  <el-link @click="toChapter(sub.id)" :underline="false">{{ sub.courseName }}</el-link>
                  <div>
                    <span style="float: left; color: red;">共10章</span>
                    <span style="float: right;">{{ sub.courseTime }}时</span>
                  </div>
                </el-card>
              </li>
            </ul>
          </div>
          <!-- 分页foot部分 -->

          <!-- 分页组件 -->
          
        </el-card>
      </el-scrollbar>

    </div>
  </div>
</template>
<script>
import axios from "axios";
import Cookie from "vue-cookie";
export default {
  data() {
    return {

      activeName: 'first',
      src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
      typeList: [],
      typeChildren: [],
      subject: [],
      findSubject: {},
      ask: {},
      course: {},
      
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1)
    },
    async handleClick(tab, event) {
      console.log(tab, event);
    },
    async res() {
      try {
        const resp = await axios.get(this.$url + "/category/list");
        this.typeList = resp.data;
      } catch (error) {
        console.error('获取分类列表出错:', error);
      }
    },
    async res2() {
      try {
        const resp = await axios.get(this.$url + "/course/list");
        this.subject = resp.data;
        
      } catch (error) {
        console.error('获取主题列表出错:', error);
      }
    },
    // 查询课程
    async find() {
      axios({
        url: this.$url + "/course/list",
        method: "post",
        params: this.findSubject
      }).then((resp) => {
        this.subject = resp.data;
        
      })
    },
    // 类别分类
    async selectKind(id) {
      axios({
        url: this.$url + "/category/findChildName?id=" + id,
        method: "get",
      }).then((resp) => {
        this.typeChildren = resp.data;
        this.findByFather(id)
      })

    },
    // 类别分类
    async selectAll() {
      this.res2()
      axios({
        url: this.$url + "/category/findChild",
        method: "get",
      }).then((resp) => {
        this.typeChildren = resp.data;
      })
    },
    // 进入章节课程
    async toChapter(id) {
      Cookie.set("courseId", id)
      this.$router.push('/chapterPage')
    },
    // 标记课程
    async test() {

      let courseInfo = localStorage.getItem("course");
      this.course = JSON.parse(courseInfo);
      this.ask = this.course
      console.log(this.ask);
    },
    // 父类别查询课程
    findByFather(id) {
      axios({
        method: 'get',
        url: this.$url + "/course/fatherCourse?id=" + id,
      }).then((resp) => {
        this.subject = resp.data
        
      })
    },
    // 子类别查询课程
    findByChildren(id) {
      axios({
        method: 'get',
        url: this.$url + "/course/childCourse?id=" + id,
      }).then((resp) => {
        this.subject = resp.data
        
      })
    },
    //热门课程
    news(){
      axios({
        method:'post',
        url: this.$url + "/course/newCourses" ,
        
      }).then((resp)=>{
       this.subject= resp.data
      })
    },
    //热门课程
    hot(){
      axios({
        method:'post',
        url: this.$url + "/course/hotCourse" ,
        
      }).then((resp)=>{
       this.subject= resp.data
      })
    }
    
    // 加载数据
    // loadData() {
    //   axios({
    //     url: this.$url + "/course/list",
    //     method: "get",
    //     params: {
    //       page: this.currentPage,
          
    //     }
    //   }).then((resp) => {
    //     this.subject = resp.data.items;
        
    //   });
    // },
    
  },

  async created() {
    await this.res();
    await this.res2();
    this.selectAll();
    this.loadData();
  },
  
};
</script>
<style>
/*  */
.el-breadcrumb {
  width: 80%;
  height: 25px;
  margin: 0 auto;
}

.el-breadcrumb-item {
  background-color: rgb(184, 181, 181);
}

.box2 {
  width: 80%;
  margin: 0 auto;

}

/*  */
.box-card {

  width: 100%;
  margin: 0 auto;

}

.box-card2 {

  width: 100%;
  margin: 0 auto;

}

/* 课程 */

.ul1 li {
  padding: 15px;

  display: inline-block;
}


.box3 {
  padding-top: 50px;
  width: 80%;
  margin: 0 auto;
}

#lin {
  width: 100%;
}

.litleBox {
  margin: 50px;
  display: inline-block;
  width: 350px;
  border: 1px solid;
}

.content {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  padding-right: 30px;
  margin-right: 30px;
  width: 300px;
  height: 200px;
}
ul{
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  list-style-type: none;
}
/* 总 */
.opp {
  top: 200px;
}

/* 分类列表 */
#type {
  margin-left: 40px;
  display: inline-block;
  padding-top: 5px;
  text-align: center;
}

#type:hover {
  background-color: #4652ff;
  border-radius: 3px;
  color: aliceblue;
}

/* 全部分类按钮 */
</style>