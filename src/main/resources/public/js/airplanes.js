var tableHelper;
var tableElement;
var edit = false;

$(document).ready(function () {

    tableElement = $('#airplaneTable');
    tableHelper =  new DataTableHelper(tableElement, {
        bLengthChange: false,
        rowId: 'id',
        columns: [
            {"data": "type"},
            {"data": "passengers"},
            {"data": "fuelLeft"},
            {"data": "fromAirport"},
            {"data": "toAirport"}
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
        setAirplaneOptions();
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

function createAirplanes(room, successCallback, errorCallback) {
    ajaxJsonCall('POST', '/api/rooms/create', room, successCallback, errorCallback);
}

function editAirplane(room, successCallback, errorCallback) {
    ajaxJsonCall('POST', '/api/airplanes/edit', room, successCallback, errorCallback);
    console.log(airplane.id);
}

function removeAirplane(airport, successCallback, errorCallback) {
    ajaxJsonCall('DELETE', '/api/airports/delete/' + airport.id, null, successCallback, errorCallback);
}

function getFormData() {
    return {
        type: $("#airplaneType").val(),
        passengers: $("#numberPassengers").val(),
        fuelLeft: $("#fuel").val(),
        fromAirport: $("#fromAirport").val(),
        toAirport: $("#toAirport").val()
    };
}

function setFormData(airplane) {
    console.log(airplane);
    $('#airplaneType').val(airplane.type);
    $('#numberPassengers').val(airplane.passengers);
    $('#fuel').val(airplane.fuelLeft);
    $('#fromAirport').val(airplane.fromAirport);
    $('#toAirport').val(airplane.toAirport);
}

function updateTable() {
    $('button.controls').prop('disabled', selectedId === undefined);
    ajaxJsonCall('GET', '/api/airplanes/', null, function(rooms) {
        tableHelper.dataTable.clear();
        tableHelper.dataTable.rows.add(rooms);
        tableHelper.dataTable.columns.adjust().draw();
    }, null)}