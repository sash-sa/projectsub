import axios from 'axios';

const baseURL = API_BASE_URL;

const client = axios.create({
  baseURL
});

// client.interceptors.response.use(function (response) {
//   return response;
// }, function (error) {
//   if (error.response.status === 401) {
//       console.log("+++++++++++++++auth+++++++++");
//     // store.commit()
//   }
//   return Promise.reject(error);
// });

// client.interceptors.request.use(function (config, store) {
//   if(localStorage.getItem("authenticated")!=undefined)setToken(JSON.parse(localStorage.getItem("authenticated")).access_token)
//   return config;
// }, function (error) {
//   return Promise.reject(error);
// });

// const setToken = (token) => client.defaults.headers.common["Authorization"] = "Bearer " + token;

//
// const auth = (axiosConfig) => client.request({
//   headers: {
//     "Content-Type": "application/x-www-form-urlencoded"
//   },
//   auth: {
//     username: "lcClient",
//     password: "sjidghlksdjfghlksdjfghklsdjfghlskdjflskdf"
//   },
//   method: "POST",
//   // url: `http://vckpmon.vckp.ru:8088/oauth/token?username=${payload.login}&password=${payload.password}&grant_type=password`
//   url: `http://localhost:8088/oauth/token?username=7707049388-1&password=123456&grant_type=password`
// })

const getIssue=()=>client.get("/issue");

const getIssueForStatus=()=>client.get("/issue/status");

const getIssueUniq=(id)=>client.get(`/issue/${id}`);

const removeIssue=(id)=>client.delete(`/issue/${id}`);

const editIssue=(issue)=>client.put("/issue",issue);

const createIssue=(issue)=>client.post("/issue",issue);

const getWorker=()=>client.get("/worker");

const removeWorker=(id)=>client.delete(`/worker/${id}`);

const createWorker=(worker)=>client.post("/worker",worker);

const getPosition=()=>client.get("/position");

const removePosition=(id)=>client.delete(`/position/${id}`);

const editPosition=(id,name,description)=>client.put(`/position/${id}?name=${name}&description=${description}`);

const createPosition=(name,description)=>client.post(`/position?name=${name}&description=${description}`);

const createStatus=(status)=>client.post("/status",status);

const  getStatus=()=>client.get("/status");

const editStatus=(status)=>client.put("/status",status);

const removeStatus=(id)=>client.delete(`/status/${id}`)

export default {
  // auth,
  getIssue,
  getIssueForStatus,
  getIssueUniq,
  removeIssue,
  editIssue,
  createIssue,
  getWorker,
  removeWorker,
  createWorker,
  getPosition,
  createPosition,
  removePosition,
  editPosition,
  createStatus,
  getStatus,
  editStatus,
  removeStatus
};


