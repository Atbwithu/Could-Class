<!-- 分类管理 -->
<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span id="headFont">子分类列表</span>
                <el-button style="float: right; padding: 3px 0" type="text"
                    @click="dialogFormVisible = true">新增类别</el-button>

                <el-button style="float: right; padding: 3px 0" type="text" @click="back">返回</el-button>
            </div>
            <div>

            </div>

            <!-- 新增弹窗 -->
            <el-dialog title="新增类别" :visible.sync="dialogFormVisible" style="width: 40%; margin: 0 auto;">
                <el-form :model="addchildren">
                    <el-form-item label="类别名称" :label-width="formLabelWidth">
                        <el-input v-model="addchildren.cname" autocomplete="off" style="width: 300px;"></el-input>
                    </el-form-item>
                    <el-form-item label="类别名称" :label-width="formLabelWidth">
                        <el-select v-model="selectedValue" style="width: 300px;" disabled="true">
                            <el-option v-for="child in children" :key="child.id" :label="child.father">{{
                                child.father }}</el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="排序顺序" :label-width="formLabelWidth">
                        <el-input v-model="addchildren.sort" autocomplete="off" style="width: 300px;"></el-input>
                    </el-form-item>
                    <el-form-item label="" :label-width="formLabelWidth">
                        <el-button @click="add()">新增</el-button>
                        <el-button @click="dialogFormVisible = false">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <!-- 修改弹窗 -->
            <el-dialog title="修改类别" :visible.sync="updateVisible" style="width: 40%; margin: 0 auto;">
                <el-form :model="updChildren">
                    <el-form-item label="类别名称" :label-width="formLabelWidth">
                        <el-input v-model="updChildren.cname" autocomplete="off" style="width: 300px;"></el-input>
                    </el-form-item>
                    <el-form-item label="类别名称" :label-width="formLabelWidth">
                        <el-select v-model="selectedValue" style="width: 300px;" disabled="true">
                            <el-option v-for="child in children" :key="child.id" :label="child.father">{{
                                child.father }}</el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="排序顺序" :label-width="formLabelWidth">
                        <el-input v-model="updChildren.sort" autocomplete="off" style="width: 300px;"></el-input>
                    </el-form-item>
                    <el-form-item label="" :label-width="formLabelWidth">
                        <el-button @click="update()">修改</el-button>
                        <el-button @click="updateVisible = false">取消</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <div>
                <el-table :data="children" border style="width: 100%">
                    <el-table-column prop="id" label="#" width="200">
                    </el-table-column>
                    <el-table-column prop="cname" label="分类名称" width="300">
                    </el-table-column>
                    <el-table-column prop="father" label="父类型" width="300">
                    </el-table-column>
                    <el-table-column prop="sort" label="排序" width="300">
                    </el-table-column>
                    <el-table-column prop="createTime" label="创建时间" width="350">
                    </el-table-column>
                    <el-table-column prop="" label="操作" width="485">
                        <template slot-scope="scope">
                            <el-button type="danger" @click="delChildren(scope.row.id)">删除</el-button>
                            <el-button type="button" @click="handelUpdate(scope.row.id)">修改</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </el-card>

    </div>
</template>
<script>
import axios from 'axios';
import Cookie from "vue-cookie";
export default {
    data() {
        return {
            dialogFormVisible: false,   // 新增弹窗
            updateVisible: false,       //修改弹窗
            fatherName: "",              //父类名称
            updChildren: {},            //修改的对象
            addchildren: {},            // 新增的对象
            children: [],               //子类对象
            category: {},
            tableData: []
        }
    },
    methods: {
        loadDataChilren() {
            let id = Cookie.get("fatherId")
            axios({
                method: "get",
                url: this.$url + '/category/children?id=' + id,
            }).then((resp) => {
                this.children = resp.data
            })
        },
        //返回父类别管理
        back() {
            this.$router.push('/categoryPage')
        },
        delChildren(id) {
            axios({
                method: "post",
                url: this.$url + '/category/delChild?id=' + id,

            }).then((resp) => {
                this.updCategory = resp.data;
                this.loadDataChilren()
            })
        },
        //新增类别
        add() {
            const isInteger = /^\d+$/.test(this.addchildren.sort);
            if (isInteger) {
                axios({
                    method: "post",
                    url: this.$url + '/category/addChild',
                    params: this.addchildren
                }).then((resp) => {
                    if (resp.data == null) {
                        this.loadDataChilren()
                        this.$message.error('新增失败: ' + resp.data.message);

                    } else {
                        this.loadDataChilren()

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
        //修改查询
        handelUpdate(id) {
            this.updateVisible = true
            axios({
                method: "get",
                url: this.$url + '/category/selectChildren?id=' + id,
            }).then((resp) => {
                this.updChildren = resp.data;
            })
        },
        //修改
        update() {
            this.updateVisible = false
            axios({
                method: "post",
                url: this.$url + '/category/updateChildren',
                params: this.updChildren
            }).then((resp) => {
                if (resp.data == null) {

                    this.$message.error('修改失败: ' + resp.data.message);
                    this.loadDataChilren()
                } else {
                    this.loadDataChilren()

                    this.$message.success('修改成功');
                    this.dialogFormVisible = false
                }
            })
        }
    },
    //刷新页面

    created() {
        this.loadDataChilren()
    }


}
</script>
<style>
#headFont {
    font-size: 35px;
}
</style>