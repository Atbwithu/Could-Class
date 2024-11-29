<template>
  <div>
    <div id="ribbon">
      <div>
        <h1 style="font-size: 30px;">用户管理</h1><br>
        <hr><br />
      </div>
      <el-input v-model="searchQuery" placeholder="请输入昵称" style="width:300px"></el-input>
      <el-button type="primary" icon="el-icon-search" @click="searchNickname">搜索</el-button>
      <el-button type="primary" @click="openAddFrom">新增用户</el-button>
    </div><br />
    <el-table :data="paginatedData" border style="width: 100%">
      <el-table-column prop="id" label="ID" width="80">
      </el-table-column>
      <el-table-column prop="account" label="用户名" width="120">
      </el-table-column>
      <el-table-column prop="userName" label="昵称" width="120">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="50">
      </el-table-column>
      <el-table-column prop="email" label="邮箱" width="200">
      </el-table-column>
      <el-table-column prop="phone" label="手机号" width="100">
      </el-table-column>
      <el-table-column prop="remake" label="备注">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间">
      </el-table-column>
      <el-table-column prop="status" label="状态" width="50">
      </el-table-column>

      <el-table-column prop="roleId" label="角色" width="70">
        <template slot-scope="scope">
          <span v-if="scope.row.roleId === 1">管理员</span>
          <span v-else-if="scope.row.roleId === 2">老师</span>
          <span v-else-if="scope.row.roleId === 3">学生</span>
        </template>
      </el-table-column>

      <el-table-column prop="" label="操作" width="250">
        <template slot-scope="scope">
          <!-- 在这里，scope 对象包含了当前行的数据和其他信息 -->
          <el-tooltip content="点击修改状态" placement="top">
            <el-button type="danger" icon="el-icon-warning" circle v-if="scope.row.status == 1"
              @click="editStatus(scope.row)"></el-button>
            <el-button type="success" icon="el-icon-warning" circle v-else @click="editStatus(scope.row)"></el-button>
          </el-tooltip>
          <el-tooltip content="点击编辑用户" placement="top">
            <el-button type="primary" icon="el-icon-edit" circle @click="handleRowClick(scope.row)"></el-button>
          </el-tooltip>
          <el-tooltip content="点击删除用户" placement="top">
            <el-button type="danger" icon="el-icon-delete" circle @click="showDeleteDialog(scope.row)"></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <!-- 分页控件 -->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
        :page-sizes="[5, 10, 20, 50]" :page-size="pageSize" layout="->, prev, pager, next, jumper, sizes"
        :total="userTable.length">
      </el-pagination>
    </div>

    <!-- 编辑的弹窗 -->
    <el-drawer title="编辑用户信息" :before-close="handleClose" :visible.sync="dialogVisible" direction="rtl"
      custom-class="demo-drawer" ref="drawer">
      <div class="demo-drawer__content">
        <el-form :model="selectedUser">
          <el-form-item label="用户名" :label-width="formLabelWidth">
            <el-input v-model="selectedUser.account" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="昵称" :label-width="formLabelWidth">
            <el-input v-model="selectedUser.userName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="性别" :label-width="formLabelWidth">
            <el-select v-model="selectedUser.sex" placeholder="请选择性别">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="邮箱" :label-width="formLabelWidth">
            <el-input v-model="selectedUser.email" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="手机号" :label-width="formLabelWidth">
            <el-input v-model="selectedUser.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="角色" :label-width="formLabelWidth">
            <el-select v-model="selectedUser.roleId" placeholder="请选择角色">
              <el-option v-for="item in roleOptions" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div class="demo-drawer__footer">
          <el-button @click="cancelForm">取 消</el-button>
          <el-button type="primary" @click="submitForm" :loading="loading">
            {{ loading ? '提交中 ...' : '确 定' }}
          </el-button>
        </div>
      </div>
    </el-drawer>
    <!-- 新增弹窗 -->
    <el-dialog title="新增用户" :visible.sync="centerDialogVisible" width="30%" center>
      <!-- 内容 -->
      <el-form ref="form" :model="addFrom" label-width="80px" size="mini">
        <el-form-item label="用户名">
          <el-input v-model="addFrom.account" @blur="checkUsernameExists"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="addFrom.userName"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="addFrom.phone"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="addFrom.sex" placeholder="请选择性别">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="addFrom.roleId" placeholder="请选择角色">
            <el-option v-for="item in roleOptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="账号状态">
          <el-radio-group v-model="addFrom.status" size="medium">
            <el-radio border label="1">启用</el-radio>
            <el-radio border label="2">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item size="large">
          <el-button type="primary" @click="subAddForm">立即创建</el-button>
          <el-button @click="centerDialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!-- 删除弹窗确认 -->
    <!-- 确认删除对话框 -->
    <el-dialog title="确认删除" :visible.sync="delDialog">
      <span>确定要删除这条记录吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="delDialog = false">取 消</el-button>
        <el-button type="primary" @click="deleteById()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import axios from 'axios';
