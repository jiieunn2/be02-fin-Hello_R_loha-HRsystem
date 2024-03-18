import { createRouter, createWebHistory } from "vue-router";


import SignUpPage from "../pages/SignUpPage.vue";
import LoginPage from "../pages/LoginPage.vue";
import MainPage from "../pages/MainPage.vue";
const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/signup", component: SignUpPage },
    { path: "/login", component: LoginPage },
    { path: "/", component: MainPage },
  ],
});

export default router;

