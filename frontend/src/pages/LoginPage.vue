<template>
    <HeaderComponent />
    <SideBar />
  
    <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Login</h3></div>
                                    <div class="card-body">
                                        <form>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputEmail" type="email" placeholder="name@example.com" v-model="username" />
                                                <label for="inputEmail">Email address</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputPassword" type="password" placeholder="Password" v-model="password"/>
                                                <label for="inputPassword">Password</label>
                                            </div>
                                            <div class="form-check mb-3">
                                                <input class="form-check-input" id="inputRememberPassword" type="checkbox" value="" />
                                                <label class="form-check-label" for="inputRememberPassword">Remember Password</label>
                                            </div>
                                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <a class="small" href="password.html">Forgot Password?</a>
                                                <a class="btn btn-primary" @click="login" >Login</a>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="register.html" >Need an account? Sign up!</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2023</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
  
  
  
  
  
  </template>
  
  
  <script>
  import SideBar from '../components/SideBar.vue'
  import HeaderComponent from '../components/HeaderComponent.vue'
  import axios from 'axios';
  export default {
    name: 'LoginPage',
    components: {
      SideBar,
      HeaderComponent,
    },
    data() {
    return {
      responseData: null,
      username:'',
      password:'',
    };
  },
  methods: {
    login() {
      console.log("click");
      // const api = process.env.VUE_APP_BACKEND_URL;
      const api = 'http://localhost:8080';
      console.log(api);
      let formData = new FormData();
      formData.append('username', this.username);
      formData.append('password', this.password);
      axios.post(api+'/employee/login',formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
        .then(response => {
          console.log('Response:', response.data);
          this.responseData = response.data;
          sessionStorage.setItem('token', response.data.result.token);
        })
        .catch(error => {
          console.error('Error updating data:', error);
        });
    }
  },
  }
  </script>
  
  
  
  <style>
  .container {
    padding-top: 50px;
  }
  
  #layoutSidenav_content {
    padding-left: 225px;
    top: 56px;
  }
  </style>