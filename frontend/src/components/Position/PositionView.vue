l
<template>
  <div>
    <v-btn color="primary" @click="create()">Создать</v-btn>
    <v-data-table
      :headers="headers"
      :items="items"
      hide-actions
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td class="text-xs-left">{{ props.item.name }}</td>
        <td class="text-xs-left">{{ props.item.description }}</td>
        <td class="text-xs-center">
          <v-btn fab dark small color="primary" @click="remove(props.item)">
            <v-icon dark>remove</v-icon>
          </v-btn>
          <v-btn fab dark small color="cyan" @click="edit(props.item)">
            <v-icon dark>edit</v-icon>
          </v-btn>
        </td>
      </template>
      <template slot="no-data">
        <v-alert :value="true" color="success">
          Данных нет
        </v-alert>
      </template>
    </v-data-table>
    <v-dialog v-model="dialogDel" max-width="500px">
      <v-card>
        <v-card-title class="headline">Удаление должности</v-card-title>
        <v-card-text>Подтвердите удаление</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="removeSucces">Удалить</v-btn>
          <v-btn @click="removeCancel">Отмена</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="dialogEdit" max-width="500px">
      <v-card>
        <v-card-title class="headline">Изменение должности</v-card-title>
        <v-card-text>
          <v-text-field v-model="newName" label="Введите новое имя"/>
          <v-text-field v-model="newDescription" label="Введите новое описание"/>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="editSucess">Изменить</v-btn>
          <v-btn @click="editSucess">Отмена</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

  </div>
</template>

<script>

  import ajax from "../../client/index";

  export default {

    data() {
      return {
        dialogEdit: false,
        dialogDel: false,
        items: [],
        selectedItem: null,
        newName: "",
        newDescription: "",
        headers: [
          {text: 'Имя', value: 'name', sortable: false, align: "center"},
          {text: 'Описание', value: 'description', align: "center"},
          {text: 'Действия',align: 'center'},
        ]
      }
    },
    methods: {
      create() {
        this.$router.push("/createPosition")
      },
      get() {
        ajax.getPosition().then(response => {
          this.items = response.data
        })
          .catch(error => {
            this.items = error
          })
      },
      remove(id) {
        this.selectedItem = id;
        this.dialogDel = true;
      },
      removeSucces() {
        this.dialogDel = false;
        ajax.removePosition(this.selectedItem.id).then(response => {
          this.get();
        })
      },
      removeCancel(){
        this.dialogDel=false;
      },
      editSucess() {
        this.dialogEdit = false;
        ajax.editPosition(this.selectedItem.id, this.newName, this.newDescription).then(response => {
          this.get();
        })
      },
      editCancel(){
        this.dialogEdit = false;
      },
      edit(id) {
        this.selectedItem = id;
        this.newName = this.selectedItem.name;
        this.newDescription = this.selectedItem.description;
        this.dialogEdit = true;
      }
    },
    created() {
      this.get();
    }
  }
</script>

<style scoped>

</style>
