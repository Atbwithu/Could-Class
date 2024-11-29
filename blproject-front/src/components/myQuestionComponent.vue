<template>
  <div>
    <el-table :data="questionByUser" style="width: 100%" height="150" size="mini">
      <el-table-column fixed prop="questions" label="问题" width="100">
      </el-table-column>
      <el-table-column prop="courseName" label="课程名称" width="120">
      </el-table-column><el-table-column prop="status" label="状态" width="">
            <template slot-scope="scope">
              <span v-if="scope.row.status === 1">已回复</span>
              <span v-else-if="scope.row.status === 0">未回复</span>
            </template>
          </el-table-column>
      <el-table-column prop="createTime" label="提问日期" width="100">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from "axios";
export default {
  // name: 'Myquestion',
  data() {
    return {
      questionByUser: []
    }
  },
  methods: {
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
  created(){
    this.userQuestion()
  }
}
</script>

<style>
.content_question {
  width: 500px;
}
</style>