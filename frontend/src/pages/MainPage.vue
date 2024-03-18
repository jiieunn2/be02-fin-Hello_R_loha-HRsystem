<template>
    <HeaderComponent />
    <SideBar />

    <div id="commute-info">
        <img class="profile-pic"
            src="https://png.pngtree.com/png-clipart/20191121/original/pngtree-user-vector-icon-png-image_5152508.jpg"
            alt="Profile Picture">
        <h2>안녕하세요, 이아영님!</h2>
        <!-- 나중에는 여기 직원 이름이 오도록 -->

        <div class="button-container">
            <button id="commuteButton" @click="commute">출근</button>
        </div>

        <h1 id="sumTime" class="time">총 업무시간 </h1>
        <h1 id="startTime" class="time">근무 시작 </h1>
        <h1 id="endTime" class="time">근무 종료 </h1>

    </div>




</template>


<script>
import SideBar from '../components/SideBar.vue'
import HeaderComponent from '../components/HeaderComponent.vue'
import axios from 'axios';
export default {
    name: 'MainPage',
    components: {
        SideBar,
        HeaderComponent,
    },
    data() {
        return {
            responseData: null,
            username: '',
            password: '',
        };
    },
    methods: {
        commute() {
            console.log("click");
            // const api = process.env.VUE_APP_BACKEND_URL;
            const api = 'http://localhost:8080';
            console.log(api);
            // let formData = new FormData();
            // formData.append('username', this.username);
            // formData.append('password', this.password);
            const token = sessionStorage.getItem('token');
            axios.post(api + '/employee/commute',null, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token,
                }
            })
                .then(response => {
                    console.log('Response:', response.data);
                    this.responseData = response.data;
                   
                })
                .catch(error => {
                    console.error('Error updating data:', error);
                });
        }
    },
}
</script>



<style>
/* button {
    border: none;
    outline: none;
    padding: 10px 20px;
    margin: 10px;
    font-size: 12px;
    background-color: #f7c231;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    width: 187px;
    height: 35px;
} */

#commuteButton {
    border: none;
    outline: none;
    padding: 10px 20px;
    margin: 10px;
    font-size: 12px;
    background-color: #f7c231;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    width: 187px;
    height: 35px;
}

button:hover {
    background-color: #f7a129;
}

button:active {
    background-color: #FF8C00;
}

#commute-info {
    margin-top: 50px;
    font-size: 12px;
    text-align: center;
    border: 2px solid #f0f0f0;
    padding: 10px;
    max-width: 220px;
    margin: auto;
    border-radius: 10px;
    background-color: #f9f9f9;
    position: relative;
}

.time {
    margin: 14px 0;
    font-size: 10px;
    margin-left: -15px;
    margin-top: 36px;
}

.profile-pic {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    border: 2px solid #FFF;
    position: absolute;
    top: 75px;
    left: 50%;
    transform: translateX(-50%);
}

.button-container {
    position: relative;
    margin-top: 150px;
}

#sumTime {
    margin-top: 15px;
    margin-left: 18px;
    margin-top: 2px;
    position: fixed;
    color: gray;

}
</style>