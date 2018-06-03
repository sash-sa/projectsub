<template>
  <div>
    <template v-if="eventVisible">
      <v-btn @click="editIssueStatus">Сменить состояние</v-btn>
      <v-btn @click="editWorker">Изменить ответственного</v-btn>
    </template>
    <v-btn @click="print">Распечатать</v-btn>

    <v-layout row id="print">
      <v-flex xs8>
        <v-layout row>
          <v-flex xs4>
            <v-subheader>Тема</v-subheader>
          </v-flex>
          <v-flex xs8>
            <v-text-field
              v-model="theme"
              readonly
            ></v-text-field>
          </v-flex>
        </v-layout>
        <v-layout row>
          <v-flex xs4>
            <v-subheader>Описание</v-subheader>
          </v-flex>
          <v-flex xs8>
            <v-text-field
              v-model="description"
              textarea
              readonly
            ></v-text-field>
          </v-flex>
        </v-layout>
        <v-layout row>
          <v-flex xs4>
            <v-subheader>Решение</v-subheader>
          </v-flex>
          <v-flex xs8>
            <v-text-field
              v-model="decision"
              textarea
              readonly
            ></v-text-field>
          </v-flex>
        </v-layout>
      </v-flex>
      <v-flex xs4>
        <v-text-field
          v-model="issueStatus"
          label="Статус"
          readonly
        ></v-text-field>
        <v-text-field
          v-model="fio"
          label="Имя жильца"
          readonly
        ></v-text-field>
        <v-text-field
          v-model="phone"
          label="Телефон жильца"
          readonly
        ></v-text-field>
        <v-text-field
          v-model="address"
          label="Адрес жильца"
          readonly
        ></v-text-field>
        <v-select
          v-model="selectWorker"
          :items="worker"
          label="Исполнитель"
          item-value="id"
          item-text="fullName"
          readonly
        ></v-select>
        <v-text-field
          v-model="timeExecution"
          label="Срок исполнения"
          readonly
        ></v-text-field>
      </v-flex>
    </v-layout>
    <v-dialog v-model="dialogEditStatus" max-width="500px">
      <v-card>
        <v-card-title class="headline">Смена состояния</v-card-title>
        <v-select @change="changeStatus"
                  v-model="selectNowStatus"
                  :items="status"
                  item-text="name"
                  item-value="id"
                  label="Новое состояние"
                  chips
                  persistent-hint
        ></v-select>
        <v-text-field v-model="decision" v-show="isDecision" multiLine label="Введите решение"/>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="editStatusSuccess">Сохранить</v-btn>
          <v-btn @click="editStatusCancel">Отмена</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="dialogEditWorker" max-width="500px">
      <v-card>
        <v-card-title class="headline">Смена исполнителя</v-card-title>
        <v-select
          v-model="selectWorkerEdit"
          :items="worker"
          item-text="fullName"
          item-value="id"
          label="Выберите нового исполнителя"
          chips
          persistent-hint
        ></v-select>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="editWorkerSuccess">Сохранить</v-btn>
          <v-btn @click="editWorkerCancel">Отмена</v-btn>
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
        theme: "",
        description: "",
        worker: [],
        selectWorker: null,
        address: "",
        phone: "",
        fio: "",
        selectItem: null,
        timeExecution: null,
        decision: null,
        issueStatus: null,
        configuration: null,
        eventVisible: false,
        dialogEditStatus: false,
        selectNowStatus: null,
        status: null,
        isDecision: false,
        dialogEditWorker:false,
        selectWorkerEdit:null
      }
    },
    methods: {
      getWorker() {
        ajax.getWorker().then(response => {
          this.worker = response.data
        })
          .catch(error => {
            this.items = error
          })
      },
      save() {
        var issue = {
          fio: this.fio,
          phone: this.phone,
          address: this.address,
          theme: this.theme,
          description: this.description,
          worker: this.selectWorker != null ? {
            id: this.selectWorker
          } : null
        }
        ajax.createIssue(issue).then(response => {
          this.$router.push("/")
        }).catch(error => {
          console.log(error)
        })
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
      },
      getConfiguration() {
        ajax.getConfiguration().then(response => {
          this.configuration = response.data;
          if (response.data.issueStatusClosed != null) {
            if (this.issueStatus != response.data.issueStatusClosed.name) this.eventVisible = true;
            else this.eventVisible = false;
          }
        }).catch(error => {
          console.log(error)
        })
      },
      getStatus() {
        ajax.getStatusnotStart().then(response => {
          this.status = response.data;
        }).catch(error => {
          console.log(error)
        })
      },
      editIssueStatus() {
        this.getStatus();
        this.dialogEditStatus = true;
      },
      editStatusSuccess() {
        if (this.configuration.issueStatusClosed != null) {
          if (this.selectNowStatus == this.configuration.issueStatusClosed.id) {
            ajax.issueClosed(this.$route.params.id, this.decision).then(response => {
              this.init();
              this.dialogEditStatus = false;
            }).catch(error => {
              console.log(error);
              this.init();
              this.dialogEditStatus = false;
            })
          }
          else {
            ajax.issueEditStatus(this.$route.params.id, this.selectNowStatus).then(response => {
              this.dialogEditStatus = false;
              this.init();
            }).catch(error => {
              console.log(error)
              this.init();
              this.dialogEditStatus = false;
            })
          }
        }
      },
      editStatusCancel() {
        this.dialogEditStatus = false;
      },
      changeStatus(value) {
        if (this.configuration.issueStatusClosed != null) {
          if (value == this.configuration.issueStatusClosed.id) this.isDecision = true;
          else this.isDecision = false;
        }
      },
      init() {
        ajax.getIssueUniq(this.$route.params.id).then(response => {
          this.selectItem = response.data;
          this.theme = this.selectItem.theme;
          this.description = this.selectItem.description;
          this.selectWorker = this.selectItem.worker;
          if(this.selectItem.worker!=null)this.selectWorkerEdit=this.selectItem.worker.id;
          this.address = this.selectItem.address;
          this.phone = this.selectItem.phone;
          this.fio = this.selectItem.fio;
          this.timeExecution = this.dateConvert(this.selectItem.timeExecution);
          this.decision = this.selectItem.decision;
          this.issueStatus = this.selectItem.issueStatus.name;
          this.getConfiguration();
        }).catch(error => {
          console.log(error)
        });

      },
      editWorker(){
        this.getWorker();
        this.dialogEditWorker=true;
      },
      editWorkerSuccess() {
        ajax.issueEditWorker(this.$route.params.id, this.selectWorkerEdit).then(response => {
          this.init();
          this.dialogEditWorker=false;
        }).catch(error => {
          console.log(error)
          this.init();
          this.dialogEditWorker=false;
        })
      },
      editWorkerCancel(){
        this.dialogEditWorker=false;
      },
      print(){
        var prtContent = document.getElementById("print");
        var WinPrint = window.open('','','left=50,top=50,width=800,height=640,toolbar=0,scrollbars=1,status=0');
        WinPrint.document.write('<div id="print" class="contentpane">');
        WinPrint.document.write(prtContent.innerHTML);
        WinPrint.document.write('</div>');
        WinPrint.document.close();
        WinPrint.focus();
        WinPrint.print();
        WinPrint.close();
        prtContent.innerHTML=strOldOne;
      }


    },
    created() {
      this.init();

    }


  }
</script>

<style scoped>

</style>
