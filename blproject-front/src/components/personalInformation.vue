<template>
  <div>
    <div class="userInfo-show">
        <el-form v-model="userInfo"  label-width="80px">
            <el-form-item label="昵称">
                <el-input v-model="userInfo.userName"></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <el-radio label="男" v-model="userInfo.sex" class="sex-chose1">男</el-radio>
                <el-radio label="女" v-model="userInfo.sex" class="sex-chose2">女</el-radio>
            </el-form-item>
            <el-form-item label="手机号码">
                <el-input v-model="userInfo.phone" type="text"></el-input>
            </el-form-item>
            <el-form-item label="个人简介">
                <el-input v-model="userInfo.remake" type="textarea"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="subUpdate()">修改</el-button>
            </el-form-item>
        </el-form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  // name: 'PersonalInformation',
    data() {
        return {
            userInfo:{},
        }
    },
    methods: {
      // 提交修改
    subUpdate() {
      console.log(this.userInfo);
      axios({
        url: this.$url + "/user/update",
        method: "post",
        data:this.userInfo
      }).then((resp) => {
        console.log("返回数据");
        console.log(resp.data['data']);
        localStorage.setItem("userInfo",JSON.stringify(resp.data['data']))
        // 这里需要转成JSON
        const test01 = localStorage.getItem("userInfo")
        const test02 = JSON.parse(test01);
        this.userInfo = test02
      })
    },
    },
     //初始化加载
    created(){
      const userInfoStr = localStorage.getItem("userInfo");
      const userInfoObj = JSON.parse(userInfoStr);
      this.userInfo=userInfoObj
    }
}
</script>

<style>
    .userInfo-show{
        margin: 0px;
    
    }
    .sex-chose1 {
        margin: 0px;
        display: flex;
        align-items: stretch;
        justify-content: flex-start;
        margin-left: 20px;
        margin-top: 13px;
    }
     .sex-chose2 {
        margin: 0px;
        display: flex;
        align-items: flex-start;
        justify-content: center;
        margin-left: -110px;
        margin-top: -15px;
        flex-direction: row;
        flex-wrap: nowrap;
    }
</style>