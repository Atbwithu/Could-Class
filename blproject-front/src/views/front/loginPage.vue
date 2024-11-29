<template>
  <div id="login-container">
    <div style="text-align: center;height: 50px">
      酷云课堂登录
    </div>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="账号" prop="account">
        <el-input type="text" v-model="ruleForm.account" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm()">登录</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        没有账号？<el-link type="primary" @click="openAddFrom">点我注册</el-link>
      </el-form-item>
    </el-form>
    <!-- 注册弹窗 -->
    <el-dialog title="注册哥布林" :visible.sync="centerDialogVisible" width="30%" center>
    <!-- 内容 -->
    <el-form ref="form" :model="UaddFrom" label-width="80px" size="mini">
        <el-form-item label="用户名">
          <el-input v-model="UaddFrom.account" @blur="checkUsernameExists"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="UaddFrom.userName"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="UaddFrom.phone"></el-input>
        </el-form-item>
        <el-form-item label="QQ号">
          <el-input v-model="UaddFrom.email"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="UaddFrom.sex" placeholder="请选择性别">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item size="large">
          <el-button type="primary" @click="subAddForm2">立即注册</el-button>
          <el-button @click="centerDialogVisible = false">前往登录</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios';
import { mapMutations } from 'vuex';
  export default {
    name: "LoginView",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账户'));
      } else {
        if (this.ruleForm.password !== '') {
          this.$refs.ruleForm.validateField('password');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    return {
      user:{},
      ruleForm:{
        account: '',
        password: '',
      },
      userToken:'',
      rules: {
        account: [
          { validator: validatePass, trigger: 'blur' }
        ],
        password: [
          { validator: validatePass2, trigger: 'blur' }
        ],
      },
      isLogin:false,
      UaddFrom:{},
      notAdd:false,
      centerDialogVisible: false,
    };
  },
  methods: {
   
    // 新增表单初始化
    openAddFrom(){
        this.centerDialogVisible = true
      },
    subAddForm2(){
      if(this.notAdd){
        axios({
            url:this.$url+"/user/insert2",
            method:"post",
            data:this.UaddFrom
        }).then(resp=>{
            //判断返回值是否为空好做跳转
            if(resp.data['code'] ===200){
                // this.$message.success("新增成功");
                this.open1()
                this.centerDialogVisible=false
            }
            if(resp.data['code']===400){
                //提示用户名可以使用
                // this.$message.error("新增失败");
                this.open2()
            }
             if(resp.data['code']===500){
                //提示用户名可以使用
                this.$message.error("新增失败");
            }
        })
      }else{
        // this.$message.error("用户名已拥有，请重新输入");
        this.open4()
      }
        
    },
    checkUsernameExists() {  
      console.log(this.UaddFrom.account);
            axios({
                // url:this.$url+"/user/selectByName2?account="+this.UaddFrom.account,
                url:this.$url+"/user/selectByName2",
                method:"post",
                data:this.UaddFrom.account
            }).then(resp=>{
              console.log(resp.data);
                //判断返回值是否为空好做跳转
                if(resp.data['code']===400){
                    //提示用户名已存在
                    this.$message.error("用户已存在,请重新输入");
                }
                if(resp.data['data']==="用户名可用"){
                    //提示用户名可以使用
                    this.$message.success("用户名可以使用");
                    this.notAdd = true
                }
            })
    },
    ...mapMutations(['changeLogin']),
    submitForm() {
      const params = {
          account: this.ruleForm.account,
          password: this.ruleForm.password
        };
        axios({
          url: this.$url + "/user/login",
          method: "post",
          data: this.$qs.stringify(params)
        }).then(resp => {
          if (resp['data']['code'] === 200) {  // 假设 200 表示登录成功
            this.$message.success("登录成功!!!");
            console.log(resp.data['data']['token']);
            // 登录操作的处理
            // token处理
            this.userToken = (resp.data['data']['token']);
            // 将用户token保存到vuex中
            this.changeLogin({token:this.userToken})
            //跳转页面
            //判断角色
            //进入前台
            if(resp.data['data']['roleId'] === 3){
              //首页
              this.$router.push({ path: "/homePage" });
              //将用户信息储存到session中
              localStorage.setItem("userInfo",JSON.stringify(resp.data['data']))
            }
            //这是后台
            if(resp.data['data']['roleId'] === 2 || resp.data['data']['roleId'] === 1){
              this.$router.push({ path: "/indexPage" });
              //将用户信息储存到session中
              localStorage.setItem("userInfo",JSON.stringify(resp.data['data']))
            }
            // document.location.href='/homePage'
          }else if(resp['data']['code'] === 303){
            this.$message.error("用户名或密码错误");
          }else if(resp['data']['code'] === 301){
            this.$message.error("用户不存在,请先注册账号");
          }
        })
      },
      registerUser() {
      axios({
        url: this.$url + "/user/register", // 使用定义好的基础URL
        method: "POST", // 使用POST方法提交表单数据
        data: this.user // 发送的数据作为请求体
      })
        .then((resp) => {
          // 假设服务器返回的数据中包含code和message字段
          if (resp.data.code === 1) {
            this.$message.error('提问失败: ' + resp.data.message);
          } else {
            this.$message.success('提问成功');
            // 清空表单
            this.formInline = {
              userName: "",
              subject: "",
              questions: "",
              phone: ""
            };
          }
        })
        .catch((error) => {
          // 处理请求错误
          console.error(error);
          this.$message.error('请求失败');
        });
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
      },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
  }
</script>

<style scoped>
  body{
    margin: 0;
  }
  #login-container{
    width: 400px;
    height: 290px;
    background: #e5e9f2;
    position: absolute;
    left: 50%;
    top: 50%;
    margin-left: -220px;
    margin-top: -170px;
    border-radius: 5px;
    padding-top: 40px;
    padding-right: 40px;
  }
</style>
