import Vue from "vue";
import VueRouter from "vue-router";

// 定义路由组件
const IndexPage = () => import("@/views/break/indexPage.vue");
const TestPage = () => import("@/views/break/testPage.vue");
const LoginPage = () => import("@/views/front/loginPage.vue");
const HomePage = () => import("@/views/cwz/homePage.vue");
const HomeContent = () => import("@/views/cwz/homeContent.vue");
const PersonalCenter = () => import("@/views/cwz/PersonalCenter.vue");
const CollectionList = () => import("@/components/collectionList.vue");
const MyQuestionComponent = () => import("@/components/myQuestionComponent.vue");
const PersonalInformation = () => import("@/components/personalInformation.vue");
const SubjectPage = () => import("@/views/front/subjectPage.vue");
const ChapterPage = () => import("@/views/front/chapterPage.vue");
const VideoPage = () => import("@/views/front/videoPage.vue");
const videoList = () => import("@/views/break/videoList.vue");
const AskPage = () => import("@/views/break/askPage.vue");
const TopPage = () => import("@/views/break/topPage.vue");
const UserPage = () => import("@/views/break/userPage.vue");
const LearnPage = () => import("@/views/break/learnPage.vue");
const CoursePage = () => import("@/views/break/coursePage.vue");
const courseRecycle = () => import("@/views/break/courseRecycle.vue");
const ChapterList = () => import("@/views/break/chapterList.vue");
const CategoryPage = () => import("@/views/break/categoryPage.vue");
const CategoryChildren = () => import("@/views/break/categoryChildren.vue");
const rolePage = () => import("@/views/cwz/rolePage.vue");

// 定义路由配置数组，并添加 meta 字段标记权限
const routes = [
  {
    name: "indexPage",
    path: "/indexPage",
    component: IndexPage,
    meta: { requiredPermission: "1" }, // 假设只有管理员能访问
    children: [
      { path: '/askPage', component: AskPage, meta: {  requiredPermission: ["admin", "teacher"] } }, // 假设普通用户能访问
      { path: '/', component: TopPage, meta: { requiredPermission: ["admin", "teacher"] } },
      { path: '/userPage', component: UserPage, meta: { requiredPermission: ["admin", "teacher"] } },
      { path: '/learnPage', component: LearnPage, meta: { requiredPermission: ["admin", "teacher"] } },
      { path: '/coursePage', component: CoursePage, meta: { requiredPermission: ["admin", "teacher"] } },
      { path: '/chapterList', component: ChapterList, meta: { requiredPermission: ["admin", "teacher"] } },
      { path: '/videoList', component: videoList, meta: { requiredPermission: ["admin", "teacher"] } },
      { path: '/categoryPage', component: CategoryPage, meta: { requiredPermission: ["admin", "teacher"] } },
      { path: '/courseRecycle', component: courseRecycle, meta: { requiredPermission: ["admin", "teacher"] } },
      { path: '/categoryChildren', component: CategoryChildren, meta: { requiredPermission: ["admin", "teacher"] } },
      { path: '/role', component: rolePage, meta: { requiredPermission: ["admin", "teacher"] } }
    ]
  },
  {
    name: "testPage",
    path: "/testPage",
    component: TestPage,
    meta: { requiredPermission: "admin" }
  },
  {
    name: "loginPage",
    path: "/",
    component: LoginPage
  },
  {
    name: "homePage",
    path: "/homePage",
    component: HomePage,
    meta: { requiredPermission: "user" },
    children: [
      { path: '/', component: HomeContent, meta: { requiredPermission: "user" } },
      { path: '/personalCenter', component: PersonalCenter, meta: { requiredPermission: "user" } ,
        children:[
          {path: '/collectionList', component: CollectionList,},
          {path: '/myQuestionComponent', component: MyQuestionComponent,},
          {path: '', component: PersonalInformation,}
        ]
      },
      { path: '/subjectPage', component: SubjectPage, meta: { requiredPermission: "user" } },
      { path: '/chapterPage', component: ChapterPage, meta: { requiredPermission: "user" } },
      { path: '/videoPage', component: VideoPage, meta: { requiredPermission: "user" } }
    ]
  }
  // 路由嵌套
  // {
  //   path: '/homePage',
  //   name: 'homePage',
  //   component: () => import('../views/cwz/homePage.vue'),
  //   redirect:'homeContent',
  //   children:[
  //     {
  //       path:'/homeContent', 
  //       component:()=>import("../views/cwz/homeContent.vue"),
  //       name: 'HomeContent'
  //     },
  //     {
  //       path:'/PersonalCenter', 
  //       component:()=>import("../views/cwz/PersonalCenter.vue"),
  //       children: [
  //         {
  //           path: '',  //空路径表示默认子路由
  //           component: () => import("@/components/personalInformation.vue"),
  //           name: 'PersonalInformation'
  //         },
  //         {  
  //           path: 'collection', //不需要空路径，直接写子路由路径  
  //           component: () => import("@/components/collectionList.vue"),  
  //           name: 'collectionPage' //给这个路由起个名字 
  //         },
  //         {  
  //           path: 'Myquestion', //不需要空路径，直接写子路由路径 
  //           component: () => import("/components/myQuestionComponent.vue"),  
  //           name: 'Myquestion' //给这个路由起个名字  
  //         },
  //       ]
  //     },
  //   ]
  // },
  
];

Vue.use(VueRouter);

// 创建路由实例
const router = new VueRouter({
  mode: "history",
  routes
});

// 路由导航守卫
// router.beforeEach((to, from, next) => {
//   // 获取用户权限信息，假设存储在 Vuex 或 localStorage 中
//   const userPermission = localStorage.getItem("userPermission"); 
//   // 如果路由需要权限且用户没有权限，则重定向到登录页面或其他提示页面
//   if (to.meta.requiredPermission && userPermission!== to.meta.requiredPermission) {
//     if (userPermission === null) {
//       next("/login"); // 重定向到登录页面
//     } else {
//       next(false); // 禁止访问
//     }
//   } else {
//     next(); // 允许访问
//   }
// });

// 导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
  if (to.path === '/') {
    next();
  } else {
    let token = localStorage.getItem('token');
    if (token === null || token === '') {
      next('/');
    } else {
      next();
    }
  }

});

export default router;