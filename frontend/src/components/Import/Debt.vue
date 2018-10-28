<template>
  <div>
    <v-layout row>
      <v-flex xs12>
        <Upload/>
      </v-flex>
    </v-layout>
    <v-layout row align-center>
      <v-flex xs4>
        Параметры импорта
      </v-flex>
      <v-flex xs8>
        <v-layout row align-center>
          <v-flex xs6>
            <strong>Начальная строка</strong>
          </v-flex>
          <v-flex xs6>
            <v-text-field type="text" v-model="rowStart"/>
          </v-flex>
        </v-layout>
        <v-layout align-center>
          <v-flex xs6 style="text-align: center">
            <strong>Название параметра</strong>
          </v-flex>
          <v-flex xs6 style="text-align: center">
            <strong>Название столбца</strong>
          </v-flex>
        </v-layout>
        <v-layout align-center>
          <v-flex xs6>
            Улица,дом
          </v-flex>
          <v-flex xs6>
            <v-text-field type="text" v-model="street"/>
          </v-flex>
        </v-layout>
        <v-layout align-center>
          <v-flex xs6>
            №, кв
          </v-flex>
          <v-flex xs6>
            <v-text-field type="text" v-model="nomerKv"/>
          </v-flex>
        </v-layout>
        <v-layout align-center>
          <v-flex xs6>
            Фамилия
          </v-flex>
          <v-flex xs6>
            <v-text-field type="text" v-model="family"/>
          </v-flex>
        </v-layout>
        <v-layout align-center>
          <v-flex xs6>
            Лицевой счет
          </v-flex>
          <v-flex xs6>
            <v-text-field type="text" v-model="ls"/>
          </v-flex>
        </v-layout>
        <v-layout align-center>
          <v-flex xs6>
            Общая сумма задолжности
          </v-flex>
          <v-flex xs6>
            <v-text-field type="text" v-model="summa"/>
          </v-flex>
        </v-layout>
        <v-layout row align-center>
          <v-flex xs3 offset-xs5>
            <v-btn primary @click="save">
              Сохранить
            </v-btn>
          </v-flex>
        </v-layout>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
  import ajax from '../../client/index';
  import Upload from "../../components/public/Upload";

  export default {
    components: {Upload},
    data() {
      return {
        rowStart: null,
        street: null,
        nomerKv: null,
        family: null,
        ls: null,
        summa: null
      }
    },
    methods: {
      save() {
        ajax.saveImportDebtParam({
          "rowNum": this.rowStart,
          "streetName": this.street,
          "nomerKvName": this.nomerKv,
          "familiName": this.family,
          "lsName": this.ls,
          "summaName": this.summa
        })
      }
    },
    mounted() {
      ajax.getImportParam(1).then(response => {
          this.rowStart = response.data.rowNum,
          this.street = response.data.streetName,
          this.nomerKv = response.data.nomerKvName,
          this.family = response.data.familiName,
          this.ls = response.data.lsName,
          this.summa = response.data.summaName
      }).catch(error => {

      })
    }
  }
</script>

<style scoped>

</style>
