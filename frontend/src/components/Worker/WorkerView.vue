l
<template>
  <div>
    <template>
      <v-btn color="primary" @click="create()">Создать</v-btn>
      <v-data-table
        :headers="headers"
        :items="workerItem"
        hide-actions
        class="elevation-1"
      >
        <template slot="items" slot-scope="props">
          <td class="text-xs-left">{{ props.item.fullName }}</td>
          <td class="text-xs-left">{{ props.item.phone }}</td>
          <td class="text-xs-left">
            <label v-for="x in props.item.position">{{x.name}}<br/></label>
          </td>
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
              v-model="fio"
              label="ФИО"
            ></v-text-field>
            <v-text-field
              v-model="phone"
              label="Телефон"
              textarea
            ></v-text-field>
            <v-select fr
              v-model="positionsSelected"
              :items="positionItem"
              item-text="name"
              item-value="id"
              label="Должность"
              multiple
              chips
            ></v-select>
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
  import {allObject} from "../Mixins/AllObject";

  export default {
    mixins: [allObject],
    data() {
      return {
        headers: [
          {text: 'ФИО', value: 'theme', sortable: false, align: "center"},
          {text: 'Телефон', value: 'description', align: "center"},
          {text: 'Должность', value: 'issueStatus', align: "center"},
          {text: 'Действия', align: "center"}
        ],
        dialogEdit: false,
        dialogDel: false,
        selectedItem: null,
        fio: "",
        phone: "",
        positionsSelected: "",
        positions: null
      }
    },
    methods: {
      getPositions() {
        this.getPosition().
        ajax.getPosition().then(response => {
          this.positions = response.data
          this.positionsSelected = this.selectedItem.position.map(x => {
            return x.id;
          })
        })
          .catch(error => {
            console.log(error)
          })
      },
      create() {
        this.$router.push("/createWorker")
      },
      removeSucces() {
        ajax.removeWorker(this.selectedItem.id).then(response => {
          this.getWorker()
          this.dialogDel = false;
        }).catch(error => {
          console.log(error)
        })
      },
      removeCancel() {
        this.dialogDel = false;
      },
      editSucess() {
        var worker = {
          id: this.selectedItem.id,
          fullName: this.fio,
          phone: this.phone,
          position: this.positionsSelected.map(function (value, index, array) {
            return {id: value}
          })
        }
        ajax.createWorker(worker).then(response => {
          this.getWorker();
          this.dialogEdit = false;
        }).catch(error => {
          console.log(error)
        })
      },
      editCancel() {
        this.dialogEdit = false;
      },
      remove(id) {
        this.selectedItem = id;
        this.dialogDel = true;
      },
      edit(id) {
        this.selectedItem = id;
        this.dialogEdit = true;
        this.fio = this.selectedItem.fullName;
        this.phone = this.selectedItem.phone;
        this.getPositions();
      }
    },
    created() {
      this.getWorker();
    }
  }
</script>

<style scoped>

</style>
