<template>
    <div>
        <!-- 学员情况 -->
        <div>
            <h1 style="font-size: 30px;">首页</h1><br>
            <hr>
            <div class="countInfo" align="center">
                <el-row :gutter="20">
                    <el-col :span="8">
                        <el-card class="box-card" style="width: 400px; height: 150px;">
                            <i class="el-icon-user" style="font-size: 50px; padding-top: 25px;"></i><b
                                style="font-size: 40px;"><span style="color: aqua;">学生数量：</span>{{ stuNum }}人</b>
                            <br>
                            <span style="margin: 0 auto; font-size: 30px;"></span>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card class="box-card" style="width: 400px; height: 150px;">
                            <i class="el-icon-collection" style="font-size: 50px; padding-top: 25px;"></i><b
                                style="font-size: 40px;"><span style="color: aqua;">课程数量：</span>{{ coursNum }}门</b>
                            <br>
                            <span style="margin: 0 auto; font-size: 30px;"></span>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card class="box-card" style="width: 400px; height: 150px;">
                            <i class="el-icon-bell" style="font-size: 50px; padding-top: 25px;"></i><b
                                style="font-size: 40px;"><span style="color: aqua;">提问数量：</span>{{ questions }}个</b>
                            <br>
                            <span style="margin: 0 auto; font-size: 30px;"></span>
                        </el-card>
                    </el-col>
                </el-row>
            </div>

        </div>

        <div class="info" style="padding-top: 100px;" align="center">
            <el-row :gutter="20">
                <el-col :span="12">
                    <el-card class="box-card" style="width: 900px; height: 400px;">
                        <div slot="header" class="clearfix">
                            <span>
                                <h2>最新提问</h2>
                            </span>
                            <el-button style="float: right; padding: 3px 0" type="text"></el-button>
                        </div>
                        <div class="text item">
                            <el-table :data="askList" style="width: 100%" height="250">
                                <el-table-column prop="questions" label="问题" width="500">
                                </el-table-column>
                                <el-table-column prop="createTime" label="日期" width="160">
                                </el-table-column>
                            </el-table>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="12">
                    <el-card class="box-card" style="width: 900px; height: 400px;">
                        <div slot="header" class="clearfix">
                            <span>
                                <h2>最新课程</h2>
                            </span>
                            <el-button style="float: right; padding: 3px 0" type="text"></el-button>
                        </div>
                        <div class="text item">
                            <el-table :data="newCourseList" style="width: 100%" height="250">
                                <el-table-column prop="courseName" label="问题" width="500">
                                </el-table-column>
                                <el-table-column prop="createTime" label="日期" width="160">
                                </el-table-column>
                            </el-table>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
            <el-row :gutter="20">
                <el-col :span="12">
                    <el-card class="box-card" style="width: 900px; height: 400px;">
                        <div slot="header" class="clearfix">
                            <span>
                                <h2>热门课程</h2>
                            </span>
                            <el-button style="float: right; padding: 3px 0" type="text"></el-button>
                        </div>
                        <div class="text item">
                            <el-table :data="hotCourse" style="width: 100%" height="250">
                                <el-table-column prop="courseName" label="课程名称" width="500">
                                </el-table-column>
                                <el-table-column prop="createTime" label="日期" width="160">
                                </el-table-column>
                            </el-table>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="12">
                    <el-card class="box-card" style="width: 900px; height: 400px;">
                        <div slot="header" class="clearfix">
                            <span>
                                <h2>最爱学习的学生</h2>
                            </span>
                            <el-button style="float: right; padding: 3px 0" type="text"></el-button>
                        </div>
                        <div class="text item">
                            <el-table :data="loveStuList" style="width: 100%" height="250">
                                <el-table-column prop="userName" label="学生" width="500">
                                </el-table-column>
                                <el-table-column prop="learnTime" label="时长" width="160">
                                </el-table-column>
                            </el-table>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
export default {
    data() {
        return {
            stuNum: '',
            questions: '',
            coursNum: '',
            askList: [],
            coursesList: [],
            stuList: [],
            maxCourse: [],
            tableData: [],
            loveStuList: [],
            newCourseList: [],
            hotCourse:[]
            
        }
    },
    methods: {

        async res2() {
            try {
                const resp = await axios.get(this.$url + "/course/hotCourse");
                this.hotCourse = resp.data;
            } catch (error) {
                console.error('获取分类列表出错:', error);
            }
            console.log(this.hotCourse);
        },
        counStu() {
            axios({
                url: this.$url + "/user/countStu",
                method: "get"
            }).then((resp) => {
                //将拿到的数据交给tableData
                this.stuNum = resp.data;
            })
        },
        countCourse() {
            axios({
                url: this.$url + "/user/countCourse",
                method: "get"
            }).then((resp) => {
                //将拿到的数据交给tableData
                this.coursNum = resp.data;
            })
        },
        countQuestions() {
            axios({
                url: this.$url + "/user/countQuestion",
                method: "get"
            }).then((resp) => {
                //将拿到的数据交给tableData
                this.questions = resp.data;
            })
        },
        question() {
            axios({
                url: this.$url + "/ask/questionList",
                method: "get"
            }).then((resp) => {
                //将拿到的数据交给tableData
                this.askList = resp.data;
            })
        },
        loveStu() {
            axios({
                url: this.$url + "/study/loveStu",
                method: "get"
            }).then((resp) => {
                //将拿到的数据交给tableData
                this.loveStuList = resp.data;
            })
        },
        courseList() {
            axios({
                url: this.$url + "/study/newCourse",
                method: "get"
            }).then((resp) => {
                //将拿到的数据交给tableData
                this.newCourseList = resp.data;
            })
        }
    },
    created() {
        this.counStu()
        this.res2()
        this.countQuestions()
        this.countCourse()
        this.question()
        this.loveStu()
        this.courseList()
    }
}
</script>
<style>
.el-row {

    margin-bottom: 20px;

    &:last-child {
        margin-bottom: 0;
    }
}

.el-col {

    border-radius: 5px;

}

.bg-purple {
    background: #e6d3d9;
}

.grid-content {
    line-height: 100px;
    height: 100px;
    border-radius: 4px;
    min-height: 36px;
}


.text {
    font-size: 14px;
}

.item {
    padding: 18px 0;
}

.box-card {
    width: 600px;
}

/* 任务头像 */
.el-icon-user {
    width: 50px;
}

/* 数量列表样式 */
.countInfo {
    overflow: hidden
}
</style>