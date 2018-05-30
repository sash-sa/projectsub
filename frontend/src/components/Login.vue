<template>
  <v-app id="inspire" >
    <v-content>
      <v-toolbar
        color="blue darken-3"
        dark
        app
        fixed
        height="90px"
        :clipped-left="$vuetify.breakpoint.lgAndUp"
      >
        <v-layout row>
          <v-flex xs3>
            <router-link to="/">
              <img class="brand" src="../assets/img/logo.png">
            </router-link>
          </v-flex>
        </v-layout>
      </v-toolbar>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary" style="text-align: center">
                <v-toolbar-title>Вход в личный кабинет поставщика услуг</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-layout row>
                    <v-flex xs4>
                      <v-subheader>Логин</v-subheader>
                    </v-flex>
                    <v-flex xs8>
                      <v-text-field label="Логин" v-model="login" type="text"></v-text-field>
                    </v-flex>
                  </v-layout>
                  <v-layout row>
                    <v-flex xs4>
                      <v-subheader>Пароль</v-subheader>
                    </v-flex>
                    <v-flex xs8>
                      <v-text-field label="Пароль" v-model="password" type="password"></v-text-field>
                    </v-flex>
                  </v-layout>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-layout row>
                  <v-flex xs6 offset-xs-6>
                    <v-btn color="primary" @click="authenticated()">Войти</v-btn>
                  </v-flex>
                </v-layout>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
      <v-footer app :height="100">
        <v-container>
          <v-layout row>
            <v-flex xs6 offset-xs-6>
              <v-spacer></v-spacer>
              <p>Все права защищены 2006-{{oldYear}} год</p>
              <p style="font-size: 15px;font-weight: 400"><strong>ГУП ВЦКП “ЖИЛИЩНОЕ ХОЗЯЙСТВО”</strong></p>
            </v-flex>
          </v-layout>
        </v-container>
      </v-footer>
    </v-content>
  </v-app>
</template>

<script>
  import ajax from '../client/index';

  export default {
    data() {
      return {
        login: "",
        password: "",
        oldYear: new Date().getFullYear(),
      }
    }
    ,
    methods: {
      authenticated() {
        if (this.login != "" && this.password != "") {
          this.$store.dispatch("authenticated", {login: this.login, password: this.password})
          this.$router.push(`/`);
        }
      }
    }
  }
</script>

<style scoped>
  body{
    background-color: #0074D9;
  }
</style>
