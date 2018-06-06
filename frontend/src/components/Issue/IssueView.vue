l
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
          <td class="text-xs-center" style="font-weight: bold">
            <v-btn depressed color="primary" @click="viewUniq(props.item.id)">{{ "ЗАПРОС-" + props.item.id }}</v-btn>
          </td>
          <td class="text-xs-left">{{ props.item.theme }}</td>
          <td class="text-xs-left">{{ props.item.description }}</td>
          <td class="text-xs-left">{{ props.item.issueStatus != null ? props.item.issueStatus.name : '' }}</td>
          <td class="text-xs-left">{{ props.item.worker != null ? props.item.worker.fullName : ''}}</td>
          <td class="text-xs-left">{{ dateConvert(props.item.timeExecution)}}</td>
          <td class="text-xs-left">{{ props.item.user != null ? props.item.user.login : ''}}</td>
          <td class="text-xs-left">{{ dateTimeConvert(props.item.created)}}</td>
          <td class="text-xs-left">{{ dateTimeConvert(props.item.update)}}</td>
          <td class="text-xs-center">
            <v-btn fab dark small color="primary" @click="remove(props.item)">
              <v-icon dark>remove</v-icon>
            </v-btn>
            <v-btn fab dark small color="primary" @click="edit(props.item)">
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
          <v-card-title class="headline">Удаление запроса</v-card-title>
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
              label="ФИО жильца"
            ></v-text-field>

            <v-text-field
              v-model="phone"
              label="Телефон жильца"
            ></v-text-field>

            <v-text-field
              v-model="address"
              label="Адрес жильца"
            ></v-text-field>

            <v-text-field
              v-model="theme"
              label="Тема"
            ></v-text-field>
            <v-text-field
              v-model="description"
              label="Описание"
              textarea
            ></v-text-field>
            <v-select
              v-model="statusSelected"
              :items="status"
              item-text="name"
              item-value="id"
              label="Статус"
            ></v-select>
            <v-select
              v-model="workerSelected"
              :items="worker"
              item-text="fullName"
              item-value="id"
              label="Исполнитель"
            ></v-select>
            <v-menu
              ref="menu2"
              :close-on-content-click="false"
              v-model="menu2"
              :nudge-right="40"
              :return-value.sync="date"
              lazy
              transition="scale-transition"
              offset-y
              full-width
              min-width="290px"
            >
              <v-text-field
                slot="activator"
                v-model="timeExecution"
                label="Срок исполнения"
                prepend-icon="event"
                readonly
              ></v-text-field>
              <v-date-picker v-model="timeExecution" @input="$refs.menu2.save(date)"></v-date-picker>
            </v-menu>
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
          {text: 'Номер', value: 'id', align: "center"},
          {text: 'Тема', value: 'theme', align: "center"},
          {text: 'Описание', value: 'description', align: "center"},
          {text: 'Статус', value: 'issueStatus', align: "center"},
          {text: 'Исполнитель', value: 'worker', align: "center"},
          {text: 'Срок исполнения', value: 'timeExecution', align: "center"},
          {text: 'Оператор', value: 'user', align: "center"},
          {text: 'Дата создания', value: 'created', align: "center"},
          {text: 'Дата обновления', value: 'update', align: "center"},
          {text: 'Действия'},
        ],
        selectItem: null,
        dialogDel: false,
        dialogEdit: false,
        fio: "",
        phone: "",
        address: "",
        theme: "",
        description: "",
        issueStatus: "",
        status: null,
        statusSelected: null,
        worker: null,
        workerSelected: null,
        timeExecution:null,
        date:null,
        menu2:null
      }
    },
    methods: {
      viewUniq(id) {
        this.$router.push(`/issue/${id}`)
      },
      getIssue() {
        ajax.getIssue().then(response => {
          this.items = response.data
        })
          .catch(error => {
            this.items = error
          })
      },
      create() {
        this.$router.push("/createIssue")
      },
      remove(id) {
        this.selectedItem = id;
        this.dialogDel = true;
      },
      removeSucces() {
        ajax.removeIssue(this.selectedItem.id).then(response => {
          this.getIssue();
          this.dialogDel = false;
        }).catch(error => {
          console.log(error);
        })
      },
      removeCancel() {
        this.dialogDel = false;
      },
      edit(id) {
        this.selectedItem = id;
        this.fio = this.selectedItem.fio;
        this.phone = this.selectedItem.phone;
        this.address = this.selectedItem.address;
        this.theme = this.selectedItem.theme;
        this.description = this.selectedItem.description;
        ajax.getStatus().then(response => {
          this.status = response.data;
        }).catch(error => {
          console.log(error)
        })
        ajax.getWorker().then(response => {
          this.worker = response.data;
        })
        if (this.selectedItem.issueStatus != null) this.statusSelected = this.selectedItem.issueStatus.id;
        if (this.selectedItem.worker != null) this.workerSelected = this.selectedItem.worker.id;
        this.timeExecution=this.selectedItem.timeExecution;
        this.dialogEdit = true;
      },
      editSucess() {
        var issue = {
          id: this.selectedItem.id,
          fio: this.fio,
          phone: this.phone,
          address: this.address,
          theme: this.theme,
          created: this.selectedItem.created,
          description: this.description,
          issueStatus: this.statusSelected != null ? {id: this.statusSelected} : null
          ,
          worker: this.workerSelected != null ? {id: this.workerSelected} : null,
          timeExecution:this.timeExecution!=null?new Date(this.timeExecution):null

        }

        console.log(this.selectedItem)
        ajax.editIssue(issue).then(response => {
          this.getIssue();
          this.dialogEdit = false;

        })
      },
      editCancel() {
        this.dialogEdit = false;
      },
      dateTimeConvert(value) {
        if (value != null) {
          var date = new Date(Date.parse(value, "dd/MM/yyyy"));
          return date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes();
        }
        return "";
      },
      dateConvert(value) {
        if (value != null) {
          var date = new Date(Date.parse(value, "dd/MM/yyyy"));
          return date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear();
        }
        return "";
      }
    },
    created() {
      this.getIssue();
    }
    ,

  }
</script>

<style scoped>

</style>
