<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <h1 style="font-size: 30px;">分类管理</h1>
                <el-button @click="batchDelete" style="float: right;" type="danger" icon="el-icon-minus"
                    size="mini">批量删除</el-button>
                <el-button style="float: right; padding: 3px 0" type="text"
                    @click="dialogFormVisible = true">新增类别</el-button>
            </div>
            <div>
                <span>
                    <el-form :model="category">
                        <el-form-item label="">
                            <el-input v-model="category.name" clearable placeholder="请输入类别名称" style="width: 200px;" />
                            <el-button icon="el-icon-search" circle @click="search()"></el-button>
                        </el-form-item>
                    </el-form>
                </span>
            </div>
            <!-- 新增弹窗 -->
            <el-dialog title="新增类别" :visible.sync="dialogFormVisible" style="width: 40%; margin: 0 auto;">
                <el-form :model="addCategory">
                    <el-form-item label="类别名称" :label-width="formLabelWidth">
                        <el-input v-model="addCategory.name" autocomplete="off" style="width: 300px;"></el-input>
                    </el-form-item>
                    <el-form-item label="类别名称" :label-width="formLabelWidth">
                        <el-select v-model="addCategory.parent" disabled="true" placeholder="无" style="width: 300px;">
                            <el-option label="区域一" value="shanghai"></el-option>
                            <el-option label="区域二" value="beijing"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="排序顺序" :label-width="formLabelWidth">
                        <el-input v-model="addCategory.sort" autocomplete="off" style="width: 300px;"></el-input>
                    </el-form-item>
                    <el-form-item label="" :label-width="formLabelWidth">
                        <el-button @click="add()">新增</el-button>
                        <el-button @click="dialogFormVisible = false">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <!-- 修改弹窗 -->
            <el-dialog title="修改类别" :visible.sync="updateVisible" style="width: 40%; margin: 0 auto;">
                <el-form :model="updCategory">
                    <el-form-item label="类别名称" :label-width="formLabelWidth">
                        <el-input v-model="updCategory.name" autocomplete="off" style="width: 300px;"></el-input>
                    </el-form-item>
                    <el-form-item label="类别名称" :label-width="formLabelWidth">
                        <el-select disabled="true" placeholder="父类" style="width: 300px;">

                        </el-select>
                    </el-form-item>
                    <el-form-item label="排序顺序" :label-width="formLabelWidth">
                        <el-input v-model="updCategory.sort" autocomplete="off" style="width: 300px;"></el-input>
                    </el-form-item>
                    <el-form-item label="" :label-width="formLabelWidth">
                        <el-button @click="update()">修改</el-button>
                        <el-button @click="updateVisible = false">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <div>

                <el-table :data="currentPageData" border style="width: 100%" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" width="80">
                    </el-table-column>
                    <el-table-column prop="id" label="#" width="80">
                    </el-table-column>
                    <el-table-column prop="name" label="分类名称" width="">
                    </el-table-column>
                    <el-table-column prop="parent" label="父类型" width="">
                        <template slot-scope="scope">
                            <span style="color: chocolate;" v-if="scope.row.parent == 0">无</span>
                            <span v-else>其他</span>
                        </template>
                    </el-table-column>
                    <el-table-column prop="sort" label="排序" width="">
                    </el-table-column>
                    <el-table-column prop="createTime" label="创建时间">
                    </el-table-column>
                    <el-table-column prop="" label="操作">
                        <template slot-scope="scope">
                            <el-button type="button" @click="handelUpdate(scope.row.id)">修改</el-button>
                            <el-button type="danger" @click="delKind(scope.row.id)">删除</el-button>
                            <el-button type="button" @click="findChildren(scope.row.id)">查询子类别</el-button>
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
            dialogFormVisible: false,
            updateVisible: false,
            category: {},
            updCategory: {},
            addCategory: {},
            categoryChildren: {},
            fatherIds: [],
            tableData: [],
            currentPage: 1, // 当前页码，初始化为 1
            pageSize: 10, // 每页显示的条数
            totalItems: 0 // 总数据条数
        }
    },
    methods: {
        // 查询数据并计算总条数
        search() {
            axios({
                method: 'get',
                url: this.$url + "/category/list",
                params: this.category
            }).then((resp) => {
                this.tableData = resp.data;
                this.totalItems = resp.data.length;
            })
        },
        // 加载数据并计算总条数
        loadData() {
            axios({
                method: 'get',
                url: this.$url + "/category/list",
            }).then((resp) => {
                this.tableData = resp.data;
                this.totalItems = resp.data.length;
            })
        },
        // 根据当前页码和每页条数计算当前页数据
        getCurrentPageData() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = start + this.pageSize;
            return this.tableData.slice(start, end);
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
        // 查询子类
        findChildren(id) {
            Cookie.set("fatherId", id)
            // axios({
            //     method: "get",
            //     url: this.$url + '/category/children?id=' + id,
            // }).then((resp) => {
            //     this.categoryChildren = resp.data
            //     localStorage.setItem("categoryChildren", JSON.stringify(resp.data))
            //     this.$router.push('/categoryChildren')
            // })
            this.$router.push('/categoryChildren')
        },
        // 新增类别
        add() {
            const isInteger = /^\d+$/.test(this.addCategory.sort);
            if (isInteger) {
                axios({
                    method: "post",
                    url: this.$url + '/category/insert',
                    params: this.addCategory
                }).then((resp) => {
                    if (resp.data < 0) {
                        this.loadData()
                        this.$message.error('新增失败: ' + resp.data.message);

                    } else {
                        this.loadData()

                        this.$message.success('新增成功');
                        this.dialogFormVisible = false
                    }
                })
            } else {
                const h = this.$createElement;

                this.$notify({
                    title: '输入错误',
                    message: h('i', { style: 'color: red' }, '顺序必须输入数字')
                });
            }
        },
        // 删除类别
        delKind(id) {
            axios({
                method: "post",
                url: this.$url + '/category/delete?id=' + id,

            }).then((resp) => {
                this.updCategory = resp.data;
                this.loadData()
            })
        },
        // 修改查询
        handelUpdate(id) {
            this.updateVisible = true
            axios({
                method: "get",
                url: this.$url + '/category/selectById?id=' + id,

            }).then((resp) => {
                this.updCategory = resp.data;
            })
        },
        // 修改
        update() {
            this.updateVisible = false
            axios({
                method: "get",
                url: this.$url + '/category/update',
                params: this.updCategory
            }).then((resp) => {
                if (resp.data == null) {
                    this.loadData()
                    this.$message.error('修改失败: ' + resp.data.message);

                } else {
                    this.loadData()

                    this.$message.success('修改成功');
                    this.dialogFormVisible = false
                }
            })
        },
        // 将数据放入数组中
        handleSelectionChange(selection) {
            this.fatherIds = selection.map(item => item.id);
            console.log(this.fatherIds);
        },
        batchDelete() {
            // 注释：如果有选择的项
            if (confirm("确实删除用户的提问吗？")) {
                if (this.fatherIds.length > 0) {
                    // 发送请求
                    axios({
                        method: 'post',
                        url: this.$url + "/category/deleteMany?ids=" + this.fatherIds,
                    })
                        .then(response => {

                            this.$message.success('删除成功!');
                            this.loadData()
                        })
                        .catch(error => {
                            // 处理请求失败的情况
                            console.error(error);
                        });
                    this.fatherIds = [];
                } else {
                    this.$message.warning('请先选择要删除的项');
                }
            }
        }

    },
    // 刷新页面时加载数据
    created() {
        this.loadData()
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