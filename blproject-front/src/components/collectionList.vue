<template>
  <div>
    <div class="content">
      <el-input placeholder="请输入内容" class="input-with-select" style="width:64%">
        <el-button slot="append" icon="el-icon-search"></el-button>
      </el-input>
    </div>
    <el-table ref="singleTable" :data="collectCourse" highlight-current-row style="width: 100%">
      <el-table-column type="index" width="50">

      </el-table-column>
      <el-table-column property="courseName" label="课程名称" width="120">
      </el-table-column>
      <el-table-column property="createTime" label="收藏日期" width="140">
      </el-table-column>
      <el-table-column property="" label="操作">
        <el-popconfirm title="确定取消收藏吗">
          <el-button class="Unfavorite" slot="reference">取消收藏</el-button>
        </el-popconfirm>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import axios from 'axios';
export default {
  // name: 'Collection',
  data() {
    return {
      // 分页
      collectCourse: [],
      currentRow: null,
    }
  },
  methods: {
    courseList() {
      let user = localStorage.getItem("userInfo");
      this.UserInfo = JSON.parse(user);
      axios({
        url: this.$url + "/study/userStudy?id=" + this.UserInfo["id"],
        method: "get"
      }).then((resp) => {
        this.collectCourse = resp.data
        console.log(this.collectCourse);
      })
    },
  },
  created() {
    this.courseList()
  }
};
</script>

<style>
.Unfavorite {
  background-color: rgb(44, 153, 247);
  color: white;
}

.content {
  width: 240px;
}
</style>