var tableHelper;
var tableElement;
var selectedId;
var edit = false;

$(document).ready(function () {

    tableElement = $('#airplaneTable');
    tableHelper =  new DataTableHelper(tableElement, {
        bLengthChange: false,
        rowId: 'id',
        columns: [
            {"data": "airplaneType"},
            {"data": "numberPassengers"},
            {"data": "maxFuel"},
            {"data": "fuelLeft"}
        ]
    });

    $('#create').on('click', function(event) {
        $('airplaneModal .modal-title').html('Create airplane');
        $('#airplaneModal').modal('show');
    });

    $('#edit').on('click', function(event) {
        edit = true;
        var airplane = tableHelper.getSelectedRowData();
        setFormData(airplane);
        $('#airplaneModal .modal-title').html('Editing');
        $('#airplaneModal').modal('show');
    });

    $('#remove').on('click', function(event) {
        var airplane = tableHelper.getSelectedRowData();
        bootboxConfirm("Are you sure you want to delete this airplane?", function(result){
            if (result == true){
                removeAirplane(airplane, function() {
                        toastr.success('Removed the airplane!');
                        updateTable();
                    },
                    handleError);
            }
            else{
                $('#modal').modal('toggle');
            }
        });
    });

    $('#airplaneForm').submit(function(event) {
        event.preventDefault();
        if (edit) {
            handleEditFormSubmit();
        } else {
            handleCreateFormSubmit();
        }
    });

    updateTable();
});

function handleCreateFormSubmit() {
    var data = getFormData();

    console.log(data);
    createAirplanes(data, function(result) {
        $('#airplaneForm').get(0).reset();
        updateTable();
        $('#airplaneModal').modal('hide');
    }, handleError);
}

function handleEditFormSubmit() {
    var airplane = tableHelper.getSelectedRowData();
    var data = getFormData();
    _.extend(airplane, data);
    editAirplane(airplane, function(result) {
        $('#airplaneForm').get(0).reset();
        updateTable();
        $('#airplaneModal').modal('hide');
        edit = false;
    }, handleError);
}

function handleError(error) {
    toastr.error(JSON.parse(error.responseText).message);
    console.log(error);
}

function getAirplanes(successCallback, errorCallback) {
    ajaxJsonCall('GET', '/api/airplanes/', null, successCallback, errorCallback);
}

function createAirplanes(airplane, successCallback, errorCallback) {
    console.log("error error");
    console.log(airplane);
    ajaxJsonCall('POST', '/api/airplanes/create', airplane, successCallback, errorCallback);
}

function editAirplane(airplane, successCallback, errorCallback) {
    ajaxJsonCall('POST', '/api/airplanes/edit', airplane, successCallback, errorCallback);
    console.log(airplane.id);
}

function removeAirplane(airplane, successCallback, errorCallback) {
    ajaxJsonCall('DELETE', '/api/airplanes/delete/' + airplane.id, null, successCallback, errorCallback);
}

function getFormData() {
    return {
        airplaneType: $("#airplaneType").val(),
        numberPassengers: $("#numberPassengers").val(),
        maxFuel: $("#maxFuel").val(),
        fuelLeft: $("#fuelLeft").val(),
    };
}

function setFormData(airplane) {
    console.log(airplane);
    $('#airplaneType').val(airplane.type);
    $('#numberPassengers').val(airplane.numberPassengers);
    $('#maxFuel').val (airplane.maxFuel),
    $('#fuelLeft').val(airplane.fuelLeft);
}

function updateTable() {
    $('button.controls').prop('disabled', selectedId === undefined);
    ajaxJsonCall('GET', '/api/airplanes/', null, function(airplanes) {
        tableHelper.dataTable.clear();
        tableHelper.dataTable.rows.add(airplanes);
        tableHelper.dataTable.columns.adjust().draw();
    }, null)}