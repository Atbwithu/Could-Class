<template>
    <div class="box01">
        <!-- 章节目录 -->
        <div class="box3">
            <el-card id="lin" class="box-card">
                <el-page-header @back="goBack" content="视频页面">
                </el-page-header>
                <el-divider></el-divider>
                <div class="video-container">
                    <div class="video-wrapper">
                        <video class="video-container" controls @ended="learnNum" @timeupdate="updatePlayTime"
                            :src="video[0].savePath"></video>
                    </div>
                </div>
            </el-card>
        </div>
        <!-- 评论 -->
        <div class="box2">
            <el-card id="lin" class="box-card">
                <el-page-header content="评论"></el-page-header>
                <hr>
                <!-- 评论 -->
                <div class="comment">
                    <div v-for="(item, index) in asklist" :key="index" style="padding-bottom: 3%; font-size: 20px;">
                        <!-- 这里可以使用 item 访问每个元素，使用 index 访问元素的索引 -->
                        <div>
                            <b>问:</b> <span> {{ item.questions }}</span>
                        </div><br>
                        <div>
                            <b>答:</b> <span> {{ item.answer }}</span>
                        </div>
                        <hr>
                    </div>
                </div>
                <!-- 提问 -->
                <div>
                    <el-form :inline="true" :model="formInline" class="demo-form-inline"
                        style="margin: 0 auto; width: 1000px;">
                        <el-form-item label="">
                            <el-input type="textarea" :rows="4" v-model="formInline.question" placeholder="请输入要提问的问题"
                                style="width: 1000px; "></el-input>
                        </el-form-item>
                        <br>
                        <el-form-item style="margin: 0 auto; width: 200px; padding-left: 45%;">
                            <el-button type="primary" @click="onSubmit">提问一下！！！</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </el-card>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import Cookie from "vue-cookie";

export default {
    name: "VideoPlayer",
    data() {
        return {
            // 视频信息
            playTime: 0,
            video: [],
            // 评论列表
            asklist: [],
            formInline: {
                cid: "",
                question: ""
            }
        };
    },
    methods: {
        // 返回上一页
        goBack() {
            this.$router.go(-1);
        },
        // 加载视频数据
        loadData() {
            let cid = Cookie.get("courseId");
            let vid = Cookie.get("videoId");
            axios({
                url: this.$url + '/chapter/selectVideo?id=' + vid,
                method: "post",
            }).then((resp) => {
                this.video = resp.data;
                console.log(this.video[0]);
            });
        },
        // 获取评论列表
        askList() {
            axios({
                url: this.$url + "/ask/askList?id=" + Cookie.get("chapterId"),
                method: "get"
            }).then((resp) => {
                this.asklist = resp.data;

            });
        },
        // 提交提问
        onSubmit() {
            this.formInline.cid = Cookie.get("courseId");
            let user = localStorage.getItem("userInfo");
            this.UserInfo = JSON.parse(user);
            axios({
                url: this.$url + "/ask/subAsk?question=" + this.formInline.question + "&cid=" + this.formInline.cid + "&uid=" + this.UserInfo["id"] + "&chapterId=" + Cookie.get("chapterId"),
                method: "POST",
            })
                .then((resp) => {
                    if (resp.data.code === 1) {
                        this.$message.error('提问失败: ' + resp.data.message);
                    } else {
                        this.$message.success('提问成功');
                        this.formInline.question = "";
                    }
                })
                .catch((error) => {
                    console.error(error);
                    this.$message.error('请求失败');
                });
        },
        updatePlayTime(event) {
            // 实时更新播放时间
            this.playTime = event.target.currentTime;
            
        },
        // 学习次数记录
        learnNum() {
            this.upTime();
            let user = localStorage.getItem("userInfo");
            this.UserInfo = JSON.parse(user);
            let cid = Cookie.get("courseId");
            axios({
                url: this.$url + "/study/handleNumUpdate?userId=" + this.UserInfo['id'] + "&courseId=" + cid,
                method: "post",
            }).then((resp) => {
                console.log(resp.data);
            });
        },
        // 学习时长的统计
        upTime() {
            let user = localStorage.getItem("userInfo");
            this.UserInfo = JSON.parse(user);
            let cid = Cookie.get("courseId");
            axios({
                url: this.$url + "/study/handleTimeUpdate?userId=" + this.UserInfo['id']
                    + "&videoTime=" + this.playTime + "&courseId=" + cid + "&chapterId=" + Cookie.get("chapterId"),
                method: "post",
            }).then((resp) => {
                console.log(resp.data);
            });
        }
    },
    created() {
        this.loadData();
        this.askList();
    }
};
</script>

<style scoped>
/* 整体布局 */
.box01 {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

/* 章节目录 */
.box3 {
    width: 60%;
    margin-top: 20px;
}

.video-container {
    padding: 20px;
    background-color: #f8f9fa;
    border-radius: 10px;
}

.video-wrapper {
    position: relative;
    padding-bottom: 56.25%;
    height: 0;
    overflow: hidden;
}

.video-wrapper video {
    position: absolute;
    top: 0;
    left: 0;
    width: 98%;
    height: 120;
    border-radius: 10px;
}

/* 评论部分 */
.box2 {
    width: 60%;
    margin-top: 20px;
}

.el-card {
    border-radius: 10px;
}

.el-page-header {
    padding: 10px 20px;
    background-color: #f0f0f0;
    border-radius: 10px 10px 0 0;
}

.comment {
    padding: 20px;
    background-color: #fff;
    border-radius: 0 0 10px 10px;
    height: 400px;
    overflow-y: auto;
}

.comment div {
    margin-bottom: 20px;
}

.comment b {
    font-size: 18px;
    color: #333;
}

.comment span {
    font-size: 16px;
    color: #666;
}

/* 提问表单 */
.el-form {
    width: 80%;
    margin: 20px auto;
}

.el-form-item label {
    font-size: 18px;
    color: #333;
}

.el-input {
    font-size: 16px;
}

.el-button {
    padding: 10px 20px;
    font-size: 18px;
}
</style>