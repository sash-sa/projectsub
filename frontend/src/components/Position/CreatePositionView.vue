<template>
  <div>
    <v-form style="text-align: center">
      <v-text-field
        v-model="name"
        label="Имя"
        v-validate="'required'" name="name" :error-messages="errors.collect('name')"
      ></v-text-field>

      <v-text-field
        v-model="description"
        label="Описание"
        textarea
      ></v-text-field>
      <v-btn color="primary" @click="create()">Создать</v-btn>
    </v-form>
  </div>
</template>

<script>

  import ajax from "../../client/index";

  export default {
    data() {
      return {
        theme:"",
        description:""
      }
    },
    methods: {
      create(){
        this.$validator.validateAll().then((valid)=>{
          if(valid){
            ajax.createPosition(this.name,this.description).then(response=>{
              this.$router.push("/position")
            }).catch(error=>{
              console.log(error)
            })
          }
        })
      }
    },
    created(){
      this.$validator.validateAll();
    }


  }
</script>

<style scoped>

</style>
