<template>
  <div>
    <v-layout row>
      <v-flex xs8>
        <v-text-field
          v-model="theme"
          label="Тема"
          disabled
        ></v-text-field>

        <v-text-field
          v-model="description"
          label="Описание"
          textarea
          disabled
        ></v-text-field>
      </v-flex>
      <v-flex xs4>
        <v-text-field
          v-model="fio"
          label="Имя жильца"
          disabled
        ></v-text-field>
        <v-text-field
          v-model="phone"
          label="Телефон жильца"
          disabled
        ></v-text-field>
        <v-text-field
          v-model="address"
          label="Адрес жильца"
          disabled
        ></v-text-field>
        <v-select
          v-model="selectWorker"
          :items="worker"
          label="Исполнитель"
          item-value="id"
          item-text="fullName"
          disabled
        ></v-select>
      </v-flex>
    </v-layout>
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
        fio: "",
        selectItem: null
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
          this.$router.push("/")
        }).catch(error => {
          console.log(error)
        })
      }
    },
    created() {
      ajax.getIssueUniq(this.$route.params.id).then(response => {
        this.selectItem = response.data;
        this.theme = this.selectItem.theme;
        this.description = this.selectItem.description;
        this.selectWorker = this.selectItem.worker;
        this.address = this.selectItem.address;
        this.phone = this.selectItem.phone;
        this.fio = this.selectItem.fio;
      }).catch(error => {
        console.log(error)
      })

    }


  }
</script>

<style scoped>

</style>
