const toFixed = (num, fixed) => {
  var re = new RegExp('^-?\\d+(?:\.\\d{0,' + (fixed || -1) + '})?') // eslint-disable-line
  return num.toString().match(re)[0]
}

const buildChart = (type = 'bar', labels, datasets, options) => ({
  type,
  data: {
    labels,
    datasets
  },
  options: {
    responsive: true,
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    },
    ...options
  }
})

export default {
  toFixed,
  buildChart
}
