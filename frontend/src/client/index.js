import axios from 'axios';
import store from '../store/index';

const baseURL = API_BASE_URL
const client = axios.create({
  baseURL
});

client.interceptors.response.use(function (response) {
  return response;
}, function (error) {
  if (error.response.status === 401) {
    console.log("+++++++++++++++auth+++++++++");
    store.commit("notAuthenticated");
  }
  return Promise.reject(error);
});

// client.interceptors.request.use(function (config, store) {
//   if(localStorage.getItem("authenticated")!=undefined)setToken(JSON.parse(localStorage.getItem("authenticated")).access_token)
//   return config;
// }, function (error) {
//   return Promise.reject(error);
// });

const setToken = (token) => client.defaults.headers.common["Authorization"] = "Bearer " + token;

const getCurrentUser=()=>client.get("/operator");

const getIssue=()=>client.get("/issue");

const getIssueForStatus=()=>client.get("/issue/status");

const getIssueUniq=(id)=>client.get(`/issue/${id}`);

const issueClosed=(id,decision)=>client.put(`/issue/${id}?decision=${decision}`)

const issueEditStatus=(id,status)=>client.put(`/issue/${id}/${status}`)

const issueEditWorker=(id,worker)=>client.put(`/issue/${id}/worker/${worker}`)

const getIssueForWorker=(worker)=>client.get(`/issue/worker/${worker}`)

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

const  getStatusnotStart=()=>client.get("/status/notStart");

const editStatus=(status)=>client.put("/status",status);

const removeStatus=(id)=>client.delete(`/status/${id}`)

const getOperator=()=>client.get("/operator/all")

const createOperator=(operator)=>client.post("/operator",operator)

const removeOperator=(id)=>client.delete(`/operator/${id}`)

const editOperator=(operator)=>client.put("/operator",operator);

const getRole=()=>client.get("/operator/role");

const  getConfiguration=()=>client.get("/configuration");

const editConfiguration=(configuration)=>client.put("/configuration",configuration);

const auth = (payload) => client.request({
  headers: {
    "Content-Type": "application/x-www-form-urlencoded"
  },
  auth: {
    username: "lcClient",
    password: "sjidghlksdjfghlksdjfghklsdjfghlskdjflskdf"
  },
  method: "POST",
  // url: `http://vckpmon.vckp.ru:8088/oauth/token?username=${payload.login}&password=${payload.password}&grant_type=password`
  url: `http://localhost:8088/oauth/token?username=${payload.login}&password=${payload.password}&grant_type=password`
  // url: `http://91.203.194.42:8088/oauth/token?username=${payload.login}&password=${payload.password}&grant_type=password`
})

export default {
  auth,
  setToken,
  getCurrentUser,
  getIssue,
  getIssueForStatus,
  getIssueUniq,
  issueClosed,
  issueEditStatus,
  issueEditWorker,
  getIssueForWorker,
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
  getStatusnotStart,
  editStatus,
  removeStatus,
  getOperator,
  createOperator,
  removeOperator,
  editOperator,
  getRole,
  getConfiguration,
  editConfiguration
};


