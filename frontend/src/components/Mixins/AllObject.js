import ajax from "../../client/index";

export const allObject ={
  data() {
    return {
      positionItem: [],
      operatorItem: [],
      statusItem: [],
      workerItem: [],
    }
  }
  ,
  methods: {
    getPosition() {
      ajax.getPosition().then(response => {
        this.positionItem = response.data
      })
        .catch(error => {
          console.log(error);
        })
    },
    getOperator() {
      ajax.getOperator().then(response => {
        this.operatorItem = response.data;
      }).catch(error => {
        console.log(error)
      })
    },
    getStatus() {
      ajax.getStatus().then(response => {
        this.statusItem = response.data;
      }).catch(error => {
        console.log(error)
      })
    },
    getWorker() {
      ajax.getWorker().then(response => {
        this.workerItem = response.data
      })
        .catch(error => {
          console.log(error)
        })
    }
  }
}
