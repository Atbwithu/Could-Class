<!-- 视频管理 -->
<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span id="headFont">章节视频列表</span>
                <el-button style="float: right;" type="primary" @click="back" size="mini">返回章节列表</el-button>
                <el-button @click="batchDelete" style="float: right;" type="danger" icon="el-icon-minus"
                    size="mini">批量删除</el-button>
                <el-button style="float: right; " size="mini" type="primary" @click="dialogUploadVideo = true">上传<i
                        class="el-icon-upload el-icon--right"></i></el-button>
            </div>
            <!-- 上传视频对话框 -->
            <div style="text-align: center;">
                <el-dialog title="上传视频" :visible.sync="dialogUploadVideo" width="30%" center>
                    <el-form ref="form" v-model="videoForm" label-width="80px">
                        <el-form-item label="">
                            <el-input v-model="videoForm.videoName" placeholder="请输入视频名称" style="width: 350px;" />
                        </el-form-item>
                        <el-form-item label="">
                            <el-upload class="avatar-uploader el-upload--text" :drag="Plus"
                                :action="$url + '/chapter/uploadVideo'" multiple :show-file-list="false"
                                :data="{ videoName: this.videoForm.videoName }"
                                :on-success="handleVideoSuccess" :before-upload="beforeUploadVideo"
                                :on-progress="uploadVideoProcess">
                                <i v-if="Plus" class="el-icon-upload"></i>
                                <div v-if="Plus" class="el-upload__text">
                                    将文件拖到此处，或<em>点击上传</em>
                                </div>
                                <el-progress v-if="videoFlag == true" type="circle" :percentage="videoUploadPercent"
                                    style="margin-top: 30px"></el-progress>
                                <div class="el-upload__tip" slot="tip">
                                    只能上传mp4/flv/avi文件，且不超过5000M
                                </div>
                            </el-upload>
                        </el-form-item>
                    </el-form>
                </el-dialog>
            </div>
            <!-- 修改弹窗 -->
            <!-- <el-dialog title="修改" :visible.sync="updateVisible" style="width: 40%; margin: 0 auto;">

            </el-dialog> -->
            <div>
                <el-table :data="videoList" style="width: 100%" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" label="" width="100">
                    </el-table-column>
                    <el-table-column prop="id" label="视频序号" width="200">
                    </el-table-column>
                    <el-table-column prop="videoName" label="视频名称" width="200">
                    </el-table-column>
                    <el-table-column prop="original_name" label="视频原名" width="200">
                    </el-table-column>
                    <el-table-column prop="savePath" label="视频路径" width="900">
                    </el-table-column>
                    <el-table-column prop="" label="操作" width="300">
                        <template slot-scope="scope">
                            <el-button type="danger" size="small" @click="del (scope.row.id)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </el-card>

    </div>
</template>
<script>
import axios from 'axios';
import VueCookie from "vue-cookie";
export default {
    data() {
        return {
            selectedIds: [],// 视频id数组
            dialogUploadVideo: false,
            videoForm: {
                videoName: "",
            },
            videoList: [],
            videoFlag: false,
            Plus: true,
            Path: {
                url: 'E:/learn_Java/wei/SSMProject/GBLClassSpringBoot/KuYunClass',
            },
        }
    },
    methods: {
        // 返回上一个页面
        back() {
            this.$router.back()
        },
        //查询视频列表
        loadData() {
            axios({
                method: 'get',
                url: this.$url + "/chapter/findAllVideo?id=" + VueCookie.get("chapterId"),
            }).then((resp) => {
                this.videoList = resp.data
            })
        },
        handleSelectionChange(selection) {
            this.selectedIds = selection.map(item => item.id);
            console.log(this.selectedIds);
        },
        //批量删除
        batchDelete() {
            // 注释：如果有选择的项
            if (confirm("确实删除用户的提问吗？")) {
                if (this.selectedIds.length > 0) {
                    // 发送请求
                    axios({
                        method: 'post',
                        url: this.$url + "/chapter/deleteVideos?ids=" + this.selectedIds,

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
        },
        del(id) {
            if (confirm("确定删除吗？删除后无法复原！！！")) {
                axios({
                    method: 'post',
                    url: this.$url + "/chapter/deleteVideo?id= " + id,
                }).then((resp) => {
                    if (resp.data == 0) {
                        this.$message.success('删除成功!');
                    } else {
                        console.error("删除失败");
                    }
                })
            }
        },
        // 检查视频上传前的条件
        beforeUploadVideo(file) {
            const isLt300M = file.size / 1024 / 1024 < 5000;
            if (['video/mp4', 'video/ogg', 'video/flv', 'video/avi', 'video/wmv', 'video/rmvb'].indexOf(file.type) === -1) {
                this.$message.error('请上传正确的视频格式');
                return false;
            }
            if (!isLt300M) {
                this.$message.error('上传视频大小不能超过5000MB哦!');
                return false;
            }
        },
        // 视频上传过程中的处理
        uploadVideoProcess(event, file, fileList) {
            this.Plus = false;
            this.videoFlag = true;
            this.videoUploadPercent = + file.percentage.toFixed(0);
        },
        // 视频上传成功的处理
        handleVideoSuccess(res, file) {
            console.log(res.data);
            this.Plus = false;
            this.videoUploadPercent = 100;
            if (res.data === 0) {
                this.$message.success('视频上传成功！');

            } else if (res.data === 1) {
                this.$message.error('视频文件为空！');
            }
            else {
                this.$message.success('视频上传成功！');
                // this.$message.error('视频上传失败，请重新上传！');
            }
            this.loadData()
        }
    },
    //刷新页面
    created() {
        console.log(VueCookie.get("chapterId"));
        this.loadData()
    }
}
</script>
<style>
#headFont {
    font-size: 35px;
}
</style>