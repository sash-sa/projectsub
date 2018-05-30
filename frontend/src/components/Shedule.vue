<template>
  <div>
    <v-container fluid>
      <v-layout row wrap>
        <v-flex xs-3>
          <v-select
            :items="worker"
            v-model="workerSelected"
            label="Работник"
            item-value="id"
            item-text="fullName"
            autocomplete
          ></v-select>
        </v-flex>
        <v-flex xs-8>
          <v-date-picker
            v-model="date"
            full-width
            landscape
            class="mt-3"
          ></v-date-picker>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
  import ajax from "../client/index";

  export default {

    data() {
      return {
        issue: null,
        date: null,
        worker:[],
        workerSelected:null
      }
    },
    methods: {
      getWorker() {
        ajax.getWorker().then(response => {
          this.worker = response.data
        })
          .catch(error => {
          })
      }
    }
    ,
    created() {
      this.getWorker();
    }

  }
</script>

<style>

</style>
