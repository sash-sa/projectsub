<template>
  <div>
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
      <v-select
        v-model="selectWorker"
        :items="worker"
        label="Исполнители"
        item-value="id"
        item-text="fullName"
      ></v-select>
      <v-btn @click="save()">Сохранить</v-btn>
    </v-form>
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
        fio: ""
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
          worker: this.selectWorker != null ? {
            id: this.selectWorker
          } : null
        }
        ajax.createIssue(issue).then(response => {
          this.$router.push("/issue")
        }).catch(error => {
          console.log(error)
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
