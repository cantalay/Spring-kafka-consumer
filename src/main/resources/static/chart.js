var maxLabels = 60;

var config = {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
            label: "TOKYO",
            backgroundColor: window.chartColors.red,
            borderColor: window.chartColors.red,
            fill: false,
            data: []
        }, {
            label: "ISTANBUL",
            backgroundColor: window.chartColors.blue,
            borderColor: window.chartColors.blue,
            fill: false,
            data: []
        }, {
            label: "BEIJING",
            backgroundColor: window.chartColors.green,
            borderColor: window.chartColors.green,
            fill: false,
            data: []
        }, {
            label: "MOSCOV",
            backgroundColor: window.chartColors.orange,
            borderColor: window.chartColors.orange,
            fill: false,
            data: []
        }, {
            label: "LONDON",
            backgroundColor: window.chartColors.purple,
            borderColor: window.chartColors.orange,
            fill: false,
            data: []
        }]
    },
    options: {
        responsive: true,
        title:{
            display:false,
            text:"Average wait time in each cluster"
        },
        scales: {
            xAxes: [{
                display: true,
                scaleLabel: {
                    display: false,
                    labelString: 'Timestamp'
                }
            }],
            yAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'waiting time'
                }
            }]
        }
    }
};

window.onload = function() {
    var ctx = document.getElementById("canvas").getContext("2d");
    window.myLine = new Chart(ctx, config);

};


// Here I assume we receive messages in correct order
function addDataToChart(message){
    console.log("SPLITTED MESSAHEEE    : "+message);
    var splitMessage = message.split(" ");

/*    console.log(" SPLITTED 1 :"+splitMessage[0]);
    console.log(" SPLITTED 2 :"+splitMessage[1]);
    console.log(" SPLITTED 3 :"+splitMessage[2]);
    console.log(" SPLITTED 4 :"+splitMessage[3]);*/

    if (splitMessage.length == 9){

        var clusterName = splitMessage[0].toUpperCase();
        var timestamp = splitMessage[6];
        var count = splitMessage[2];
        var avg = splitMessage[2];

        // Add point if new
        if (config.data.labels.indexOf(timestamp) < 0)
            addOne(timestamp);

        // Remove one point if already too many
        if (config.data.labels.length > maxLabels)
            removeOne();

        // Update point
        config.data.datasets.forEach(function(dataset, datasetIndex) {
            if (dataset.label == clusterName){
                dataset.data.forEach(function(point, pointIdx) {
                    if (point.x == timestamp) point.y = avg;
                });
            }
        });

    } else {
        console.log("NEW MESSAGE HERE : -----------    "+message);
    }
    window.myLine.update();
}


function addOne(timestamp) {

    config.data.labels.push(timestamp);
    config.data.datasets.forEach(function(dataset, datasetIndex) {
        dataset.data.push({x: timestamp, y: undefined});
    });

    window.myLine.update();
}

function removeOne() {

    config.data.labels.shift();
    config.data.datasets.forEach(function(dataset, datasetIndex) {
        dataset.data.shift();
    });

    window.myLine.update();
}