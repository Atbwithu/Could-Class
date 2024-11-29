<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span id="headFont">回收站</span>
                <!-- <el-button @click="batchDelete" style="float: right;" type="danger" icon="el-icon-minus"
                    size="mini">批量删除</el-button> -->
            </div>
            <div>
                <el-table :data="currentPageData.filter(item => item.isDelete == 1)" style="width: 100%;" size="small">
                    <el-table-column prop="id" label="#" width="100">
                    </el-table-column>
                    <el-table-column prop="courseName" label="课程名称" width="">
                    </el-table-column>
                    <el-table-column prop="category" label="类型" width="200">
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
                    <el-table-column label="操作" width="400">
                        <template slot-scope="scope">
                            <el-button size="mini" type="danger" @click="del(scope.row.id)">删除</el-button>
                            <el-button size="mini" type="success" @click="recover(scope.row.id)">恢复</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
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
import Cookie from "vue-cookie";
export default {
    data() {
        return {

            courseList: [],
            currentPage: 1, // 当前页码，初始化为 1
            pageSize: 10, // 每页显示的条数
            totalItems: 0 // 总数据条数
        }
    },
    methods: {
        recover(id) {
            this.$confirm('确实恢复该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //发起请求:带着id请求后端删除接口
                axios({
                    //请求地址
                    url: this.$url + "/course/recover?id=" + id,
                    //请求方式
                    method: 'post',
                }).then(() => {
                    //删除以后做的事情
                    this.courseLoad();
                })

                this.$message({
                    type: 'success',
                    message: '恢复成功!'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消恢复'
                });
            });
        },
        // 查询数据并计算总条数
        courseLoad() {
            axios({
                url: this.$url + "/course/list",
                method: "get",
            }).then((resp) => {
                this.courseList = resp.data;
                this.totalItems = resp.data.length;
            })
        },
        del(id) {

            if (confirm("确定删除吗？删除后无法复原！！！")) {
                axios({
                    method: 'post',
                    url: this.$url + "/course/delete?id= " + id,
                }).then((resp) => {

                    if (resp.data == 0) {
                        this.$message.success('删除成功!');
                    } else {
                        console.error("删除失败");
                    }
                    this.courseLoad()
                })
            }

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
            this.loadData();
        },
        // 当前页码改变时的处理函数
        handleCurrentChange(page) {
            this.currentPage = page;
        },

    },
    // 刷新页面时加载数据
    created() {
        this.courseLoad()
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
#headFont {
    font-size: 35px;
}
</style>