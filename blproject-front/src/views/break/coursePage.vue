<template>
    <div>
        <!-- 新增 -->
        <el-dialog title="添加课程" :visible.sync="dialogAddVisible">
            <el-form :model="addCourse" enctype="multpart/form-data">
                <el-form-item label="课程名称" :label-width="''"
                    :rules="{ required: true, message: '请输入课程名称', trigger: 'blur' }">
                    <el-input style="width: 60%;" v-model="addCourse.courseName" autocomplete="off"
                        placeholder="请输入课程名称"></el-input>
                </el-form-item>
                <el-form-item label="课程时长" :label-width="200" :rules="{ required: true, }">
                    <el-input style="width: 60%;" v-model="addCourse.courseTime" autocomplete="off"
                        placeholder="课程时长"></el-input>
                </el-form-item>
                <el-form-item label="方向" :label-width="'200'" :rules="{ required: true, }">
                    <el-select style="width: 60%;" v-model="addCourse.father" autocomplete="off">
                        <el-option v-for="item in typeList" :key="item.id" :label="item.name"
                            @click.native="getchildList(item.id)" :value="item.id">
                        </el-option>
                        <!-- 您可以根据实际需求添加更多的选项 -->
                    </el-select>
                </el-form-item>
                <el-form-item label="类别" :label-width="'200'" :rules="{ required: true, }">
                    <el-select style="width: 60%;" v-model="addCourse.child" autocomplete="off">
                        <el-option v-for="kid in childList" :key="kid.id" :label="kid.cname" :value="kid.id">
                            <span v-if="kid.cname === null"> </span>
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="导师" :label-width="'200'" :rules="{ required: true, }">
                    <el-input style="width: 60%;" v-model="addCourse.teacher" autocomplete="off"
                        placeholder="导师"></el-input>
                </el-form-item>
                <el-form-item label="排序" :label-width="'200'" :rules="{ required: true, }">
                    <el-input style="width: 60%;" v-model="addCourse.order" autocomplete="off"
                        placeholder="排序"></el-input>
                </el-form-item>
                <el-form-item label="封面" :label-width="'200'" :rules="{ required: true, }">
                    <el-upload class="upload-demo" drag action="" :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload" :on-change="handleFileChange" :auto-upload="false"
                        ref="upload" :limit="1" :on-exceed="handleExceed" multiple>
                        <img v-if="addCourse.cover" :src="addCourse.cover" class="avatar">
                        <i class="el-icon-upload"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item label="推荐" :label-width="'200'" :rules="{ required: true, }">
                    <el-select style="width: 60%;" v-model="addCourse.rec" autocomplete="off">
                        <el-option value="0" key="请选择" label="请选择">请选择</el-option>
                        <el-option value="1" key="推荐" label="推荐">推荐</el-option>
                        <el-option value="2" key="不推荐" label="不推荐">不推荐</el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogAddVisible = false">取 消</el-button>
                <el-button type="primary" @click=subAddForm>确 定</el-button>
            </div>
        </el-dialog>

        <!-- 编辑 -->
        <el-dialog title="编辑用户" :visible.sync="dialogEditVisible">
            <el-form :model="course" enctype="multpart/form-data">
                <el-form-item v-show="false" :label="''" :label-width="200"
                    :rules="{ required: true, trigger: 'blur' }">
                    <el-input style="width: 60%;" v-model="course.id" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="课程名称：" :label-width="200" :rules="{ required: true, trigger: 'blur' }">
                    <el-input style="width: 60%;" v-model="course.courseName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="课程时长" :label-width="200" :rules="{ required: true, trigger: 'blur' }">
                    <el-input style="width: 60%;" v-model="course.courseTime" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="方向" :label-width="'200'" :rules="{ required: true, }">
                    <el-select style="width: 60%;" v-model="course.fatherId" autocomplete="off">
                        <el-option v-for="item in typeList" :key="item.id" :label="item.name"
                            @click.native="getchildList(item.id)" :value="item.id">
                        </el-option>
                        <!-- 您可以根据实际需求添加更多的选项 -->
                    </el-select>
                </el-form-item>
                <el-form-item label="类别" :label-width="'200'" :rules="{ required: true, }">
                    <el-select style="width: 60%;" v-model="course.childId" autocomplete="off">
                        <el-option v-for="kid in childList" :key="kid.id" :label="kid.cname" :value="kid.id">
                            <span v-if="kid.cname === null"> </span>
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="导师" :label-width="200" :rules="{ required: true, trigger: 'blur' }">
                    <el-input style="width: 60%;" v-model="course.teacher" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="排序" :label-width="200" :rules="{ required: true, trigger: 'blur' }">
                    <el-input style="width: 60%;" v-model="course.order" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="封面" :label-width="200" :rules="{ required: true, trigger: 'blur' }">
                    <div style="display: flex; align-items: center;">
                        <el-upload style="float: left;" class="upload-demo" drag action=""
                            :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload"
                            :on-change="handleFileChange" :auto-upload="false" ref="upload" :limit="1"
                            :on-exceed="handleExceed" multiple>
                            <i class="el-icon-upload" style="font-size: 24px; color: #666;"></i>
                        </el-upload>
                        <img v-if="course.cover" :src="course.cover" class="avatar"
                            style="width: 300px; height: 200px; margin-left:50px ; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);">
                    </div>
                </el-form-item>
                <!-- <el-form-item label="推荐" :label-width="formLabelWidth" :rules="{ required: true, trigger: 'blur' }">
                    <el-select style="width: 60%;" v-model="form.rec" autocomplete="off">
                        <el-option value="0">请选择</el-option>
                        <el-option value="1" key="推荐" label="推荐">推荐</el-option>
                        <el-option value="2" key="不推荐" label="不推荐">不推荐</el-option>
                    </el-select>
                </el-form-item> -->


            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogEditVisible = false">取 消</el-button>
                <el-button type="primary" @click="subEditForm()">确 定</el-button>
            </div>
        </el-dialog>
        <el-card>
            <h1 style="font-size: 30px;">课程管理</h1>
            <hr><br />
            <div>
                <el-form :inline="true" :model="course" class="demo-form-inline">
                    <el-form-item label="课程名称">
                        <el-input v-model="course.courseName" clearable placeholder="请输入课程名称"></el-input>
                    </el-form-item>
                    <el-form-item label="导师">
                        <el-input v-model="course.teacher" clearable placeholder="请输入导师"></el-input>
                    </el-form-item>
                    <el-form-item label="是否推荐">
                        <el-select v-model="course.rec" clearable placeholder="请选择" @change="handleSelectChange">
                            <el-option value="1" key="推荐" label="推荐">推荐</el-option>
                            <el-option value="5" key="不推荐" label="不推荐">不推荐</el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="findCourse">查询</el-button>
                        <el-button type="info" @click="res()">重置</el-button>
                        <el-button type="primary" @click="dialogAddVisible = true">新增</el-button>
                    </el-form-item>
                </el-form>
            </div>
            <el-table :data="currentPageData.filter(item => item.isDelete != 1)" style="width: 100%;" size="small">
                <el-table-column prop="id" label="#" width="100">
                </el-table-column>
                <el-table-column prop="courseName" label="课程名称" width="">
                </el-table-column>
                <el-table-column prop="category" label="类型" width="">
                </el-table-column>
                <el-table-column prop="teacher" label="教师" width="">
                </el-table-column>
                <el-table-column prop="courseTime" label="课程时长" width="">
                </el-table-column>
                <el-table-column prop="order" label="排序" width="">
                </el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="">
                </el-table-column>
                <el-table-column label="推荐">
                    <template slot-scope="scope">
                        <span style="color: chocolate;" v-if="scope.row.rec == 1">推荐</span>
                        <span v-else>不推荐</span>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="350px">
                    <template slot-scope="scope">
                        <el-button type="text" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button size="mini" v-if="scope.row.rec == 1" type="warning"
                            @click="handleEffect(scope.$index, scope.row)">不推荐</el-button>
                        <el-button size="mini" v-if="scope.row.rec == 5" type="success"
                            @click="handleEffect2(scope.$index, scope.row)">推荐</el-button>
                        <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
                        <el-button size="mini" type="success" @click="selChapter(scope.row.id)">章节列表</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <!-- 分页组件 -->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[5, 10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="totalItems">
        </el-pagination>
    </div>
</template>
<script>
import axios from 'axios';
import VueCookie from 'vue-cookie';
export default {
    data() {
        return {
            course: {
                rec: null
            },
            typeList: [],
            childList: [],
            fileList: [],
            addCourse: {}, //新增课程的对象
            editCourse: {}, //修改编辑课程的对象
            courseList: [],
            dialogAddVisible: false,
            dialogEditVisible: false,
            currentPage: 1, // 当前页码，初始化为 1
            pageSize: 15, // 每页显示的条数
            totalItems: 0 // 总数据条数
        }
    },
    methods: {
        handleSelectChange(value) {
            if (!value) {
                // 处理值被清空的情况  
                this.$message.warning("值错误")
            }
        },
        courseLoad() {

            axios({
                url: this.$url + "/course/list",
                method: "get",
            }).then((resp) => {
                this.courseList = resp.data;
                this.totalItems = resp.data.length;
            })
        },
        findCourse() {

            if (this.course.rec !== null && this.course.rec !== '') {
                axios({
                    url: this.$url + "/course/list",
                    method: "get",
                    params: this.course
                }).then((resp) => {
                    if (resp.data == null) {
                        this.$message.error('查询失败: ' + resp.data.message);
                    } else {
                        console.log(resp.data);
                        this.courseList = resp.data;
                        this.$message.success('查询成功');
                    }
                    this.courseList = resp.data;
                    this.totalItems = resp.data.length;
                })
            } else {
                this.res()
            }

        },
        handleFileChange(file, fileList) {
            // 更新fileList以跟踪上传的文件
            this.fileList = fileList;
            // 更新文件是否已选择的状态
            this.addCourse.hasCover = fileList.length > 0;
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },
        handleAvatarSuccess(response, file) {
            this.addCourse.imageUrl = response.url;  // 假设后端返回了一个包含url的对象
            // 假设服务器返回了图片链接，并存储在response.data中
            this.addCourse.image = response.data;
            // 将上传成功的文件添加到fileList中，以便在界面上显示
            this.fileList.push({ name: file.name, url: response.data });
        },
        handleExceed(files, fileList) {
            this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
        },
        //新增
        subAddForm() {
            let formData = new FormData();
            formData.append("courseName", this.addCourse.courseName);
            formData.append("courseTime", this.addCourse.courseTime);
            formData.append("teacher", this.addCourse.teacher);
            formData.append("father", this.addCourse.father);
            formData.append("child", this.addCourse.child);
            formData.append("order", this.addCourse.order);
            formData.append("rec", this.addCourse.rec);
            console.log(this.fileList.length);

            if (this.fileList.length > 0) {
                let file = this.fileList[0].raw; // 假设 fileList 中的元素直接就是文件对象  
                formData.append('file', file);
                console.log(formData)
                axios({
                    method: "post",
                    url: this.$url + "/course/insert",
                    data: formData
                }).then(response => {
                    // 处理响应...  
                    console.log(response.data);
                    this.courseLoad();
                    // 清空数据  
                    // this.resetForm(); // 考虑使用一个方法来统一清空表单数据  
                })
                    .catch(error => {
                        // 处理错误...  
                        console.error(error);
                        console.log(formData)
                        alert('上传失败，请检查网络连接或文件是否有效。');
                    });
            } else {
                alert('请选择上传文件！');
                console.error('无法获取上传文件');
            }
            //处理返回值
            this.dialogAddVisible = false
            //再查一次
            this.courseLoad();
            //清空数据
            this.addCourse.courseName = '';
            this.addCourse.courseTime = '';
            this.addCourse.type = '';
            this.addCourse.teacher = '';
            this.addCourse.ordera = '';
            this.addCourse.cover = null;
            this.addCourse.subjectChapter = '';
            this.addCourse.rec = '';
            // 处理返回值，比如关闭对话框、刷新列表等  
            this.dialogAddVisible = false;


        },
        //编辑
        handleEdit(index, row) {
            console.log(row.id);
            this.dialogEditVisible = true;
            //调用，根据id查询
            axios({
                url: this.$url + "/course/selectById?id=" + row.id,
                method: "post"
            }).then((resp) => {
                //将拿到的数据交给tableData
                this.course = resp.data;
            })
        },
        //修改
        subEditForm() {
            let formData = new FormData();
            formData.append("id", this.course.id);
            formData.append("courseName", this.course.courseName);
            formData.append("courseTime", this.course.courseTime);
            formData.append("order", this.course.order);
            formData.append("teacher", this.course.teacher);

            if (this.fileList.length > 0) {
                let file = this.fileList[0].raw; // 假设 fileList 中的元素直接就是文件对象
                formData.append('file', file);
            }

            axios({
                // 请求方式
                method: "post",
                // 请求地址
                url: this.$url + "/course/update",
                // 请求数据（包含表单数据和文件）
                data: formData
            }).then(() => {
                // 处理返回值
                this.dialogEditVisible = false;
                // 再查一次
                this.courseLoad();
                // 清空数据
                this.course.courseName = '';
                this.course.order = '';
                this.course.type = '';
                this.course.courseTime = '';
                this.course.teacher = '';
                this.course.cover = null;
            })
                .catch(error => {
                    // 处理错误...
                    console.error(error);
                    alert('上传失败，请检查网络连接或文件是否有效。');
                });
        },
        //删除
        handleDelete(id) {

            this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //发起请求:带着id请求后端删除接口
                axios({
                    //请求地址
                    url: this.$url + "/course/fakeDelete?id=" + id,
                    //请求方式
                    method: 'post',
                }).then(() => {
                    //删除以后做的事情
                    this.courseLoad();
                })

                this.$message({
                    type: 'success',
                    message: '删除成功!'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });

        },
        //修改推荐
        handleEffect(index, row) {
            axios({
                url: this.$url + "/course/updateRecById1?id=" + row.id,
                method: "post",
                params: this.form,
            }).then(() => {
                this.courseLoad();
            })
        },
        handleEffect2(index, row) {
            axios({
                url: this.$url + "/course/updateRecById2?id=" + row.id,
                method: "post",
                params: this.form,
            }).then(() => {
                //再查一次
                this.courseLoad();
            })
        },
        //根据课程名称查询章节
        selChapter(id) {
            VueCookie.set("courseId", id)
            this.$router.push('/chapterList')
        },
        //父类别
        getName() {
            axios({
                method: 'get',
                url: this.$url + "/category/getName",
            }).then((resp) => {
                this.typeList = resp.data
            })
        },
        //查询父类够查询子类

        getchildList(id) {
            console.log(id);
            axios({
                method: 'post',
                url: this.$url + "/category/getChildrenName?id=" + id,
            }).then((resp) => {
                this.childList = resp.data
            })
        },
        // 根据当前页码和每页条数计算当前页数据
        getCurrentPageData() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = start + this.pageSize;
            return this.courseList.slice(start, end);
        },
        // 每页条数改变时的处理函数
        handleSizeChange(size) {
            this.pageSize = size;
            this.currentPage = 1;
            this.courseLoad();
        },
        // 当前页码改变时的处理函数
        handleCurrentChange(page) {
            this.currentPage = page;
        },
        res() {
            this.courseLoad()
            this.getName()
        }
    },

    watch: {
        tableData: {
            handler(newValue) {
                this.courseCount = newValue.length;
            },
            deep: true
        },

    },


    created() {
        this.courseLoad()
        this.getName()
    },
    computed: {
        // 计算当前页要显示的数据
        currentPageData() {
            return this.getCurrentPageData();
        }
    }
}
</script>
<style>
.avatar:hover {
    transform: scale(1.1);
}

.avatar {
    opacity: 0.8;
}
</style>
