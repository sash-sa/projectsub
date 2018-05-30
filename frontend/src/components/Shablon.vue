<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      fixed
      app
    >
      <template class="text-xs-center" style="text-align: center;">
        <center>
          <v-avatar color="indigo">
            <v-icon dark>account_circle</v-icon>
          </v-avatar>
        </center>
        <br/><br/><br/>
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
    <v-toolbar color="indigo" dark fixed app>
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
    </v-toolbar>
    <v-content>
      <v-container fluid grid-list-md>
        <router-view></router-view>
      </v-container>
    </v-content>
    <v-footer color="indigo" app>
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
        {text: 'Главная', path: '/index', icon: "home"},
        {text: 'Заявки', path: '/issue', icon: "home"},
        {text: 'Отчеты', path: '/report', icon: "home"},
        {text: 'Рассписание', path: '/schedule', icon: "home"},

        {
          text: 'Настройки', path: '/position', icon: "home",
          children: [
            {text: 'Работники', path: '/worker', icon: "home"},
            {text: 'Должности', path: '/position', icon: "home"},
            {text: 'Операторы', path: '/position', icon: "home"},
            {text: 'Статусы', path: '/status', icon: "home"},
            {text: 'История', path: '/historyUpload'}
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

  #nprogress .bar {
    height: 3px;
    background: red;
  }

  #nprogress .spinner-icon {
    border-top-color: red;
    border-left-color: red;
  }

  .textVckp {
    font-size: 15px;
    text-transform: uppercase;
    color: #060606;
    white-space: nowrap;
  }
</style>
