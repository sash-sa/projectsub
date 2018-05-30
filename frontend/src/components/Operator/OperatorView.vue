<template>
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
      <td class="text-xs-left">{{ dateConvert(props.item.created)}}</td>
      <td class="text-xs-left">{{ dateConvert(props.item.update)}}</td>
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
</template>

<script>
  import ajax from "../client/index";

  export default {

    data() {
      return {
        items: [],
        headers: [
          {text: 'Логин', value: 'id', align: "center"},
          {text: 'Полное имя', value: 'theme', align: "center"},
          {text: 'Действия'},
        ],
      }
    },
    methods: {

    }
    ,
    created() {
    }

  }
</script>

<style>

</style>
