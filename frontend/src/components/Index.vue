<template>
  <v-container fluid>
    <v-layout row wrap>
      <template v-for="(x,index) in issue">
        <v-flex xs4>
          <v-card>
            <v-toolbar color="primary" dark>
              <v-toolbar-title>{{index}}</v-toolbar-title>
            </v-toolbar>
            <center>
              <v-chip v-if="x.length==0" color="primary" style="color: white;font-weight: bold">Запросов нет</v-chip>
            </center>
            <v-list two-line>
              <template v-for="item in x">
                <v-list-tile avatar @click="">
                  <v-chip>{{dateConvert(item.created)}}</v-chip>
                  <v-list-tile-content>
                    <v-list-tile-title v-html="item.theme"></v-list-tile-title>
                    <v-list-tile-sub-title v-html="item.description"></v-list-tile-sub-title>
                  </v-list-tile-content>
                </v-list-tile>
              </template>
            </v-list>
          </v-card>
        </v-flex>
      </template>
    </v-layout>
  </v-container>
</template>

<script>
  import ajax from "../client/index";

  export default {

    data() {
      return {
        issue: null
      }
    },
    methods: {
      getIssue() {
        ajax.getIssueForStatus().then(response => {
          this.issue = response.data;
        }).catch(error => {
          console.log(error)
        })
      },
      dateConvert(value) {
        if (value != null) {
          var date = new Date(Date.parse(value, "dd/MM/yyyy"));
          return date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes();
        }
        return "";
      }
    }
    ,
    created() {
      this.getIssue();
    }

  }
</script>

<style>

</style>
