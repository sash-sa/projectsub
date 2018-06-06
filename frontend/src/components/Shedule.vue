<template>
  <div>
    <v-layout row id="print">
      <v-flex xs4>
        <v-select
          :items="worker"
          v-model="workerSelected"
          label="Работник"
          item-value="id"
          item-text="fullName"
          autocomplete
          @change="changeWorker"
        ></v-select>
      </v-flex>
      <v-flex xs8>
        <v-date-picker locale="ru_RU"
                       v-model="date"
                       full-width
                       landscape
                       :events="workerEvents"
                       event-color="red lighten-1"

        ></v-date-picker>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex xs1>
        <v-btn color="primary" @click="create()" v-if="workerSelected!=null">Создать</v-btn>
      </v-flex>
      <v-flex xs10>
        <v-data-table
          :headers="issueHeaders"
          :items="issueItems"
          hide-actions
          class="elevation-1"
        >
          <template slot="items" slot-scope="props">
            <td class="text-xs-center" style="font-weight: bold">
              <v-btn depressed color="primary" @click="viewUniq(props.item.id)">{{ "ЗАПРОС-" + props.item.id }}</v-btn>
            </td>
            <td class="text-xs-left">{{ props.item.theme }}</td>
            <td class="text-xs-left">{{ props.item.description }}</td>
            <td class="text-xs-left">{{ props.item.issueStatus != null ? props.item.issueStatus.name : '' }}</td>
            <td class="text-xs-left">{{ dateConvert(props.item.timeExecution)}}</td>
            <td class="text-xs-left">{{ dateTimeConvert(props.item.created)}}</td>
            <td class="text-xs-left">{{ dateTimeConvert(props.item.update)}}</td>
          </template>
          <template slot="no-data">
            <v-alert :value="true" color="success">
              Запросов на исполнение нет
            </v-alert>
          </template>
        </v-data-table>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
  import ajax from "../client/index";

  export default {

    data() {
      return {
        issue: null,
        date: null,
        worker: [],
        workerSelected: null,
        workerEvents: null,
        issueItems: [],
        issueHeaders: [
          {text: 'Номер', value: 'id', align: "center"},
          {text: 'Тема', value: 'theme', align: "center"},
          {text: 'Описание', value: 'description', align: "center"},
          {text: 'Статус', value: 'issueStatus', align: "center"},
          {text: 'Срок исполнения', value: 'timeExecution', align: "center"},
          {text: 'Дата создания', value: 'created', align: "center"},
          {text: 'Дата обновления', value: 'update', align: "center"},
        ]
      }
    },
    methods: {
      viewUniq(id) {
        this.$router.push(`/issue/${id}`)
      },
      getWorker() {
        ajax.getWorker().then(response => {
          this.worker = response.data;
        })
          .catch(error => {
          })
      },
      getIssueForWorker() {

      },
      changeWorker(value) {
        ajax.getIssueForWorker(value).then(response => {
          console.log(response.data)
          this.workerEvents = [];
          response.data.forEach((x) => {
            var date = new Date(x.timeExecution);
            this.workerEvents.push(date.toISOString().substr(0, 10))
          });
          this.issueItems=response.data;
        }).catch(error => {
          console.log(error)
        })
      },
      dateConvert(value) {
        if (value != null) {
          var date = new Date(Date.parse(value, "dd/MM/yyyy"));
          return date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear();
        }
        return "";
      },
      dateTimeConvert(value) {
        if (value != null) {
          var date = new Date(Date.parse(value, "dd/MM/yyyy"));
          return date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes();
        }
        return "";
      },
      create() {
        this.$router.push("/createIssue")
      },
    }
    ,
    created() {
      this.getWorker();
    }


  }
</script>

<style>

</style>
