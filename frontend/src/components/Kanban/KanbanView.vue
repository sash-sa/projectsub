<template>
  <div>
    <v-layout row class="text-xs-center" style="margin-top: 20px">
      <v-flex xs4 class="text-xs-center">
        <strong>Порядок следования</strong>
      </v-flex>
      <v-flex xs8>
        <draggable v-model="statusItem" @start="drag=true" @end="drag=false" @sort="change"
                   style="text-align: left;">
          <v-layout row v-for="(element,index) in statusItem" :key="element.id"
                    style="font-weight: bold;border-bottom: #7f7f7f solid 1px">
            <v-flex xs2>{{index+1 }}</v-flex>
            <v-flex xs6>{{element.name}}</v-flex>
            <v-flex xs4></v-flex>
          </v-layout>
        </draggable>
      </v-flex>
    </v-layout>

  </div>
</template>

<script>
  import draggable from 'vuedraggable'
  import ajax from "../../client/index";
  import {allObject} from "../Mixins/AllObject";

  export default {
    components: {draggable},
    mixins: [allObject],
    data(){
      return{
        statusItem:[]
      }
    },
    created() {
      ajax.getStatusForIndex().then(response=>{
        this.statusItem=response.data
      })
    },
    methods:{
      change(){
        for(var i=0;i<this.statusItem.length;i++){
            ajax.setNomerStatus(this.statusItem[i].id,i)
        }
      }
    }

  }
</script>

<style scoped>

</style>
