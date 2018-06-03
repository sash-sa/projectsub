<template>
  <div>
    <v-btn @click="edit">Изменить</v-btn>
    <v-layout row>
      <v-flex xs4>
        <v-subheader>Этап создания запроса</v-subheader>
      </v-flex>
      <v-flex xs8>
        <v-text-field
          v-model="statusStart"
          readonly
        ></v-text-field>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex xs4>
        <v-subheader>Этап завершения запроса</v-subheader>
      </v-flex>
      <v-flex xs8>
        <v-text-field
          v-model="statusStop"
          readonly
        ></v-text-field>
      </v-flex>
    </v-layout>
    <v-dialog v-model="dialogEdit">
      <v-card style="text-align: center">
        <v-select
          v-model="statusSelectedStart"
          :items="status"
          item-text="name"
          item-value="id"
          label="Этап создания запроса"
        ></v-select>
        <v-select
          v-model="statusSelectedStop"
          :items="status"
          item-text="name"
          item-value="id"
          label="Этап завершения запроса"
        ></v-select>
        <v-btn @click="editSucess">Сохранить</v-btn>
        <v-btn @click="editCancel">Отмена</v-btn>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
  import ajax from '../client/index';

  export default {
    data() {
      return {
        statusStart: null,
        statusStop: null,
        status: null,
        statusSelectedStart: null,
        statusSelectedStop: null,
        configurationItem: null
      }
    }
    ,
    methods: {
      getConfiguration() {
        ajax.getConfiguration().then(response => {
          this.configurationItem = response.data;
          if(response.data.issueStatusStart != null){
            this.statusStart =  response.data.issueStatusStart.name;
            this.statusSelectedStart =  response.data.issueStatusStart.id;
          }

          if(response.data.issueStatusClosed != null){
            this.statusStop =  response.data.issueStatusClosed.name;
            this.statusSelectedStop =  response.data.issueStatusClosed.id;
          }

        }).catch(error => {
          console.log(error)
        })
      },
      edit() {
        this.getStatus();
        this.dialogEdit = true;
      },
      editSucess() {
        var configur = {
          id: this.configurationItem.id,
          issueStatusStart: {
            id: this.statusSelectedStart
          },
          issueStatusClosed: {
            id: this.statusSelectedStop
          }
        }
        ajax.editConfiguration(configur).then(response => {
          this.getConfiguration();
          this.dialogEdit = false;
        }).cath(error => {
          console.log(error)
        })

      },
      editCancel() {
        this.dialogEdit = false;
      },
      getStatus() {
        ajax.getStatus().then(response => {
          this.status = response.data;
        }).catch(error => {
          console.log(error)
        })
      }
    },
    created() {
      this.getConfiguration();
    }
  }
</script>

<style scoped>
</style>
