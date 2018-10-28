<template>
  <div>
    <input type="file" @change="inputFileSync" v-bind:id="inputFile" style="display: none"
           v-bind:multiple="multipleUpload"/>
    <v-subheader>Выбрано для загрузки {{fileListUpload.length}} файла</v-subheader>
    <template v-if="fileListUpload.length==0">
      <v-btn @click="changeInputFileSync" depressed small color="primary">Выбрать файлы
        <v-icon right dark>folder_open</v-icon>
      </v-btn>
    </template>
    <template v-if="fileListUpload.length>0">
      <v-container grid-list-md text-xs-center>
        <v-layout row>
          <v-flex xs4>
            <v-btn @click="changeInputFileSync" depressed small color="primary" style="width: 100%">Выбрать файлы
              <v-icon right dark>folder_open</v-icon>
            </v-btn>
          </v-flex>
          <v-flex xs4>
            <v-btn @click="uploadFiles" depressed small color="primary" style="width: 100%">Загрузить
              <v-icon right dark>cloud_upload</v-icon>
            </v-btn>
          </v-flex>
          <v-flex xs4>
            <v-btn @click="clearFiles" depressed small color="primary" style="width: 100%">Отмена
              <v-icon right dark>cancel</v-icon>
            </v-btn>
          </v-flex>
        </v-layout>
        <template v-for="(item, index) in fileListUpload">
          <v-layout row>
            <v-flex xs2>
              <v-icon color="primary" small>file_upload</v-icon>
            </v-flex>
            <template v-if="!item.process">
              <v-flex xs8>
                <div class="ListDiv">
                  {{item.file.name}}
                </div>
              </v-flex>
            </template>
            <template v-if="item.process">
              <v-flex xs4>
                <div class="ListDiv">
                  {{item.file.name}}
                </div>
              </v-flex>
            </template>
            <v-flex xs4 v-if="item.process">
              <v-progress-linear :indeterminate="item.process"></v-progress-linear>
            </v-flex>
            <v-flex xs2>
              <v-btn flat icon color="red lighten-2" light @click="deleteFiles(index)">
                <v-icon>delete</v-icon>
              </v-btn>
            </v-flex>
          </v-layout>
          <br/>
          <template v-for="error in item.messages.message" v-if="item.messages.typ==0">
            <v-layout row>
              <v-flex xs12>
                <v-alert :value="true" type="error">
                  {{error.code}}
                </v-alert>
              </v-flex>
            </v-layout>
          </template>
          <v-alert :value="item.messages.typ==1" type="success">
            Успешная отправка файла
          </v-alert>
        </template>
      </v-container>
    </template>
  </div>
</template>

<script>

  import ajax from "../../client/index";

  export default {
    props: {
      multipleUpload: {
        default: false
      },
      typeImport:{
        default:1
      }
    },
    data() {
      return {
        fileListUpload: [],
        inputFile: "inputFile"
      }
    },
    methods: {
      inputFileSync(value) {
        this.clearFiles();
        if (value.srcElement.files != null) {
          for (var i = 0; i < value.srcElement.files.length; i++) {
            this.fileListUpload.push({
              file: value.srcElement.files.item(i),
              process: false,
              messages: {message: [], typ: null}
            })
          }
        }
        console.log(this.fileListUpload);
      },
      deleteFiles(index) {
        console.log(this.fileListUpload.splice(index, 1));
      },
      clearFiles() {
        this.fileListUpload.splice(0, this.fileListUpload.length)
      },
      changeInputFileSync() {
        document.getElementById("inputFile").click();
      },
      uploadFiles() {
        this.fileListUpload.forEach((files) => {
            var form = new FormData();
            form.append("file", files.file);
            files.process = true;
            ajax.uploadFiles(form,this.typeImport).then(response => {
              files.messages.typ = 1;
              files.process = false;
            }).catch(error => {
              console.log(error.response)
              files.messages.message = error.response.data;
              files.messages.typ = 0;
              files.process = false;
            })
          }
        )
      }

    }
  }
</script>

<style scoped>
  .ListDiv {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    text-align: left;
    -webkit-box-flex: 1;
  }
</style>
