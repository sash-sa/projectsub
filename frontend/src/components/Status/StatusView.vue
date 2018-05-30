<template>
  <div>
    <template>
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
          <v-card-title class="headline">Удаление сотрудника</v-card-title>
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
              v-model="name"
              label="Наименование"
            ></v-text-field>
            <v-text-field
              v-model="description"
              label="Описание"
              textarea
            ></v-text-field>
            <v-btn @click="editSucess">Сохранить</v-btn>
            <v-btn @click="editCancel">Отмена</v-btn>
          </v-form>
        </v-card>

      </v-dialog>
    </template>
  </div>
</template>

<script>

  import ajax from "../../client/index";

  export default {

    data() {
      return {
        items: [],
        headers: [
          {text: 'Наименование', value: 'name', sortable: false, align: "center"},
          {text: 'Описание', value: 'description', align: "center"},
          {text: 'Действия', align: "center"}
        ],
        dialogEdit: false,
        dialogDel: false,
        selectedItem: null,
        name: "",
        description: ""
      }
    },
    methods: {
      create() {
        this.$router.push("/createStatus");
      },
      remove(id) {
        this.selectedItem = id;
        this.dialogDel = true;
      },
      edit(id) {
        this.selectedItem = id;
        this.name = this.selectedItem.name;
        this.description = this.selectedItem.description;
        this.dialogEdit = true;
      },
      editSucess() {
        ajax.editStatus({id: this.selectedItem.id, name: this.name, description: this.description})
          .then(response => {
            this.getStatus();
            this.dialogEdit = false;
          }).catch(error => {
          console.log(error)
        })
      },
      editCancel() {
        this.dialogEdit = false;
      },
      removeSucces() {
        ajax.removeStatus(this.selectedItem.id).then(response => {
          this.getStatus();
          this.dialogDel = false;
        }).catch(error => {
          console.log(error)
        })
      },
      removeCancel(){
        this.dialogDel=false;
      },
      getStatus() {
        ajax.getStatus().then(response => {
          this.items = response.data;
        }).catch(error => {
          console.log(error)
        })
      }
    },
    created() {
      this.getStatus();
    }
  }
</script>

<style scoped>

</style>
