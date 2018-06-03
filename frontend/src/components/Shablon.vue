<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      fixed
      app
    >
      <template class="text-xs-center" style="text-align: center;">
        <center>
          <v-avatar color="blue darken-2">
            <v-icon dark>account_circle</v-icon>
          </v-avatar>
          <br/>
          <v-chip>Login : <span style="font-weight: bold;font-size: 15px"> {{this.$store.state.authenticated.user.login}}</span></v-chip>
        </center>
        <br/>
        <hr size="5"/>
       <br/><br/>
      </template>
      <v-spacer></v-spacer>
      <template v-for="item in items">
        <v-list dense :key="item.text">
          <v-list-group
            v-if="item.children"
            v-model="item.model"
            :key="item.text"
            :prepend-icon="item.model ? item.icon : item['icon-alt']"
            append-icon=""
          >
            <v-list-tile slot="activator">
              <v-list-tile-content>
                {{ item.text }}
              </v-list-tile-content>
            </v-list-tile>
            <v-list-tile
              v-for="(child, i) in item.children"
              :key="i"
              @click="$router.push(child.path || '/')"
            >
              <v-list-tile-action>
                <v-icon color="primary">{{ child.icon }}</v-icon>
              </v-list-tile-action>
              <v-list-tile-content>
                {{ child.text }}
              </v-list-tile-content>
            </v-list-tile>
          </v-list-group>
          <v-list-tile v-else @click="$router.push(item.path || '/')">
            <v-list-tile-action>
              <v-icon color="primary">{{item.icon}}</v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              {{ item.text }}
            </v-list-tile-content>
          </v-list-tile>
        </v-list>
      </template>
    </v-navigation-drawer>
    <v-toolbar color="blue darken-2" dark fixed app>
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
    </v-toolbar>
    <v-content>
      <v-container fluid grid-list-md>
        <router-view></router-view>
      </v-container>
    </v-content>
    <v-footer color="blue darken-2" height="70" app>
      <span class="white--text">&copy; {{oldYear}}</span>
    </v-footer>
  </v-app>
</template>

<script>


  export default {
    data: () => ({
      oldYear: new Date().getFullYear(),
      drawer: null,
      items: [
        {text: 'Главная', path: '/', icon: "home"},
        {text: 'Заявки', path: '/issue', icon: "dialpad"},
        {text: 'Отчеты', path: '/report', icon: "report"},
        {text: 'Рассписание', path: '/schedule', icon: "call_split"},

        {
          text: 'Настройки',
          children: [
            {text: 'Общие', path: '/baseConfiguration', icon: "present_to_all"},
            {text: 'Работники', path: '/worker', icon: "present_to_all"},
            {text: 'Должности', path: '/position', icon: "present_to_all"},
            {text: 'Операторы', path: '/operator', icon: "present_to_all"},
            {text: 'Статусы', path: '/status', icon: "present_to_all"},
            {text: 'История', path: '/historyUpload', icon: "present_to_all"}
          ]
        }
      ]
    }),
    computed: {
      orgName: function () {
        // return this.$store.state.authenticated.user.orgName;
      },
      userName: function () {
        // return this.$store.state.authenticated.user.userName;
      }
    }
  }
</script>

<style>
  .brand {
    margin-left: 20px
  }

</style>