export default {
  data() {
    return {
      roleInfo: {},
      roleOptions: [], // 用于存储从后端获取的角色列表  
      addFrom: {},
      userTable: [],
      centerDialogVisible: false,
      //设置一个新增不允许的值
      notAdd: false,
      // 删除确认弹窗
      delDialog: false,
      recordToDelete: null, // 用于存储待删除的记录信息
      // 编辑需要的数据
      selectedUser: {},// 选中用户的数据
      dialogVisible: false, // 对话框可见性
      formLabelWidth: '80px', // 表单标签宽度
      loading: false, // 提交按钮加载状态
      // 搜索昵称
      searchQuery: '', // 输入框的值
      // 分页
      currentPage: 1, // 当前页码
      pageSize: 10, // 每页显示的记录数
    }
  },
  computed: {
    paginatedData() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.userTable.slice(start, end);
    },
    totalPages() {
      return Math.ceil(this.userTable.length / this.pageSize);
    },
  },
  methods: {
    handleSizeChange(newSize) {
      this.pageSize = newSize;
      this.currentPage = 1; // 当每页显示数量改变时，重置当前页码
    },
    handleCurrentChange(newPage) {
      if (newPage > 0 && newPage <= this.totalPages) {
        this.currentPage = newPage;
      }
    },
    // 搜索昵称
    searchNickname() {
      console.log(this.searchQuery);
      axios({
        url: this.$url + "/user/selectByNickName?userName=" + this.searchQuery,
        method: "post",
        // data:this.searchQuery,
      }).then(resp => {
        //判断返回值是否为空好做跳转
        if (resp.data['code'] === 400) {
          //提示用户名已存在
          this.$message.error("用户已存在,请重新输入");
        }
        if (resp.data['code'] === 200) {
          //提示用户名可以使用
          this.userTable = resp.data['data']
        }
      })
    },
    // 处理行点击事件，获取选中用户的数据----编辑
    handleRowClick(row) {
      console.log(row);
      this.getRoleInfo()
      this.selectedUser = row
      this.dialogVisible = true;
    },
    // 处理对话框关闭前的操作
    handleClose(done) {
      // 这里可以进行表单验证等操作
      done();
    },
    // 取消表单操作
    cancelForm() {
      this.dialogVisible = false;
    },
    // 提交表单操作
    submitForm() {
      this.loading = true;
      // 这里进行表单提交操作
      // console.log("下面是内容");
      axios({
        url: this.$url + "/user/updateById",
        method: "post",
        data: this.selectedUser
      }).then(resp => {
        //判断返回值是否为空好做跳转
        if (resp.data['code'] === 200) {
          // this.$message.success("修改成功");
          this.open1()
          this.centerDialogVisible = false,
            this.getUserInfo()
          setTimeout(() => {
            this.loading = false;
            this.dialogVisible = false;
            // 模拟更新成功后的操作
          }, 2000);
        }
        if (resp.data['code'] === 400) {
          //提示用户名可以使用
          // this.$message.error("新增失败");
          this.open2()
        }
        if (resp.data['code'] === 500) {
          //提示用户名可以使用
          this.$message.error("新增失败");
        }
      })
    },
    // 新增表单初始化
    openAddFrom() {
      this.centerDialogVisible = true
      this.getRoleInfo()
    },
    checkUsernameExists() {
      console.log(this.addFrom.account);
      axios({
        url: this.$url + "/user/selectByName",
        method: "post",
        data: {
          account: this.addFrom.account
        }
      }).then(resp => {
        //判断返回值是否为空好做跳转
        if (resp.data['code'] === 400) {
          //提示用户名已存在
          this.$message.error("用户已存在,请重新输入");
        }
        if (resp.data['data'] === "用户名可用") {
          //提示用户名可以使用
          this.$message.success("用户名可以使用");
          this.notAdd = true
        }
      })
    },
    subAddForm() {
      if (this.notAdd) {
        axios({
          url: this.$url + "/user/insert",
          method: "post",
          data: this.addFrom
        }).then(resp => {
          //判断返回值是否为空好做跳转
          if (resp.data['code'] === 200) {
            // this.$message.success("新增成功");
            this.open1()
            this.centerDialogVisible = false,
              this.getUserInfo()
          }
          if (resp.data['code'] === 400) {
            //提示用户名可以使用
            // this.$message.error("新增失败");
            this.open2()
          }
          if (resp.data['code'] === 500) {
            //提示用户名可以使用
            this.$message.error("新增失败");
          }
        })
      } else {
        // this.$message.error("用户名已拥有，请重新输入");
        this.open4()
      }
    },
    //修改状态
    editStatus(row) {
      console.log(row);
      axios({
        url: this.$url + "/user/updateStatus?id=" + row.id + "&status=" + row.status,
        method: "post",
      }).then((resp) => {
        console.log(resp.data);
        //判断返回值是否为空好做跳转
        if (resp.data['code'] === 200) {
          this.open1()
          this.centerDialogVisible = false,
            this.getUserInfo()
        }
        if (resp.data['code'] === 400) {
          //提示用户名可以使用
          // this.$message.error("新增失败");
          this.open2()
        }
        if (resp.data['code'] === 500) {
          //提示用户名可以使用
          this.$message.error("新增失败");
        }
      })
    },
    //获取角色列表
    getRoleInfo() {
      console.log("测试");
      axios({
        url: this.$url + "/role/selectAll",
        method: "post"
      }).then(resp => {
        //判断返回值是否为空好做跳转
        if (resp.data['data'] === null) {
          //延迟跳转提示用户
          this.$message.warning("还未登录！！请先登录");
          setTimeout(() => {
            // 延迟 3 秒后进行跳转
            //返回登录页面
            this.$router.push({ path: "/" });
          }, 2000);
        }
        //获取角色数据
        this.roleOptions = resp.data['data'].map(role => ({
          label: role.roleName, // 使用roleName作为显示的标签  
          value: role.roleId    // 使用roleId作为选项的值  
        }));
        //将拿到的数据给table
        this.roleInfo = resp.data['data'];
      })
    },
    getUserInfo() {
      axios({
        url: this.$url + "/user/list",
        method: "post"
      }).then(resp => {
        //判断返回值是否为空好做跳转
        if (resp.data['data'] === null) {
          //延迟跳转提示用户
          this.$message.warning("还未登录！！请先登录");
          setTimeout(() => {
            // 延迟 3 秒后进行跳转
            //返回登录页面
            this.$router.push({ path: "/" });
          }, 2000);
        }
        //将拿到的数据给table
        console.log(resp.data);
        this.userTable = resp.data['data'];
        this.totalItems = resp.data.length;
      })
    },
    showDeleteDialog(record) {
      this.recordToDelete = record;
      this.delDialog = true;
    },
    deleteById() {
      if (this.recordToDelete) {
        axios({
          url: this.$url + "/user/delete?id=" + this.recordToDelete.id,
          method: "post",
        }).then(resp => {
          this.delDialog = false;
          if (resp.data.data === 1) {
            this.$message.success("删除成功");
          }
          if (resp.data['code'] === 400) {
            this.$message.error("删除失败");
          }
          this.getUserInfo();
        })
      }
    },
    //下面是消息通知
    open1() {
      this.$notify({
        title: '成功',
        message: '这是一条成功的提示消息',
        // message: '',
        type: 'success'
      });
    },
    open2() {
      this.$notify({
        title: '警告',
        message: '这是一条警告的提示消息',
        type: 'warning'
      });
    },

    open3() {
      this.$notify.info({
        title: '消息',
        message: '这是一条消息的提示消息'
      });
    },

    open4() {
      this.$notify.error({
        title: '错误',
        message: '用户名已存在,换一个用户名吧'
      });
    }

  },
  created() {

    this.getUserInfo();
  },
}
</script>
<style>
.el-table {
  /* margin: 0 auto; */
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
}

.el-input {
  padding-right: 20px;
}

.label-text {
  font-weight: bold;
}
</style>