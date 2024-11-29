<template>
  <div>

    <!-- 学生详情弹窗 -->
    <div>
      <el-dialog :visible.sync="dialogVisible" title="" style="width: 100%; margin: 0 auto;">
        <el-card class="box-card">
          <el-table :data="userCourseList" style="width: 100%;" size="small">


            <el-table-column prop="userName" label="学员名称" width="">
            </el-table-column>
            <el-table-column prop="courseName" label="课程名" width="">
            </el-table-column>
            <el-table-column prop="courseTime" label="课程课时" width="">
            </el-table-column>
            <el-table-column prop="learnTime" label="学习时长" width="">
            </el-table-column>
            <el-table-column prop="askNum" label="提问次数" width="">
            </el-table-column>
            <el-table-column prop="finishTime" label="完成率" width="">
            </el-table-column>
          </el-table>
        </el-card>
      </el-dialog>
    </div>

    <el-card class="box-card">
      <h1 style="font-size: 30px;">学员管理</h1><br>
      <hr>
      <!-- 标签及搜索 -->
      <div>
        <ElForm :model="form">
          <ElFormItem label="">
            <ElInput v-model="form.userName" placeholder="请输入搜索的学生姓名" size="mini" style="width: 200px;"></ElInput>

            <el-button size="small" icon="el-icon-search" circle @click="find()"></el-button>
          </ElFormItem>
        </ElForm>
      </div>

      <div>
        <el-table :data="tableData" style="width: 100%;" size="small" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50px">
          </el-table-column>
          <el-table-column prop="id" label="#" width="">
          </el-table-column>
          <el-table-column prop="userName" label="姓名" width="">
          </el-table-column>
          <el-table-column prop="sex" label="性别" width="">
          </el-table-column>
          <el-table-column prop="phone" label="电话" width="">
          </el-table-column>
          <el-table-column prop="collectNum" label="收藏课程数" width="">
          </el-table-column>
          <el-table-column prop="learnNum" label="学习次数" width="">
          </el-table-column>
          <el-table-column prop="questionNum" label="提问次数" width="">
          </el-table-column>
          <el-table-column prop="learnTime" label="学习时长" width="">
          </el-table-column>
          <el-table-column label="操作" width="">
            <template slot-scope="scope">
              <el-button type="button" @click="learnInfo(scope.row.id)">学习记录</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="block">
      </div>
    </el-card>


  </div>
</template>
<script>
import axios from 'axios';
export default {
  data() {
    return {
      form: {},
      answers: {},
      tableId: [], //批量删除ids
      tableData: [],
      userCourseList: [],
      dialogVisible: false,
    }
  },
  methods: {
    // 刷新页面展示数据
    res() {
      axios({
        url: this.$url + "/study/list",
        method: "get",
      }).then((resp) => {
        this.tableData = resp.data;
      })
    },
    // 查询请求
    find() {
      axios({
        url: this.$url + "/study/list",
        method: "get",
        params: this.form
      }).then((resp) => {
        if (resp.data == null) {
          this.$message.error('查询失败: ' + resp.data.message);
        } else {
          this.tableData = resp.data;
          this.$message.success('查询成功');
        }

      })
    },
    //将选中的id放入数组中
    handleSelectionChange(selection) {
      this.tableId = selection.map(item => item.id);
      console.log(this.tableId);
    },
    //批量删除的方法
    deletes() {
      axios({
        method: 'post',
        url: this.$url,
        data: this.tableId
      }).then((resp) => {
        resp.data
      })
    },
    // 用户学习记录
    learnInfo(id) {
      this.dialogVisible = true;

      axios({
        url: this.$url + "/study/userStudy?id=" + id,
        method: "get"
      }).then((resp) => {
        this.userCourseList = resp.data
      })
    }
  },


  created() {
    this.res()
  }
}
</script>