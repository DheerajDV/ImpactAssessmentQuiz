import  "./chart.min.js"
import "./chartjs-plugin-datalabels.min.js"



async function initial() {
var month=document.getElementById("month");
var year=document.getElementById("year");
var array=[]
  await fetch(`BarChart/${month.value}&${year.value}`)
  .then(response=>response.json())
  .then(data=>{
array=data
  })
const label=[]
  for (var i =0;i<array.length;i++)
  {
label[i]=i+1;

  }
Chart.register(ChartDataLabels);
  var chart=new Chart(
    document.getElementById('acquisitions'),
    {
    plugins: [ChartDataLabels],
      type: 'bar',
      data: {
//        labels: label,

        datasets: [
          {
                borderColor: '#36A2EB',
                backgroundColor: '#9BD0F5',
//            label: 'Courses by date',
            data: array
          }
        ]
      }
    }
  )


  month.addEventListener("change", updatedata)
  year.addEventListener("change", updatedata)
   async function updatedata()
  {
  var uparray=[]
   await fetch(`BarChart/${month.value}&${year.value}`)
    .then(response=>response.json())
    .then(data=>{

  uparray=data
    })
  var uplabel=[]
    for (var i =0;i<uparray.length;i++)
    {
  uplabel[i]=i+1;
    }

await addData(chart,uplabel,uparray)

   }
function addData(chart, label, data) {
    chart.data.labels=label;
    chart.data.datasets[0].data=data;
    chart.update();
}
// document.getElementById('acquisitions').addEventListener('click', function(evt) {
//    var firstPoint = chart.getElementsAtEventForMode(evt, 'nearest', { intersect: true }, true);
//    console.log(firstPoint)
//    if (firstPoint) {
//      var label = chart.data.labels[firstPoint.index];
//      var value = chart.data.datasets[firstPoint.datasetIndex].data[firstPoint.index];
//
//      alert('Label: ' + label + "\nValue: " + value);
//    }
//  });

   ;}
initial();
