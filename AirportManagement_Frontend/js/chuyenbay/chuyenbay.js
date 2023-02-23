function viewFlightPage() {
    $(".main").load("./chuyenbay/flightPage.html", function() {
        setupFlightSearchEvent();
        setupFlightFilter();
        buildFlightTable();

        //type plane
        setupTypePlaneSelectionInForm();

        // pilot
        setupPilotSelectionInForm();

        //from city and toCity
        setupChuyenCityForm();
    });
}

function buildFlightTable() {
    $('#flight-table tbody').empty();
    getListFlights();
}

var flights = [];
var names = [];
var cities = [];

// paging
var flightCurrentPage = 1;
var size = 5;
var tongFlight = 0;

// sorting
var sortField = "id";
var isAsc = false;

// get List
function getListFlights() {
    var url = "http://localhost:8080/api/final/v1/flights";

    // paging
    url += '?pageNumber=' + flightCurrentPage + '&size=' + size;

    // sorting
    url += "&sort=" + sortField + "," + (isAsc ? "asc" : "desc");

    // search
    var search = document.getElementById("search-flight-input").value;
    if (search) {
        url += "&search=" + search;
    }

    // filter
    var maxStartTime = $("#filter-max-start-time-select option:selected").text();
    if(maxStartTime){
        url += "&maxStartTime=" + maxStartTime;
    }
    
    var minStartTime = $("#filter-min-start-time-select option:selected").text();
    if(minStartTime){
        url += "&minStartTime=" + minStartTime;
    }
    

    // call API from server
    $.ajax({
        url: url,
        type: 'GET',
        contentType: "application/json",
        dataType: 'json', // datatype return
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(data, textStatus, xhr) {
            // success
            flights = data.content;
            fillFlightToTable();
            tongFlight = data.totalPages;
            fillFlightPaging(data.numberOfElements, data.totalPages);
            createFlightPagination(data.totalPages);
            fillFlightSorting();
        },
        error(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
            
    });

}

function fillFlightToTable() {
    flights.forEach(function(item, index) {
        item.CoTruong = (item.CoTruong === null) ? "Unknown" : item.CoTruong; 
        $('#flight-table tbody').append(
            '<tr>' +
            '<td> ' +
            '<span class="flight-checkbox"> ' +
            '<input id="checkbox-' + index + '" type="checkbox" onClick="onChangeFlightCheckboxItem()"/>' +
            '<label></label>' +
            '</span>' +
            '</td>' +
            '<td>' + item.CoTruong + '</td>' +
            '<td>' + item.startTime + '</td>' +
            '<td>' + item.endTime + '</td>' +
            '<td>' + item.fromCity + '</td>' +
            '<td>' + item.toCity + '</td>' +

            '<td class="td-actions"> ' +
            '<a href="#" data-toggle="tooltip" title="Edit" onclick="showUpdateFlightModal('+ item.id + ',\''+ item.type + '\', \'' + item.CoTruong + '\',\'' + item.startTime + '\',\'' + item.endTime + '\' , \'' + item.fromCity + '\',\'' + item.toCity + '\')"><i class="fa-solid fa-pencil"></i></a>' +
            '<a href="#" data-toggle="tooltip" title="Devare" onclick="showDeleteSingleFlightModal(' + item.id + ', \'' + item.startTime + '\',\'' + item.fromCity + '\',\'' + item.toCity + '\')"><i class="fa-regular fa-trash-can"></i></a>' +
            '</td>' +
            '</tr>'
        );
    });
}

function createFlightPagination(totalPages){
    $(".pagination").empty();
    $('.pagination').append(
        '<li class="page-item" id="above-flight"><a class="page-link" href="#" onclick="prevFlightPage()" id="flight-prev-btn">Previous</a></li>' +
        '<li class="page-item"><a class="page-link" href="#" onclick="nextFlightPage()" id="flight-next-btn">Next</a></li>'
    );

    var totalFlights = totalPages;

    while(totalFlights > 0){
        $('#above-flight').after(
            '<li class="page-item"><a class="page-link" href="#">' + totalFlights + '</a></li>'
        );

        totalFlights -= 1;
    }

    $('.page-link').click(function(){
        const elem = $(this).text();
        if(elem != 'Previous' && elem != 'Next'){
            changeFlightPage(Number(elem));
        }
    });
}

// paging
function fillFlightPaging(currentSize, totalPages) {
    // text
    document.getElementById("flight-page-info").innerHTML = currentSize + (currentSize > 1 ? " records " : " record ") + flightCurrentPage + " of " + totalPages;
}

function prevFlightPage() {
    if(flightCurrentPage == 1){
        changeFlightPage(flightCurrentPage);
    }else
        changeFlightPage(flightCurrentPage - 1);
}

function nextFlightPage() {
    if(flightCurrentPage == tongFlight){
        changeFlightPage(flightCurrentPage);
    }else
        changeFlightPage(flightCurrentPage + 1);
}

function changeFlightPage(page) {
    flightCurrentPage = page;
    buildFlightTable();
}

// Sorting
function fillFlightSorting() {
    var sortTypeClazz = isAsc ? "fa-sort-up" : "fa-sort-down";
    var defaultSortType = "fa-sort";

    switch (sortField) {
        case 'startTime':
            changeIconSort("start-time-sort", sortTypeClazz);
            changeIconSort("end-time-sort", defaultSortType);
            break;
        case 'endTime':
            changeIconSort("start-time-sort", defaultSortType);
            changeIconSort("end-time-sort", sortTypeClazz);
            break;
            // sort by id
        default:
            changeIconSort("start-time-sort", defaultSortType);
            changeIconSort("end-time-sort", defaultSortType);
            break;
    }
}

function changeIconSort(id, sortTypeClazz) {
    //chuyen ve kieu sort default
    document.getElementById(id).classList.remove("fa-sort", "fa-sort-up", "fa-sort-down");
    //kieu sorting ban dau
    document.getElementById(id).classList.add(sortTypeClazz);
}

// đổi kiểu sorting
function changeFlightSort(field) {
    if (field == sortField) {
        isAsc = !isAsc;
    } else {
        sortField = field;
        isAsc = true;
    }
    buildFlightTable();
}

// search

function setupFlightSearchEvent() {
    $("#search-flight-input").on("keyup", function(event) {
        // enter key code = 13
        if (event.keyCode === 13) {
            buildFlightTable();
        }
    });
}

// filter
function filterFlight() {
    buildFlightTable();
}

function setupFlightFilter() {
    setupMinStartTime();
    setupMaxStartTime();
}

function setupMinStartTime() {
    $("#filter-min-start-time-select").select2({
        placeholder: "Thời gian xuất phát sớm nhất"
    });
}

function setupMaxStartTime() {
    $("#filter-max-start-time-select").select2({
        placeholder: "Thời gian xuất phát muộn nhất"
    });
}



// Refresh Table
function refreshFlightTable() {
    // refresh paging
    flightCurrentPage = 1;
    size = 5;

    // refresh sorting
    sortField = "id";
    isAsc = false;

    // refresh search
    document.getElementById("search-flight-input").value = "";

    // refresh filter
    $('#filter-min-start-time-select').val('').trigger('change');
    $('#filter-max-start-time-select').val('').trigger('change');

    // Get API
    buildFlightTable();
}

function openFlightModal() {
    $('#addAndUpdateFlightModal').modal('show');
}

function hideFlightModal() {
    $('#addAndUpdateFlightModal').modal('hide');
}

// open create modal 
function openAddFlightModal() {
    openFlightModal();
    resetAddFlightForm();
}

//update chuyen bay
function showUpdateFlightModal(id,typePlane,pilot, startTime, endTime, fromCity, toCity) {  
    openFlightModal();
    resetUpdateFlightForm(id,typePlane,pilot, startTime, endTime, fromCity, toCity);
}

function resetUpdateFlightForm(id, typePlane,pilot, startTime, endTime, fromCity, toCity){
    // set title
    document.getElementById("addAndUpdateFlight-modal-title").innerHTML = "Update Flight";

    var flightIdField = document.getElementById("flight-id");
    flightIdField.value = id;

    // pilot
    // setupPilotSelectionInForm();

    //from city and toCity
    // setupChuyenCityForm();
    // Reset all input value

    //reset placeholder
    $('#modal-type-plane-select').select2();
    //reset placeholder
    $('#modal-city-select').select2();
    //reset placeholder
    $('#modal-pilot-select').select2();

    
    $('#modal-type-plane-select option[value="' + typePlane + '"]').prop('selected', true);
    $('#modal-type-plane-select').attr("disabled", "true");

    document.getElementById("modal-start-time").value = startTime;
    document.getElementById("modal-end-time").value = endTime;

    // https://stackoverflow.com/questions/9837346/get-options-value-by-text
    
    var value = $("#modal-city-select option").filter(function() {
        return $(this).text() === fromCity + ' - ' + toCity;
      }).first().attr("value");
    $('#modal-city-select option[value="' + value + '"]').prop('selected', true);
    
    var pilotValue = $("#modal-pilot-select option").filter(function() {
        return $(this).text() === pilot;
      }).first().attr("value");
    $('#modal-pilot-select option[value="' + pilotValue + '"]').prop('selected', true);

    // Reset all error message
    resetFlightModalErrMessage();
}

function resetAddFlightForm() {
    
    // set title
    document.getElementById("addAndUpdateFlight-modal-title").innerHTML = "Create New Flight";
    document.getElementById("flight-id").value = "";

    //reset all placeholder
    $("#modal-pilot-select").select2({
        placeholder: "Select pilot name"
    });

    $("#modal-city-select").select2({
        placeholder: "Select tuyen bay"
    });

    //remove disabled attribute
    $('#modal-type-plane-select').attr('disabled',false);


    // typePlane placeholder
    // setupTypePlaneSelectionInForm();

    // Reset all input value

    document.getElementById('modal-type-plane-select').value = "";
    document.getElementById("modal-start-time").value = "";
    document.getElementById("modal-end-time").value = "";
    document.getElementById("modal-city-select").value = "";
    document.getElementById("modal-pilot-select").value = "";

    // Reset all error message
    resetFlightModalErrMessage();
}

function setupTypePlaneSelectionInForm() {
    $("#modal-type-plane-select").select2({
        placeholder: "Select a plane type"
    });
}

function setupPilotSelectionInForm() {
    // change selectboxes to selectize mode to be searchable
    // setup call API
    $.ajax({
        url: "http://localhost:8080/api/final/v1/pilots/all",
        dataType: 'json',
        contentType: "application/json",
        type: "GET",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(data, textStatus, xhr) {
            // success
            names = data;
            // alert(data);
            fillIntoSelectPilotForm();
        },
        error(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

function fillIntoSelectPilotForm(){
    names.forEach(function(item) {
        $('#modal-pilot-select').append(
            '<option value="' +item.id + '">' + item.fullName + '</option>'
        );
    });

}

function setupChuyenCityForm(){
    $.ajax({
        url: "http://localhost:8080/api/final/v1/tuyens/cities",
        dataType: 'json',
        contentType: "application/json",
        type: "GET",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(data, textStatus, xhr) {
            // success
            cities = data;
            // alert(data);
            fillIntoSelectCityForm();
        },
        error(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
    
}

function fillIntoSelectCityForm(){
    cities.forEach(function(item) {
        $('#modal-city-select').append(
            '<option value="' +item.id + '">' + item.fromCity + ' - ' + item.toCity + '</option>'
        );
    });
}

function resetFlightModalErrMessage() {
    hideFieldErrorMessage("modal-input-errMess-start-time", "modal-start-time");
    hideFieldErrorMessage("modal-input-errMess-end-time", "modal-end-time");
    hideFieldErrorMessage("modal-input-errMess-typePlane", "modal-type-plane-select");
    hideFieldErrorMessage("modal-input-errMess-pilot", "modal-pilot-select");
    hideFieldErrorMessage("modal-input-errMess-city", "modal-city-select");
    hideFieldErrorMessage("modal-input-errMess-time", "modal-start-time");
    hideFieldErrorMessage("modal-input-errMess-time", "modal-end-time");
}

// save
function saveFlight() {
    var id = document.getElementById("flight-id").value;
    if (!id) {
        addFlight();
    }
     else {
        updateFlight(id);
    }
}

var error_message_not_blank = "Thông tin bắt buộc. Yêu cầu nhập.";
var error_message_format = "format ngày giờ không hợp lệ: dd-MM-yyyy HH:mm:ss";
var error_message_future = "Thời gian không ở trong hiện tại hoặc tương lai";
var error_message_date_range_not_valid = "Thời gian khởi hành phải nhỏ hơn thời gian hạ cánh";
var error_message_type_plane = "bạn phải chọn 1 loại máy bay!";
var error_message_tuyen = "Yêu cầu chọn 1 tuyến bay!";
var error_message_pilot = "Yêu cầu chọn 1 phi công!";

function showFieldErrorMessage(messageId, inputId, message) {
    document.getElementById(messageId).innerHTML = message;
    document.getElementById(messageId).style.display = "block";
    document.getElementById(inputId).style.border = "1px solid red";
}

function hideFieldErrorMessage(messageId, inputId) {
    document.getElementById(messageId).style.display = "none";
    document.getElementById(inputId).style.border = "1px solid #ccc";
}

function addFlight() {
    var typePlane = $('#modal-type-plane-select option:selected').val();
    var startTime = document.getElementById("modal-start-time").value;
    var endTime = document.getElementById("modal-end-time").value;
    var tuyenId = $('#modal-city-select option:selected').val();
    var phiCongId = $('#modal-pilot-select option:selected').val();

    // validate
    var validStartTime = isValidStartTime(startTime);
    var validEndTime = isValidEndTime(endTime);
    var validDateRange = validateDateRange(String(startTime), String(endTime));
    var validTypePlane = isValidTypePlane(typePlane);
    var validTuyen = isValidTuyen(tuyenId);
    var validPilot = isValidPilot(phiCongId);

    // format
    if (!validStartTime || !validEndTime || !validTypePlane || !validTuyen || !validPilot ||!validDateRange) {
        return;
    }

    // gọi API để tạo mới tuyến bay
    createFlightViaAPI(typePlane,startTime,endTime,tuyenId,phiCongId);

}

function updateFlight(flightId){
    var fromTime = document.getElementById("modal-start-time").value;
    var endTime = document.getElementById("modal-end-time").value;
    var tuyenID = $('#modal-city-select option:selected').val();
    var pilotId = $('#modal-pilot-select option:selected').val();

    //validate 
    var validStartTime = isValidStartTime(fromTime);
    var validEndTime = isValidEndTime(endTime);
    var validDateRange = validateDateRange(fromTime, endTime);
    var validTuyen = isValidTuyen(tuyenID);
    var validPilot = isValidPilot(pilotId);

    // format
    if (!validStartTime || !validEndTime || !validTuyen || !validPilot || !validDateRange) {
        return;
    }

    alert("Done");
    var updateData= {
        "startTime": fromTime,
        "endTime": endTime,
        "phiCongId": pilotId,
        "tuyenBayId": tuyenID
    };
    //call api
    $.ajax({
        url: 'http://localhost:8080/api/final/v1/flights/' + flightId,
        type: 'PUT',
        data: JSON.stringify(updateData), // body
        contentType: "application/json", // type of body (json, xml, text)
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(data, textStatus, xhr) {
            // success
            document.getElementById("flight-id").innerHTML = "";
            hideFlightModal();
            showSuccessSnackBar("Update successfully");
            buildFlightTable();
        },
        error(jqXHR, textStatus, errorThrown) {
            alert("Error when loading data");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
    
}
function createFlightViaAPI(typePlane, startTime, endTime, tuyenBayId, phiCongId) {
    // call api create department
    var newFlight = {
        "typePlane": typePlane,
        "startTime": startTime,
        "endTime": endTime,
        "tuyenBayId": tuyenBayId,
        "phiCongId": phiCongId
    }

    $.ajax({
        url: 'http://localhost:8080/api/final/v1/flights',
        type: 'POST',
        data: JSON.stringify(newFlight), // body
        contentType: "application/json", // type of body (json, xml, text)
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(data, textStatus, xhr) {
            // success
            hideFlightModal();
            showSuccessSnackBar("Success! New flight created!");
            buildFlightTable();
        },
        error(jqXHR, textStatus, errorThrown) {
            alert("Error when loading data");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

//kiểm tra với lựa chọn trường type plane
function isValidTypePlane(type) {
    if (!type) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-typePlane", "modal-type-plane-select", error_message_type_plane);
        return false;
    }

    hideFieldErrorMessage("modal-input-errMess-typePlane", "modal-type-plane-select");
    return true;
}

//kiểm tra với lựa chọn trường tuyen id
function isValidTuyen(tuyen) {
    if (!tuyen) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-city", "modal-city-select", error_message_tuyen);
        return false;
    }

    hideFieldErrorMessage("modal-input-errMess-city", "modal-city-select");
    return true;
}

//kiểm tra với lựa chọn trường phi công
function isValidPilot(pilot) {
    if (!pilot) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-pilot", "modal-pilot-select", error_message_pilot);
        return false;
    }

    hideFieldErrorMessage("modal-input-errMess-pilot", "modal-pilot-select");
    return true;
}

// kiem tra start time
function isValidStartTime(startTime) {

    if (!startTime) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-start-time", "modal-start-time", error_message_not_blank);
        return false;
    }
    
    // validate format
    var idate = startTime.split(' ');
    var date = idate[0];
    var time = idate[1];
    if(date == null || time == null){
        
        showFieldErrorMessage("modal-input-errMess-start-time", "modal-start-time", error_message_format);
        return false;
    }
    var result1 = checkDate(date);
    var result2 = checkTime(time);
    if(result1 == null || result2 == null){
        
        showFieldErrorMessage("modal-input-errMess-start-time", "modal-start-time", error_message_format);
        return false;
    }

    //kiem tra co phai thoi gian trong tuong lai khong
    if(!isFutureDate(date)){
        
        showFieldErrorMessage("modal-input-errMess-start-time", "modal-start-time", error_message_future);
        return false;
    }

    hideFieldErrorMessage("modal-input-errMess-start-time", "modal-start-time");
    return true;
}


//kiem tra end time
function isValidEndTime(endTime) {

    if (!endTime) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-end-time", "modal-end-time", error_message_not_blank);
        return false;
    }
    
    // validate format
    var idate = endTime.split(' ');
    var date = idate[0];
    var time = idate[1];
    if(date == null || time == null){
        showFieldErrorMessage("modal-input-errMess-end-time", "modal-end-time", error_message_format);
        return false;
    }
    var result1 = checkDate(date);
    var result2 = checkTime(time);
    if(result1 == null || result2 == null){
        
        showFieldErrorMessage("modal-input-errMess-end-time", "modal-end-time", error_message_format);
        return false;
    }

    //kiem tra co phai thoi gian trong tuong lai khong
    if(!isFutureDate(date)){
        showFieldErrorMessage("modal-input-errMess-end-time", "modal-end-time", error_message_future);
        return false;
    }

    hideFieldErrorMessage("modal-input-errMess-end-time", "modal-end-time");
    return true;
}

//validate start time < end time
function validateDateRange(startTime, endTime) {

    //start time
    // validate format
    var idate1 = startTime.split(' ');
    var date1 = idate1[0];
    var time1 = idate1[1];

    //sai format start time
    if(date1 == null || time1 == null){
        showFieldErrorMessage("modal-input-errMess-time", "modal-start-time", error_message_format);
        return false;
    }

    var dateResult1 = checkDate(date1);
    var timeResult1 = checkTime(time1);
    
    //check end time
    var idate2 = endTime.split(' ');
    var date2 = idate2[0];
    var time2= idate2[1];
    if(date2 == null || time2 == null){
        showFieldErrorMessage("modal-input-errMess-time", "modal-end-time", error_message_format);
        return false;
    }
    var dateResult2 = checkDate(date2);
    var timeResult2 = checkTime(time2);
    //trường hợp ngày không hợp lệ
    if(dateResult1 == null || dateResult2 == null){
        showFieldErrorMessage("modal-input-errMess-time", "modal-start-time", error_message_format);
        showFieldErrorMessage("modal-input-errMess-time", "modal-end-time", error_message_format);
        return false;
    }
    var temp1 = date1.split('-');
    const d1 = new Date(temp1[2], temp1[1], temp1[0]);
    var temp2 = date2.split('-');
    const d2 = new Date(temp2[2], temp2[1], temp2[0]);
    if(d1 > d2){
        showFieldErrorMessage("modal-input-errMess-time", "modal-start-time", error_message_date_range_not_valid);
        showFieldErrorMessage("modal-input-errMess-time", "modal-end-time", error_message_date_range_not_valid);
        return false;
    }

    if(timeResult1 == null || timeResult2 == null) {
        showFieldErrorMessage("modal-input-errMess-time", "modal-start-time", error_message_format);
        showFieldErrorMessage("modal-input-errMess-time", "modal-end-time", error_message_format);
        return false;
    }
    var i = 1;
    while(i <=3){
        if(Number(timeResult1[i]) > Number(timeResult2[i])){
            showFieldErrorMessage("modal-input-errMess-time", "modal-start-time", error_message_date_range_not_valid);
            showFieldErrorMessage("modal-input-errMess-time", "modal-end-time", error_message_date_range_not_valid);
            return false;
        }
        i++;
    }

    hideFieldErrorMessage("modal-input-errMess-time", "modal-start-time");
    hideFieldErrorMessage("modal-input-errMess-time", "modal-end-time");
    return true;
}

//check format ngay
function checkDate(checkDate){
    //format dd-MM-yyyy
    // regular expression to match required date format
    var dateformat = /^(0[1-9]|1\d|2\d|3[01])\-(0[1-9]|1[0-2])\-(19|20)\d{2}$/;
    return checkDate.match(dateformat);
}

//check format gio
function checkTime(time){
    //format time: HH:mm:ss
    var regex = /^(?:(?:([01]?\d|2[0-3]):)?([0-5]?\d):)?([0-5]?\d)$/;
    return time.match(regex);
}

//check co phai ngay trong tuong lai khong
function isFutureDate(idate){
    var today = new Date().getTime(),
        temp = idate.split("-");
    var newDate = new Date(temp[2], temp[1], temp[0]).getTime();
    return (today - newDate) < 0;
}

// delete single flight
function showDeleteSingleFlightModal(flightId,fromTime,from,to) {
    $('#deleteSingleFlightModal').modal('show');
    document.getElementById('delete-single-flight-confirm-mess').innerHTML = 'This action can not be undone. Delete <span style="color:red;">'+ 'chuyến bay xuất phát lúc '+ fromTime + " từ " + from + " tới " + to + '</span>?';
    document.getElementById('delete-single-flight-btn').onclick = function() { deleteSingleFlight(flightId) };
}

function deleteSingleFlight(flightId) {
    $.ajax({
        url: 'http://localhost:8080/api/final/v1/flights/' + flightId,
        type: 'DELETE',
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(result) {
            // error
            if (result == undefined || result == null) {
                alert("Error when loading data");
                return;
            }

            // success
            showSuccessSnackBar("Success! Flight deleted.");
            $('#deleteSingleFlightModal').modal('hide');
            buildFlightTable();
        }
    });
}

// delete multiple flight
function onChangeFlightCheckboxAll() {
    var i = 0;
    while (true) {
        var checkboxItem = document.getElementById("checkbox-" + i);
        if (checkboxItem !== undefined && checkboxItem !== null) {
            checkboxItem.checked = document.getElementById("checkbox-all").checked
                // if (document.getElementById("checkbox-all").checked) {
                //     checkboxItem.checked = true;
                // } else {
                //     checkboxItem.checked = false;
                // }
            i++;
        } else {
            break;
        }
    }
}

function onChangeFlightCheckboxItem() {
    var i = 0;
    while (true) {
        var checkboxItem = document.getElementById("checkbox-" + i);
        if (checkboxItem !== undefined && checkboxItem !== null) {
            if (!checkboxItem.checked) {
                document.getElementById("checkbox-all").checked = false;
                return;
            }
            i++;
        } else {
            break;
        }
    }
    document.getElementById("checkbox-all").checked = true;
}

function showDeleteMultipleFlightsModal() {
    $('#deleteMultipleFlightsModal').modal('show');

    // get checked
    var ids = [];
    var fromCities = [];
    var toCities = [];
    var fromTimes = [];
    var endTimes = [];
    var i = 0;
    while (true) {
        var checkboxItem = document.getElementById("checkbox-" + i);
        if (checkboxItem !== undefined && checkboxItem !== null) {
            if (checkboxItem.checked) {
                ids.push(flights[i].id);
                fromCities.push(flights[i].fromCity);
                toCities.push(flights[i].toCity);
                fromTimes.push(flights[i].startTime);
                endTimes.push(flights[i].endTime);
            }
            i++;
        } else {
            break;
        }
    }
    i = 0;
    var message = "";
    while(i < fromCities.length){
        message += "<br>- Chuyến bay từ " + fromCities[i] + " tới " + toCities[i] + " , bay lúc " + fromTimes[i] + " và hạ cánh lúc " + endTimes[i];
        i++;
    }

    if (!ids || ids.length == 0) {
        document.getElementById('delete-flights-confirm-mess').innerHTML = 'Choose at least one flight to delete!';
        document.getElementById('delete-multiple-flights-btn').style.display = 'none';
    } else {
        document.getElementById('delete-flights-confirm-mess').innerHTML = 'This action can not be undone. Delete <span id="user-flights-delete-message"></span>?';
        document.getElementById('user-flights-delete-message').innerHTML += '<span style="color: red;">' + message + '</span>';
        document.getElementById('delete-multiple-flights-btn').style.display = 'inline-block';
        document.getElementById('delete-multiple-flights-btn').onclick = function() { deleteMultipleFlights(ids) };
    }
}

function deleteMultipleFlights(flightIds) {
    $.ajax({
        url: 'http://localhost:8080/api/final/v1/flights?ids=' + flightIds.toString(),
        type: 'DELETE',
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(result) {
            // error
            if (result == undefined || result == null) {
                alert("Error when loading data");
                return;
            }

            // success
            showSuccessSnackBar("Success! Flight deleted.");
            $('#deleteMultipleFlightsModal').modal('hide');
            buildFlightTable();
        }
    });
}