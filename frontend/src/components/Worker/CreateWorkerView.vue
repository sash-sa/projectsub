<template>
  <div>
    <v-form style="text-align: center">
      <v-text-field
        v-model="fio"
        label="ФИО"
      ></v-text-field>
      <v-text-field
        v-model="phone"
        label="Телефон"
        textarea
      ></v-text-field>
      <v-select
        v-model="positionsSelected"
        :items="positions"
        item-text="name"
        item-value="id"
        label="Должность"
        multiple
        chips
        persistent-hint
      ></v-select>
      <v-btn @click="save">Сохранить</v-btn>
    </v-form>
  </div>
</template>

<script>

  import ajax from "../../client/index";

  export default {

    data() {
      return {
        fio: "",
        phone: "",
        positions: [],
        positionsSelected: []
      }
    },
    methods: {
      getPositions() {
        ajax.getPosition().then(response => {
          this.positions = response.data
        })
          .catch(error => {
            console.log(error)
          })
      }
      ,
      save() {
        var worker = {
          fullName: this.fio,
          phone: this.phone,
          position: this.positionsSelected.map(function (value, index, array) {
            return {id: value}
          })
        }
        ajax.createWorker(worker).then(response => {
            this.$router.push("/worker")
        }).catch(error => {
          console.log(error)
        })
      }
    },
    created() {
      this.getPositions()
    }


  }
</script>

<style scoped>

</style>
