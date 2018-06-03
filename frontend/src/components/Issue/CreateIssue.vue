<template>
  <div>
    <v-container grid-list-md text-xs-center>
      <v-form class="text-xs-center">
        <v-text-field
          v-model="fio"
          label="Имя жильца"
        ></v-text-field>

        <v-text-field
          v-model="phone"
          label="Телефон жильца"
        ></v-text-field>

        <v-text-field
          v-model="address"
          label="Адрес жильца"
        ></v-text-field>

        <v-text-field
          v-model="theme"
          label="Тема"
        ></v-text-field>

        <v-text-field
          v-model="description"
          label="Описание"
          textarea
        ></v-text-field>
        <v-layout row wrap>
          <v-flex xs6>
            <v-select
              v-model="selectWorker"
              :items="worker"
              label="Исполнители"
              item-value="id"
              item-text="fullName"
            ></v-select>
          </v-flex>
          <v-flex xs6>
            <v-menu
              ref="menu2"
              :close-on-content-click="false"
              v-model="menu2"
              :nudge-right="40"
              :return-value.sync="date"
              lazy
              transition="scale-transition"
              offset-y
              full-width
              min-width="290px"
            >
              <v-text-field
                slot="activator"
                v-model="timeExecution"
                label="Срок исполнения"
                prepend-icon="event"
                readonly
              ></v-text-field>
              <v-date-picker v-model="timeExecution" @input="$refs.menu2.save(date)"></v-date-picker>
            </v-menu>
          </v-flex>
        </v-layout>

        <v-btn @click="save()">Сохранить</v-btn>
      </v-form>
    </v-container>
  </div>
</template>

<script>

  import ajax from "../../client/index";

  export default {

    data() {
      return {
        theme: "",
        description: "",
        worker: [],
        selectWorker: null,
        address: "",
        phone: "",
        fio: "",
        timeExecution:null,
        menu2:null,
        date:null
      }
    },
    methods: {
      getWorker() {
        ajax.getWorker().then(response => {
          this.worker = response.data
        })
          .catch(error => {
            this.items = error
          })
      },
      save() {
        var issue = {
          fio: this.fio,
          phone: this.phone,
          address: this.address,
          theme: this.theme,
          description: this.description,
          timeExecution:this.timeExecution!=null?new Date(this.timeExecution):null,
          worker: this.selectWorker != null ? {
            id: this.selectWorker
          } : null
        }
        ajax.createIssue(issue).then(response => {
          this.$router.push("/issue")
        }).catch(error => {
          console.log(error)
          this.$router.push("/issue")
        })
      }
    },
    created() {
      this.getWorker();
    }


  }
</script>

<style scoped>

</style>
