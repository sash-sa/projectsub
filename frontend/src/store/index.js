import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';
import ajax from '../client/index';

Vue.use(Vuex)


export default new Vuex.Store({
  state: {
    authenticated: {
      isAuthenticated: false,
      access_token: "",
      refresh_token: "",
      error: "",
      user: {
        login: "",
        user: null,
        userName: "",
        organization: null,
        orgName: ""
      }
    },
    lsItems: [],
    lsPageSize: 0,
    lsUniq: null,
    lsUniqParam: [],
    lsUniqAdres: [],
    lsUniqHouse: [],
    lsUniqTenants: [],
    lsUniqArea: [],
    questionItems: [],
    questionPageSize: 1,
    questionPage: 1,
    questionRowsPerPage: 7,
    profileUser: [],
    profileOrganisation: [],
    profileOrganisationUser: [],
    profileUserParam: [],
    allUserFile: [],
    contractItems: [],
    contractPageSize: 0
  },
  mutations: {
    authenticated: (state, payload) => {
      // var axiosConfig = {
      //   headers: {
      //     "Content-Type": "application/x-www-form-urlencoded"
      //   },
      //   auth: {
      //     username: "lcClient",
      //     password: "sjidghlksdjfghlksdjfghklsdjfghlskdjflskdf"
      //   },
      //   method: "POST",
      //   // url: `http://vckpmon.vckp.ru:8088/oauth/token?username=${payload.login}&password=${payload.password}&grant_type=password`
      //   url: `http://localhost:8088/oauth/token?username=${payload.login}&password=${payload.password}&grant_type=password`
      // };
      ajax.auth().then(response => {
        state.authenticated.isAuthenticated = true;
        state.authenticated.user.login = payload.login;
        state.authenticated.access_token = response.data.access_token;
        state.authenticated.refresh_token = response.data.refresh_token;
        ajax.setToken(state.authenticated.access_token);
        ajax.getCurrentUser().then(responseUser => {
          state.authenticated.user = responseUser.data;
          localStorage.setItem("authenticated", JSON.stringify(state.authenticated));
        })
      })
        .catch(error => {
          localStorage.removeItem("authenticated");
          state.authenticated.error = error;
          state.authenticated.isAuthenticated = false;
        })
    },
    isAuthenticated(state) {
      if (state.authenticated == undefined) {
        if (localStorage.getItem("authenticated") != null) {
          state.authenticated = JSON.parse(localStorage.getItem("authenticated"))
          ajax.setToken(state.authenticated.access_token);
        }
      }
    },
    logout(state) {
      localStorage.removeItem("authenticated");
      state.authenticated.isAuthenticated = false;
    },
    setLs(state, payload) {
      state.lsItems = payload.content;
      state.lsPageSize = payload.totalPages;
    },
    setLsUniq(state, payload) {
      state.lsUniq = payload;
      state.lsUniqParam = [];
      state.lsUniqParam.push({key: 'Код ЛС', value: state.lsUniq.knanim})
      state.lsUniqParam.push({key: 'Статус ЛС', value: state.lsUniq.status})
      state.lsUniqParam.push({key: 'Тип квартиры', value: state.lsUniq.pkNaim})
      state.lsUniqParam.push({key: 'Тип фонда', value: state.lsUniq.tipFond})

      state.lsUniqAdres = [];
      state.lsUniqAdres.push({key: 'Наименование улицы', value: state.lsUniq.house.street})
      state.lsUniqAdres.push({key: 'Номер дома', value: state.lsUniq.house.ndom})
      state.lsUniqAdres.push({key: 'Номер квартиры', value: state.lsUniq.nkv})

      state.lsUniqTenants = []
      state.lsUniqTenants.push({key: 'Кол-во зарегистрированных', value: state.lsUniq.n})
      state.lsUniqTenants.push({key: 'Кол-во собственников', value: state.lsUniq.nown})
      state.lsUniqTenants.push({key: 'Кол-во временно зарегистрированных', value: state.lsUniq.nvr})

      state.lsUniqArea = []
      state.lsUniqArea.push({key: 'Общая площадь ЛС', value: state.lsUniq.sqAll})
      state.lsUniqArea.push({key: 'Жилая площадь ЛС', value: state.lsUniq.sqGil})
      state.lsUniqArea.push({key: 'Площадь МОП', value: state.lsUniq.mop})

      state.lsUniqHouse = []
      state.lsUniqHouse = state.lsUniq.house
    },
    setQuestion(state, payload) {
      state.questionItems = payload.question.content;
      state.questionPageSize = payload.question.totalPages - 1;
    },
    setProfile(state, payload) {
      state.profileUser=[];
      state.profileOrganisation=[];
      state.profileOrganisationUser=[];
      state.profileUser.push({key: 'Логин', value: payload.singleUser.login})
      state.profileUser.push({key: 'ФИО', value: payload.singleUser.fullName})
      state.profileUser.push({key: 'Email', value: payload.singleUser.notificationEMail})
      state.profileUser.push({key: 'Телефон', value: payload.singleUser.phone})
      state.profileOrganisation.push({key: 'ИНН', value: payload.organization.inn});
      state.profileOrganisation.push({key: 'Имя организации', value: payload.organization.orgName});
      payload.organization.singleUser.forEach(user => {
          state.profileOrganisationUser.push({
            key: user.login,
            value: user.fullName
          })
        }
      )
      state.profileUserParam = payload.userParam;
    },
    setAllUserFiles(state, payload) {
      state.allUserFile = payload;
    },
    setContract(state, payload) {
      state.contractItems = payload.pageList;
      state.contractPageSize = payload.pageCount;
    },
    getAuthenticated(state) {
      return state.authenticated.isAuthenticated;
    }
  },
  actions: {
    authenticated(context, payload) {
      context.commit("authenticated", payload);
    },
    getLs(context, payload) {
      ajax.getLs(payload).then(response => {
        context.commit("setLs", response.data);
      }).catch(error => {
        console.log(error);
      })
    },
    getLsUniq(context, id) {
      ajax.getLsUniq(id).then(response => {
        context.commit("setLsUniq", response.data);
      }).catch(error => {
        console.log(error);
      })
    },
    getQuestion(context, payload) {
      ajax.getQuestion(payload)
        .then(response => {
          context.commit("setQuestion", response.data);
        }).catch(error => {
        console.log(error);
      })
    },
    addQuestion(context, payload) {
      return new Promise((resolve, reject) => {
        return ajax.addQuestion.then(response => {
          resolve(response);
        })
          .catch(error => {
            console.log(error);
          })
      })
    },
    getProfile(context) {
      return ajax.getProfile()
        .then(response => {
          context.commit("setProfile", response.data)
        })
        .catch(error => {
          console.log(error);
        })
    },
    changePassword(context, payload) {
      return new Promise((resolve, reject) => {
        ajax.changePassword.then(response => {
          resolve(response);
        })
          .catch(error => {
            reject(error);
          })
      })
    },
    changeNotification(context, payload) {
      return new Promise((resolve, reject) => {
        ajax.changeNotification.then(response => {
          resolve(response);
        })
          .catch(error => {
            reject(error);
          })
      })
    },
    getAllUserFiles(context) {
      ajax.get("/files/allfiles").then(response => {
        context.commit("setAllUserFiles", response.data)
      })
    },
    getContract(context) {
      ajax.getContract().then(response => {
        context.commit("setContract", response.data);
      }).catch(error => {
        console.log((error))
      })
    },


  }

})



