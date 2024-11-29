<template>
  <div>
    <div>
      <!-- 搜索框 -->
      <!-- <el-input type="text" v-model="queryRole.roleName" style="width: 300px;"></el-input>
        <el-button @click="query()">搜索</el-button>
        <el-button @click="reload()">重置</el-button> -->
        <!-- 展示数据 -->
        <el-table :data="tableData" style="width: 100%" class="tbinfo">
          <el-table-column prop="roleId" label="id" width="300" class="tbinfo">
          </el-table-column>
          <el-table-column prop="roleName" label="姓名" width="300" class="tbinfo">
          </el-table-column>
          <el-table-column label="操作" width="200px">
                    <template slot-scope="scope">
                        <el-button type="text" @click="handleEdit(scope.row)">编辑</el-button>
                        <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
        </el-table>
    </div>

    <!-- // 编辑的弹窗 -->
    <el-dialog title="编辑角色" :visible.sync="dialogEditVisible">
      <el-form :model="roleFrom" enctype="multpart/form-data">
        <el-form-item label="兼职标题：" :label-width="formLabelWidth"
          :rules="{ required: true, message: '请输入姓名', trigger: 'blur' }">
          <el-input style="width: 60%;" v-model="roleFrom.roleName" autocomplete="off" placeholder="请输入"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="CancelEdit()">取 消</el-button>
        <el-button type="primary" @click="subUpdate()">保 存</el-button>
      </div>
    </el-dialog>
    
    <el-button @click="dialogAddVisible = true">新增</el-button>
    <!-- 新增 -->
    <el-dialog title="新增角色" :visible.sync="dialogAddVisible">
      <el-form :model="roleFrom" enctype="multpart/form-data">
        <el-form-item label="角色名称：" :label-width="formLabelWidth"
          :rules="{ required: true, message: '请输入名称', trigger: 'blur' }">
          <el-input style="width: 60%;" v-model="roleFrom.roleName" autocomplete="off" placeholder="请输入"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取 消</el-button>
        <el-button type="primary" @click="subAddForm()">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>
<style>
    .tbinfo {
      margin: 0px;
      padding: 0px;
    }
</style>

<script>
import axios from "axios";
export default {
  data() {
    return {
      tableData: [],
      dialogAddVisible: false,
      roleFrom: {},
      formLabelWidth: "120px",
      dialogEditVisible: false,
      queryRole:{
        roleName: null
      },
    }
  },
  methods: {
    // 取消编辑的操作
    CancelEdit(){
      this.dialogEditVisible = false
      this.roleFrom.roleName = ""
    },
    // 搜索
    query(){
      axios({
      url: this.$url + "/role/selectAll",
      method: "get",
      params: this.queryRole
    }).then((resp) => {

      //将拿到的数据交给tableData
      this.tableData = resp.data;
    })
    },
  
    //提交新增表单
    subAddForm() {
      this.dialogAddVisible = false
      console.log(this.roleFrom);
      axios({
        url: this.$url + "/role/add",
        method: "post",
        data: this.roleFrom
      }).then((resp) => {
        console.log(resp.data);
        // 新增之后刷新
        this.getRoleInfo()
      })
    },
    // 提交修改
    subUpdate() {
      this.dialogEditVisible = false
      axios({
        url: this.$url + "/role/updateById",
        method: "post",
        params: this.roleFrom // 与新增为同一表单
      }).then((resp) => {
        console.log(resp.data);
        // 新增之后刷新
        this.getRoleInfo()
        // 修改之后情况表单
        this.roleFrom.name = '';
      })
    },
    // 刷新
    reload() {
      axios({
        url: this.$url + "/role/selectAll",
        method: "get"
      }).then((resp) => {
        //将拿到的数据交给tableData
        this.tableData = resp.data;
        this.queryRole.name = ''
      })
    },
    // 编辑
    handleEdit(row){
      this.dialogEditVisible = true
      // 发送请求，根据id查询数据详情
      axios({
      url: this.$url + "/role/selectById?roleId="+row.roleId,
      method: "get"
    }).then((resp) => {
      //将拿到的数据交给tableData
      this.roleFrom = resp.data;
    })
    },
    handleDelete(row) {
      if (confirm("是否删除")) {
        // 发送请求，根据id查询数据详情
        console.log(row.roleId)
        axios({
          url: this.$url + "/role/deleteById?roleId=" + row.roleId,
          method: "get"
        }).then((resp) => {
          this.getRoleInfo()
          if(resp.data['code'] == 200){
            this.getRoleInfo()
          }
        })
      }
    },
    getRoleInfo(){
      axios({
        url: this.$url + "/role/selectAll",
        method: "get"
      }).then((resp) => {
        //将拿到的数据交给tableData
        this.tableData = resp.data['data'];
      })
    }
  },
  created() {
    this.getRoleInfo()
  },
}
</script>
