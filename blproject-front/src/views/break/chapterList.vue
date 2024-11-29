<template>
  <div>
    <el-button type="primary" @click="dialogVisible = true">新增</el-button>
    <el-button type="primary" @click="back">返回章节列表</el-button>
    <el-button @click="batchDelete" style="float: right;" type="danger" icon="el-icon-minus"
      size="mini">批量删除</el-button>
    <!-- 新增章节对话框 -->
    <el-dialog title="新增章节" :visible.sync="dialogVisible" width="50%">
      <el-form :model="chapterForm" ref="chapterForm" label-width="120px">
        <el-form-item label="章节名称">
          <el-input v-model="chapterForm.name" @input="validateName"></el-input>
        </el-form-item>
        <div class="button-group">
          <el-button type="primary" @click="addChapter">提交</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </div>
      </el-form>
    </el-dialog>

    <!-- 章节列表表格 -->
    <el-table :data="chapterList" border style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" label="" width="100">
      </el-table-column>
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="name" label="章节名称"></el-table-column>
      <el-table-column prop="courseName" label="课程名称"></el-table-column>
      <el-table-column prop="askNum" label="提问次数"></el-table-column>
      <el-table-column prop="learnNUm" label="学习次数"></el-table-column>
      <el-table-column prop="createTime" label="时间"></el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="updata(scope.row.id)">编辑</el-button>
          <el-button type="text" style="color: red;" size="small" @click="del(scope.row.id)">删除</el-button>
          <el-button type="text" size="small" @click="sreachVideo(scope.row.id)">查看视频列表</el-button>
        </template>
      </el-table-column>
    </el-table>



    <!-- 查看所有视频对话框 -->

  </div>
</template>

<script>
import router from "@/router";
import axios from "axios";
import VueCookie from "vue-cookie";

export default {
  name: "goods-page",
  data() {
    return {

      savePath: "src/assets/videoResource",
      fileList: [],
      chapterList: [],
      chapterForm: {},
      selectedIds: [],
      form: {},
      currentVideoSrc: '',
      dialogVisible: false,
      dialogAllVideo: false,
      videoList: [],
      formData: '',



    };
  },
  methods: {
    handleSelectionChange(selection) {
      this.selectedIds = selection.map(item => item.id);
      console.log(this.selectedIds);
    },
    // 跳转到课程页面
    back() {
      this.$router.push('/coursePage')
    },
    // 验证章节名称是否为空
    validateName() {
      if (this.chapterForm.name.trim() === '') {
        this.$message.error('章节名称不能为空');
      }
    },
    // 处理文件上传成功的逻辑
    handleFileChange(file, fileList) {
      this.fileList = fileList;
    },
    // 提交新增章节的表单
    submitChapter() {
      if (this.chapterForm.name.trim() === '') {
        this.$message.error('章节名称不能为空');
        return;
      }

      const formData = new FormData();
      formData.append('savePath', this.savePath);
      formData.append('chapterName', this.chapterForm.name);
      this.fileList.forEach(file => {
        formData.append('file', file.raw);
      });

      axios.post('http://localhost:8082/chapter/uploadVideo', formData, {
        headers: {
          'Content-Type': 'ultipart/form-data'
        }
      })
        .then(response => {
          this.dialogVisible = false;
          this.fileList = [];
          this.chapterForm.name = '';
        })
        .catch(error => {
          console.error('Error submitting chapter:', error);
        });
    },
    // 新增章节
    addChapter() {
      
      axios({
        method: 'post',
        url: `${this.$url}/chapter/addChapter?name=${this.chapterForm.name}&courseId=${VueCookie.get("courseId")}`
      })
        .then((resp) => {
          resp.data
          this.res()
        })
    },
    // 查询章节下的所有视频
    sreachVideo(id) {
      VueCookie.set("chapterId", id)
      this.$router.push('/videoList')
    },
    //批量删除
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
    },
    del(id) {
      if (confirm("确定删除吗？删除后无法复原！！！")) {
        axios({
          method: 'post',
          url: this.$url + "/chapter/deleteChapterById?id= " + id,
        }).then((resp) => {

          if (resp.data == 0) {
            this.$message.success('删除成功!');
          } else {
            console.error("删除失败");
          }
          this.res()
        })
      }

    },
    res() {
      axios({
        url: `${this.$url}/chapter/findAll?id=${VueCookie.get("courseId")}`,
        method: "post",
      })
        .then((resp) => {
          this.chapterList = resp.data;
        });
    }
  },
  created() {
    this.res()

  }
};
</script>

<style scoped>
.custom-upload {
  border: 2px dashed #409eff;
  padding: 20px;
  text-align: center;
  border-radius: 5px;
}

.centered-div {
  text-align: center;
}

.upload-tip {
  color: #999;
  font-size: 14px;
}

.custom-spinner {
  color: #409eff;
}
</style>