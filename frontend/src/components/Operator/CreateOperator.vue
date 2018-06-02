<template>
  <div>
    <v-form class="text-xs-center">
      <v-text-field
        v-model="login"
        label="Логин"
      ></v-text-field>

      <v-text-field
        v-model="full_name"
        label="Имя пользователя"
      ></v-text-field>

      <v-text-field
        v-model="password"
        label="Пароль" type="password"
      ></v-text-field>

      <v-select
        v-model="selectRole"
        :items="role"
        label="Роль"
        multiple
        item-value="id"
        item-text="name"
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
        login: "",
        full_name: "",
        password: "",
        selectRole: [],
        role: null
      }
    },
    methods: {
      getRole() {
        ajax.getRole().then(response => {
          this.role = response.data;
          if (this.role != null && this.role.length > 0) this.selectRole.push(this.role[0].id);
        }).catch(error => {
          console.log(error)
        })
      },
      save() {
        var user = {
          login: this.login,
          password: this.password,
          fullName: this.full_name,
          role: (this.selectRole != null ? this.selectRole.map(function (value) {
            return {id: value}
          }) : null)
        }
        ajax.createOperator(user).then(response=>{
          this.$router.push("/operator")
        })
      }
    },
    created() {
      this.getRole();
    }


  }
</script>

<style scoped>

</style>
