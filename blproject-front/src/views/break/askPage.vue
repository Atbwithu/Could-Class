<template>
  <div>
    <el-card class="box-card">
      <h1 style="font-size: 30px;">问答管理</h1><br>
      <!-- 标签及搜索 -->
      <div>
        <ElForm :model="form">
          <ElFormItem label="">
            <ElInput v-model="form.userName" placeholder="请输入搜索的学生姓名" size="mini" style="width: 200px;"></ElInput>
            <ElInput v-model="form.courseName" placeholder="请输入搜索的学科" size="mini" style="width: 200px;"></ElInput>
            <el-button size="mini" icon="el-icon-search" circle @click="find()"></el-button>
            <el-button @click="batchDelete" style="float: right;" type="danger" icon="el-icon-minus"
              size="mini">批量删除</el-button>
          </ElFormItem>
        </ElForm>
      </div>
      <!-- 列表 -->
      <div>
        <el-table :data="currentPageData" style="width: 100%;" size="mini" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="80"></el-table-column>
          <el-table-column prop="id" label="#" width="">
          </el-table-column>
          <el-table-column prop="userName" label="姓名" width="">
          </el-table-column>
          <el-table-column prop="courseName" label="科目" width="">
          </el-table-column>
          <el-table-column prop="questions" label="问题" width="">
          </el-table-column>
          <el-table-column prop="status" label="状态" width="">
            <template slot-scope="scope">
              <span v-if="scope.row.status === 1">已回复</span>
              <span v-else-if="scope.row.status === 0">未回复</span>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="提问时间" width="">
          </el-table-column>
          <el-table-column label="操作" width="">
            <template slot-scope="scope">
              <el-button type="button" @click="openDialog(scope.row.id)">回答</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="block">
        <!-- 分页组件 -->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
          :page-sizes="[5, 10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
          :total="totalItems">
        </el-pagination>
      </div>
    </el-card>

    <!-- 回答弹窗 -->
    <el-dialog :visible.sync="dialogVisible" title="" style="width: 50%; margin: 0 auto;">
      <ElForm :model="answers" enctype="multipart/form-data">
        <ElFormItem label="">
          <p>
            问题：<el-input v-model="answers.questions" type="text" :disabled="true" size="mini" />
          </p>
          <p>
            回答内容：<el-input v-model="answers.answer" type="text" placeholder="请回答" size="mini" />
          </p>
          <p style="margin: 0 auto;">
            <el-button type="primary" @click="sub()" round size="mini">回答</el-button>
            <el-button type="info" round size="mini">取消</el-button>
          </p>
        </ElFormItem>
      </ElForm>
    </el-dialog>

  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      askPage: {
        userName: "",
        courseName: "",
        startNum: 1,
        sizePage: 10

      },
      userName: "",
      subject: "",
      question: "",
      form: {}, // 用于存储查询条件
      answers: {}, // 用于存储回复数据
      tableData: [], // 存储列表数据
      selectedIds: [], // 用于存储批量删除选择的 ID 数组
      dialogVisible: false, // 控制回答弹窗的显示状态
      currentPage: 1, // 当前页码，初始化为 1
      pageSize: 10, // 每页显示的条数
      totalItems: 0 // 总数据条数

    }
  },
  methods: {
    // 刷新页面展示数据
    res() {
      axios({
        url: this.$url + "/ask/list",
        method: "get",
      }).then((resp) => {
        this.tableData = resp.data;
        // 注释：获取后端返回的数据并赋值给 tableData
        this.totalItems = resp.data.length;
      })
    },
    // 查询请求
    find() {
      axios({
        url: this.$url + "/ask/list",
        method: "get",
        params: this.form
      }).then((resp) => {

        if (resp.data == null) {
          this.$message.error('查询失败: ' + resp.data.message);
        } else {
          this.tableData = resp.data;
          this.totalItems = resp.data.length;
          this.$message.success('查询成功');
        }

      })
    },
    // 根据当前页码和每页条数计算当前页数据
    getCurrentPageData() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.tableData.slice(start, end);
    },
    // 每页条数改变时的处理函数
    handleSizeChange(size) {
      this.pageSize = size;
      this.currentPage = 1;
      this.res();
    },
    // 当前页码改变时的处理函数
    handleCurrentChange(page) {
      this.currentPage = page;
    },
    // 回答弹窗
    openDialog(id) {
      this.dialogVisible = true;
      axios({
        url: this.$url + "/ask/selectById?id=" + id,
        method: "get",
      }).then((resp) => {
        this.answers = resp.data;
      })
    },
    // 回复
    sub() {
      this.dialogVisible = false;
      axios({
        url: this.$url + "/ask/answer",
        method: "post",
        params: this.answers
      }).then((resp) => {
        // 假设服务器返回的数据中包含 code 和 message 字段
        if (resp.data.code == 1) {
          this.$message.error('回答失败: ' + resp.data.message);
        } else {
          this.$message.success('回答成功');
          this.res()
          // 清空表单
          this.answers.answer = "";
        }
      })
    },
    // 处理表格选择项变化的方法
    handleSelectionChange(selection) {
      // 注释：将选择的项的 ID 存储到 selectedIds 数组中
      this.selectedIds = selection.map(item => item.id);
      console.log(this.selectedIds);
    },
    // 批量删除方法
    batchDelete() {
      // 注释：如果有选择的项
      if (confirm("确实删除用户的提问吗？")) {
        if (this.selectedIds.length > 0) {
          // 发送请求
          axios({
            method: 'post',
            url: this.$url + "/ask/deleteByIds?ids=" + this.selectedIds,

          })
            .then(response => {

              this.$message.success('删除成功!');
              this.res()
            })
            .catch(error => {
              // 处理请求失败的情况
              console.error(error);
            });
          this.selectedIds = [];
        } else {
          this.$message.warning('请先选择要删除的项');
        }
      }
    }
  },
  created() {
    this.res()
  },
  computed: {
    // 计算当前页要显示的数据
    currentPageData() {
      return this.getCurrentPageData();
    }
  }
}
</script>
<style>
.box-card {
  width: 100%;
}
</style>