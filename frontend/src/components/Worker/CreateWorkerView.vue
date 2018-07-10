<template>
  <div>
    <v-form style="text-align: center">
      <v-text-field
        v-model="fio"
        label="ФИО" v-validate="'required'" name="fio" :error-messages="errors.collect('fio')"
      ></v-text-field>
      <v-text-field
        v-model="phone"
        label="Телефон"
        textarea
      ></v-text-field>
      <v-select
        v-model="positionsSelected"
        :items="positionItem"
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
  import {allObject} from "../Mixins/AllObject";


  export default {
    mixins: [allObject],
    data() {
      return {
        fio: "",
        phone: "",
        positionsSelected: []
      }
    },
    methods: {
      save() {
        this.$validator.validateAll().then((valid) => {
          if(valid){
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
        })
      }
    },
    created() {
      this.getPosition();
      this.$validator.validateAll();
    }
  }
</script>

<style scoped>

</style>
