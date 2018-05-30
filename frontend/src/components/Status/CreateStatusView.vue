<template>
  <div>
    <v-form style="text-align: center">
      <v-text-field
        v-model="name"
        label="Наименование"
      ></v-text-field>
      <v-text-field
        v-model="description"
        label="Описание"
        textarea
      ></v-text-field>
      <v-btn @click="save">Сохранить</v-btn>
    </v-form>
  </div>
</template>

<script>

  import ajax from "../../client/index";

  export default {

    data() {
      return {
        name: "",
        description: "",
      }
    },
    methods: {
      save() {
        var status = {
          name:this.name,
          description:this.description
        }
        ajax.createStatus(status).then(response => {
            this.$router.push("/status")
        }).catch(error => {
          console.log(error)
        })
      }
    },


  }
</script>

<style scoped>

</style>
