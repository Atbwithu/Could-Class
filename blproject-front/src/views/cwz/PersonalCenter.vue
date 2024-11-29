<template>
    <div id="app">
        <div class="container">  
            <div class="avatar-crumbs-wrapper">  
                <div class="showUserInfo">  
                    <div v-if="userInfo.imageUrl">
                        <img :src="userInfo.imageUrl"  alt="User UserInfo" class="titleImg" />
                    </div> 
                    <div v-else>
                        <!-- <el-avatar :size="150" :src="circleUrl">
                            <i class="el-icon-user" style="font-size: 80px"></i>
                        </el-avatar> -->
                        <el-avatar icon="el-icon-user-solid"></el-avatar>
                    </div>
                    <div class="userInfo-container">  
                        <span class="userNameandtext">{{ userInfo.userName }}</span>  
                    </div>  
                    <div class="texr-class">
                        <div class="text-content">  
                            <span>收藏课程</span>
                        </div>  
                        <div class="text-content">  
                            <span>学时</span>
                        </div>  
                        <div class="text-content">  
                            <span>提问</span>  
                        </div>  
                    </div>  
                </div>  
                <!-- 面包屑 -->
                <div class="crumbs">  
                    <div class="el-breadcrumb">
                        <el-breadcrumb separator="/">
                            <el-breadcrumb-item :to="{ path: '/homePage' }">首页</el-breadcrumb-item>  
                            <el-breadcrumb-item v-if="$route.name === 'personalCenter'">个人中心</el-breadcrumb-item>  
                            <!-- 这里可以添加更多的条件判断来显示不同的子项 -->  
                            <el-breadcrumb-item v-if="$route.name === 'personalInformation'">个人信息</el-breadcrumb-item>  
                            <el-breadcrumb-item v-if="$route.name === 'collection'">我的收藏</el-breadcrumb-item>  
                            <el-breadcrumb-item v-if="$route.name === 'myquestion'">我的问题</el-breadcrumb-item>  
                            <!-- 其他子项 -->  
                        </el-breadcrumb> 
                    </div> 
                    <div class="allconten">
                        <router-view></router-view>
                    </div>
                </div>
            </div>
        <!-- 第二个盒子 -->
            <div class="showUserInfo">
                <div class="userInfo-containerTwo">
                    <router-link :to="'personalCenter'">个人信息</router-link>
                </div>
                <div class="userInfo-containerTwo">
                    <router-link :to="'collectionList'">我的收藏</router-link> 
                </div>
                <div class="userInfo-containerTwo">
                    <router-link :to="'myQuestionComponent'">我的问题</router-link> 
                </div>
                <div class="userInfo-containerTwo">
                    <router-link :to="'personalCenter'">我的课程信息</router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            userInfo:"",
        }
    },
    methods: {
      
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
    .allconten {
         box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
        width: 450px;
        margin: 0px;
        padding: 20px;
        margin-top:  50px;
        border-radius: 5px;
    }
    .router-link-active {
    text-decoration: none;
    color: rgb(40, 145, 236);
    }

    .avatar-crumbs-wrapper {  
        display: flex;
        justify-content: flex-start;
        align-items: flex-start;
    }
    .el-breadcrumb {
            font-size: 14px;
            line-height: 1;
            margin-top: 6px;
            margin-left: 5px;
    }
    
    
    .crumbs {
        background-color: rgb(151, 153, 155);
        margin: 0px;
        padding: 0px;
        margin-top: 30px;
        margin-left: 10px;
        width: 500px;
        height: 35px;
        border-radius: 5px;
    }
    
    .titleImg {
        margin: 0px;
        padding: 0px;
        display: flex;
        border-radius: 100%;
        width: 250px;
    }
    .showUserInfo {
    /* 盒子阴影 */
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
    /* 确保 avatar 是块级元素并且有一个合适的高度和宽度 */  
    width: 320px;
    /* 圆角 */
    border-radius: 8px;
    display: block;  
    text-align: center; 
    margin-left: 23%;
    margin-top: 30px;
    /* 水平居中内部的块级元素，但对于行内元素（如 span）需要额外的处理 */  
    /* 根据需要设置其他样式，如宽度、高度、内边距等 */  
    }
    .titleImg {  
    /* 图片的样式 */  
    display: block; /* 图片作为块级元素会默认占据整行 */  
    margin: 0 auto; 
    margin-top: 10px;
    } 
    .userInfo-container {  
    /* 使得文本在 avatar 中垂直居中（这里使用了一个简单的方法，即 flexbox）*/  
    display: flex;  
    justify-content: center; /* 水平居中 */  
    align-items: center; /* 垂直居中，但因为只有一个子元素且为 span，所以效果上可能不明显 */  
    flex-direction: column; /* 子元素垂直堆叠 */  
    /* 你可能还需要设置一些内边距，以在图片和文本之间留出一些空间 */  
    padding-top: 10px; /* 示例值，根据需要调整 */
    } 
    .userNameandtext {
    /* 文本样式 */  
    /* 这里可以添加你想要的任何样式 */  
        background-color: rgb(11, 123, 251);
        font-size: 20px;
        width: 200px;
        color: white; 
        border-radius: 5px;
    }   

    .texr-class {
        font-size:13px;
        display: flex;
        flex-direction: column-reverse;
        margin-right: 200px;
        margin-bottom: 13px;
    }
    .text-content {
        margin-top: 29px;
    }
    .userInfo-containerTwo {
        color: rgb(8, 137, 250);
        margin: 0px;
        padding: 0;
        margin-left: -180px;
        margin-top: 5px;
    }
    
</style>