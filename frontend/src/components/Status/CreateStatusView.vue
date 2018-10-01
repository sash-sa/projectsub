<template>
  <div>
    <v-form style="text-align: center">
      <v-text-field
        v-model="name" v-validate="'required'" name="name" :error-messages="errors.collect('name')"
        label="Наименование"
      ></v-text-field>
      <v-text-field
        v-model="description"
        label="Описание"
        textarea
      ></v-text-field>
      <v-layout row>
        <v-flex xs8 offset-xs4>
          <v-switch v-model="visible" class="text-xs-center" label="Отображать на стартовом экране"/>
        </v-flex>
      </v-layout>
      <v-btn @click="create">Сохранить</v-btn>
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
        visible:true
      }
    },
    methods: {
      create() {
        this.$validator.validateAll().then((valid) => {
          if (valid) {
            var status = {
              name: this.name,
              description: this.description,
              visibleForIndex: this.visible
            }
            ajax.createStatus(status).then(response => {
              this.$router.push("/status")
            }).catch(error => {
              console.log(error)
            })
          }
        })
      }
    },
    created() {
      this.$validator.validateAll();
    }


  }
</script>

<style scoped>

</style>
