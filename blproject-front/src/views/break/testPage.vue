<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="提问">
        <el-input v-model="formInline.questions" placeholder="请输入要提问的问题"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提问</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {

      formInline: {
        userName: "",
        subject: "",
        questions: "",
        phone: ""
      }
    };
  },
  methods: {
    onSubmit() {
      // 获取session
      let userStr = localStorage.getItem("userInfo");
      try {
        if (userStr) {
          let user = JSON.parse(userStr);
          this.formInline.userName = user['userName'];
          this.formInline.phone = user['phone'];
        } else {
          // 处理 userInfo 不存在的情况
          console.error('User info not found in localStorage');
          return;
        }
      } catch (e) {
        // 处理 JSON 解析错误
        console.error('Error parsing user info from localStorage:', e);
        return;
      }

      console.log(this.formInline.userName);
      console.log(this.formInline.phone);
      console.log(this.formInline);

      axios({
        url: this.$url + "/ask/subAsk", // 使用定义好的基础URL
        method: "POST", // 使用POST方法提交表单数据
        data: this.formInline // 发送的数据作为请求体
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
    }
  }
};
</script>