<template>
  <div>
    <v-btn color="primary" @click="create()">Создать</v-btn>
    <v-alert :value="deleteError" type="error">
      Ошибка удаления. К оператору привязаны запросы
    </v-alert>
    <v-data-table
      :headers="headers"
      :items="operatorItem"
      hide-actions
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td class="text-xs-left">{{ props.item.login }}</td>
        <td class="text-xs-left">{{ props.item.fullName }}</td>
        <td class="text-xs-left">
          <template v-for="x in props.item.role">
            {{x.name}}
          </template>
        </td>
        <td class="text-xs-center">
          <v-btn fab dark small color="primary" @click="remove(props.item)">
            <v-icon dark>remove</v-icon>
          </v-btn>
          <v-btn fab dark small color="primary" @click="edit(props.item)">
            <v-icon dark>edit</v-icon>
          </v-btn>
        </td>
      </template>
    </v-data-table>
    <v-dialog v-model="dialogDel" max-width="500px">
      <v-card>
        <v-card-title class="headline">Удаление Оператора</v-card-title>
        <v-card-text>Подтвердите удаление</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="removeSucces">Удалить</v-btn>
          <v-btn @click="removeCancel">Отмена</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="dialogEdit">
      <v-card>
        <v-form style="text-align: center">
          <v-text-field
            v-model="login"
            label="Логин"
          ></v-text-field>

          <v-text-field
            v-model="fullName"
            label="Полное имя"
          ></v-text-field>

          <v-select
            v-model="selectRole"
            :items="role"
            multiple
            item-text="name"
            item-value="id"
            label="Роль"
          ></v-select>
          <v-btn @click="editSucess">Сохранить</v-btn>
          <v-btn @click="editCancel">Отмена</v-btn>
        </v-form>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
  import ajax from "../../client/index";
  import {allObject} from "../Mixins/AllObject";

  export default {
    mixins: [allObject],
    data() {
      return {
        headers: [
          {text: 'Логин', value: 'login', align: "center"},
          {text: 'Полное имя', value: 'full_name', align: "center"},
          {text: 'Роль', value: 'role', align: "center"},
          {text: 'Действия'},
        ],
        selectItem: null,
        dialogDel: false,
        dialogEdit: false,
        deleteError: false,
        login: "",
        fullName: "",
        selectRole: null,
        role: null
      }
    },
    methods: {
      getRole() {
        ajax.getRole().then(response => {
          this.role = response.data;
        }).catch(error => {
          console.log(error)
        })
      },
      create() {
        this.$router.push("/createOperator")
      },
      remove(item) {
        this.selectedItem = item;
        this.dialogDel = true;
      },
      removeSucces() {
        ajax.removeOperator(this.selectedItem.uid).then(response => {
          this.getOperator();
          this.dialogDel = false;
        }).catch(error => {
          console.log(error)
          if (error.request.status == 409) this.deleteError = true;
          this.dialogDel = false;
        })
      },
      removeCancel() {
        this.dialogDel = false;
      },
      edit(item) {
        this.selectedItem = item;
        this.getRole();
        this.login = this.selectedItem.login;
        this.fullName = this.selectedItem.fullName;
        this.selectRole = this.selectedItem.role;
        this.dialogEdit = true;
      },
      editSucess() {
        var user = {
          uid: this.selectedItem.uid,
          login: this.login,
          fullName: this.fullName,
          role: (this.selectRole != null ? this.selectRole.map(function (value) {
            return {id: value.id}
          }) : null)
        }
        ajax.editOperator(user).then(response => {
          this.dialogEdit = false;
          this.getOperator();
        }).cath(error => {
          console.log(error)
        })
      },
      editCancel() {
        this.dialogEdit = false;
      }
    },
    created() {
      this.getOperator();
    }

  }
</script>

<style>

</style>
