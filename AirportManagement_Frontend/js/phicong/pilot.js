function viewPilotPage() {
    $(".main").load("./phicong/pilotPage.html", function() {
        setupPilotSearchEvent();
        setupPilotFilter();
        buildPilotTable();
    });
}

function buildPilotTable() {
    $('#pilot-table tbody').empty();
    getListPilots();

    // gender
    setupPilotGenderSelectionInForm();
}

var pilots = [];

// paging
var pilotCurrentPage = 1;
var size = 5;
var tongPilot = 0;

// sorting
var sortField = "id";
var isAsc = false;

// get List
function getListPilots() {
    var url = "http://localhost:8080/api/final/v1/pilots";

    // paging
    url += '?pageNumber=' + pilotCurrentPage + '&size=' + size;

    // sorting
    url += "&sort=" + sortField + "," + (isAsc ? "asc" : "desc");

    // search
    var search = document.getElementById("search-pilot-input").value;
    if (search) {
        url += "&search=" + search;
    }

    // filter
    var maxFlyTime = $("#filter-max-fly-time-select option:selected").text();
    if(maxFlyTime){
        url += "&maxSoGioBay=" + maxFlyTime;
    }
    
    var minFlyTime = $("#filter-min-fly-time-select option:selected").text();
    if(minFlyTime){
        url += "&minSoGioBay=" + minFlyTime;
    }

    var gender = document.getElementById("filter-pilot-gender-select").value;
    if (gender && gender != "All genders") {
        url += "&gender=" + gender;
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
            pilots = data.content;
            fillPilotToTable();
            tongPilot = data.totalPages;
            fillPilotPaging(data.numberOfElements, data.totalPages);
            createPilotPagination(data.totalPages);
            fillPilotSorting();
        },
        error(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
            
    });

}

function fillPilotToTable() {
    pilots.forEach(function(item, index) {
        $('#pilot-table tbody').append(
            '<tr>' +
            '<td> ' +
            '<span class="pilot-checkbox"> ' +
            '<input id="checkbox-' + index + '" type="checkbox" onClick="onChangePilotCheckboxItem()"/>' +
            '<label></label>' +
            '</span>' +
            '</td>' +
            '<td>' + item.username + '</td>' +
            '<td>' + item.firstName + '</td>' +
            '<td>' + item.lastName + '</td>' +
            '<td>' + item.gender + '</td>' +
            '<td>' + item.soGioBay + '</td>' +

            '<td class="td-actions"> ' +
            '<a href="#" data-toggle="tooltip" title="Edit" onclick="showUpdatePilotModal('+ item.id + "," + item.soGioBay + ',\'' + item.firstName + '\',\'' + item.lastName + '\',\'' + item.gender + '\')"><i class="fa-solid fa-pencil"></i></a>' +
            '<a href="#" data-toggle="tooltip" title="Devare" onclick="showDeleteSinglePilotModal(' + item.id + ', \'' + item.firstName + '\',\'' + item.lastName + '\',' + item.soGioBay + ')"><i class="fa-regular fa-trash-can"></i></a>' +
            '</td>' +
            '</tr>'
        );
    });
}

function createPilotPagination(totalPages){
    $(".pagination").empty();
    $('.pagination').append(
        '<li class="page-item" id="above-pilot"><a class="page-link" href="#" onclick="prevPilotPage()" id="pilot-prev-btn">Previous</a></li>' +
        '<li class="page-item"><a class="page-link" href="#" onclick="nextPilotPage()" id="pilot-next-btn">Next</a></li>'
    );

    var totalPilots = totalPages;

    while(totalPilots > 0){
        $('#above-pilot').after(
            '<li class="page-item"><a class="page-link" href="#">' + totalPilots + '</a></li>'
        );

        totalPilots -= 1;
    }

    $('.page-link').click(function(){
        const elem = $(this).text();
        if(elem != 'Previous' && elem != 'Next'){
            changePilotPage(Number(elem));
        }
    });
}

// paging
function fillPilotPaging(currentSize, totalPages) {
    // text
    document.getElementById("pilot-page-info").innerHTML = currentSize + (currentSize > 1 ? " records " : " record ") + pilotCurrentPage + " of " + totalPages;
}

function prevPilotPage() {
    if(pilotCurrentPage == 1){
        changePilotPage(pilotCurrentPage);
    }else
        changePilotPage(pilotCurrentPage - 1);
}

function nextPilotPage() {
    if(pilotCurrentPage == tongPilot){
        changePilotPage(pilotCurrentPage);
    }else
        changePilotPage(pilotCurrentPage + 1);
}

function changePilotPage(page) {
    pilotCurrentPage = page;
    buildPilotTable();
}

// Sorting
function fillPilotSorting() {
    var sortTypeClazz = isAsc ? "fa-sort-up" : "fa-sort-down";
    var defaultSortType = "fa-sort";

    switch (sortField) {
        case 'username':
            changeIconSort("pilot-username-sort", sortTypeClazz);
            changeIconSort("pilot-first-name-sort", defaultSortType);
            changeIconSort("pilot-last-name-sort", defaultSortType);
            changeIconSort("pilot-gender-sort", defaultSortType);
            changeIconSort("pilot-fly-time-sort", defaultSortType);
            break;
        case 'firstName':
            changeIconSort("pilot-username-sort", defaultSortType);
            changeIconSort("pilot-first-name-sort", sortTypeClazz);
            changeIconSort("pilot-last-name-sort", defaultSortType);
            changeIconSort("pilot-gender-sort", defaultSortType);
            changeIconSort("pilot-fly-time-sort", defaultSortType);
            break;

        case 'lastName':
            changeIconSort("pilot-username-sort", defaultSortType);
            changeIconSort("pilot-first-name-sort", defaultSortType);
            changeIconSort("pilot-last-name-sort", sortTypeClazz);
            changeIconSort("pilot-gender-sort", defaultSortType);
            changeIconSort("pilot-fly-time-sort", defaultSortType);
            break;
        case 'gender':
            changeIconSort("pilot-username-sort", defaultSortType);
            changeIconSort("pilot-first-name-sort", defaultSortType);
            changeIconSort("pilot-last-name-sort", defaultSortType);
            changeIconSort("pilot-gender-sort", sortTypeClazz);
            changeIconSort("pilot-fly-time-sort", defaultSortType);
            break;

        case 'soGioBay':
            changeIconSort("pilot-username-sort", defaultSortType);
            changeIconSort("pilot-first-name-sort", defaultSortType);
            changeIconSort("pilot-last-name-sort", defaultSortType);
            changeIconSort("pilot-gender-sort", defaultSortType);
            changeIconSort("pilot-fly-time-sort", sortTypeClazz);
            break;
            // sort by id
        default:
            changeIconSort("pilot-username-sort", defaultSortType);
            changeIconSort("pilot-first-name-sort", defaultSortType);
            changeIconSort("pilot-last-name-sort", defaultSortType);
            changeIconSort("pilot-gender-sort", defaultSortType);
            changeIconSort("pilot-fly-time-sort", defaultSortType);
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
function changePilotSort(field) {
    if (field == sortField) {
        isAsc = !isAsc;
    } else {
        sortField = field;
        isAsc = true;
    }
    buildPilotTable();
}

// search

function setupPilotSearchEvent() {
    $("#search-pilot-input").on("keyup", function(event) {
        // enter key code = 13
        if (event.keyCode === 13) {
            buildPilotTable();
        }
    });
}

// filter
function filterPilot() {
    buildPilotTable();
}

function setupPilotFilter() {
    setupMinFliedTime();
    setupMaxFliedTime();
    setupPilotGender();
}

function setupMinFliedTime() {
    $("#filter-min-fly-time-select").select2({
        placeholder: "Số giờ bay ít nhất"
    });
}

function setupMaxFliedTime() {
    $("#filter-max-fly-time-select").select2({
        placeholder: "Số giờ bay nhiều nhất"
    });
}

function setupPilotGender() {
    $("#filter-pilot-gender-select").select2({
        placeholder: "Select a gender"
    });
}


// Refresh Table
function refreshPilotTable() {
    // refresh paging
    pilotCurrentPage = 1;
    size = 5;

    // refresh sorting
    sortField = "id";
    isAsc = false;

    // refresh search
    document.getElementById("search-pilot-input").value = "";

    // refresh filter
    $('#filter-min-fly-time-select').val('').trigger('change');
    $('#filter-max-fly-time-select').val('').trigger('change');
    $('#filter-pilot-gender-select').val('').trigger('change');

    // Get API
    buildPilotTable();
}

function openPilotModal() {
    $('#addAndUpdatePilotModal').modal('show');
    setupPilotModalTimeEvent();
}

function hidePilotModal() {
    $('#addAndUpdatePilotModal').modal('hide');
}

// open create modal 
function openAddPilotModal() {
    openPilotModal();
    resetAddPilotForm();
}

//enter when input fliedTime field
function setupPilotModalTimeEvent() {
    $("#modal-pilot-fly-time").on("keyup", function(event) {
        // enter key code = 13
        if (event.keyCode === 13) {
            savePilot();
        }
    });
}

//update chuyen bay
function showUpdatePilotModal(id,soGioBay,firstName,lastName,gender) {
    openPilotModal();
    resetUpdatePilotForm(id,soGioBay,firstName,lastName,gender);
}

function resetUpdatePilotForm(id,soGioBay,firstName,lastName,gender){
    // set title
    document.getElementById("addAndUpdatePilot-modal-title").innerHTML = "Update Pilot";

    var pilotIdField = document.getElementById("pilot-id");
    pilotIdField.value = id;


    //reset placeholder
    $('#modal-pilot-gender-select').select2();

    $('#modal-pilot-gender-select option[value="' + gender + '"]').prop('selected', true);
    $('#modal-pilot-gender-select').attr("disabled", "true");

    // $('#modal-pilot-role-hidden').attr("hidden", true);
    $('#modal-pilot-first-name').attr("readOnly", true);
    $('#modal-pilot-last-name').attr("readOnly", true);
    $('#pilot-user-name-block').attr("hidden", 'true');
    $('#pilot-pass-word-block').attr("hidden", 'true');
    document.getElementById("modal-pilot-fly-time").value = soGioBay;
    document.getElementById('modal-pilot-first-name').value = firstName;
    document.getElementById('modal-pilot-last-name').value = lastName;

    // Reset all error message
    resetPilotModalErrMessage();
}

function resetAddPilotForm() {
    // $("#filter-max-start-time-select option:selected").text();
    // set title
    document.getElementById("addAndUpdatePilot-modal-title").innerHTML = "Create New Pilot";
    document.getElementById("pilot-id").value = "";

    //remove disabled attribute

    $('#modal-pilot-gender-select').attr("disabled", false);
    // $('#modal-pilot-role-hidden').attr("hidden", false);
    $('#modal-pilot-first-name').attr("readOnly", false);
    $('#modal-pilot-last-name').attr("readOnly", false);
    $('#pilot-user-name-block').attr("hidden", false);
    $('#pilot-pass-word-block').attr("hidden", false);


    //reset placeholder
    $('#modal-type-plane-select').select2({
        placeholder: "Select gender"
    });

    // Reset all input value

    document.getElementById('modal-pilot-gender-select').value = "";
    document.getElementById("modal-pilot-first-name").value = "";
    document.getElementById("modal-pilot-last-name").value = "";
    // document.getElementById("modal-pilot-role-select").value = "";
    document.getElementById("modal-pilot-user-name").value = "";
    document.getElementById("modal-pilot-pass-word").value = "123@456";
    document.getElementById("modal-pilot-fly-time").value = "";

    // Reset all error message
    resetPilotModalErrMessage();
}


function setupPilotGenderSelectionInForm() {
    $("#modal-pilot-gender-select").select2({
        placeholder: "Select gender"
    });
}


function resetPilotModalErrMessage() {
    hideFieldErrorMessage("modal-input-errMess-user-name", "modal-pilot-user-name");
    hideFieldErrorMessage("modal-input-errMess-first-name", "modal-pilot-first-name");
    hideFieldErrorMessage("modal-input-errMess-last-name", "modal-pilot-last-name");
    hideFieldErrorMessage("modal-input-errMess-gender", "modal-pilot-gender-select");
    hideFieldErrorMessage("modal-input-errMess-pass", "modal-pilot-pass-word");
    hideFieldErrorMessage("modal-input-errMess-fly-time", "modal-pilot-fly-time");
}

// save
function savePilot() {
    var id = document.getElementById("pilot-id").value;
    if (!id) {
        addPilot();
    }
     else {
        updatePilot(id);
    }
}

var error_message_username = "Tên đăng nhập phải có từ 6 đến 50 kí tự!";
var error_message_username_exists = "Tên đăng nhập đã tồn tại!";
var error_message_password = "Mật khẩu tối thiểu 6 kí tự và yêu cầu 1 kí tự đặc biệt";
var error_message_first_name = "Họ đệm phải có từ 6 tới 50 kí tự và không có kí tự đặc biệt!";
var error_message_name = "Tên không được để trống và không có kí tự đặc biệt!";
var error_message_gender = "Lựa chọn giới tính của nhân viên!";
var error_message_fly_time = "Giờ bay phải là số nguyên dương lớn hơn 800!";

function addPilot() {
    var gender = document.getElementById('modal-pilot-gender-select').value;
    var firstName = document.getElementById("modal-pilot-first-name").value;
    var lastName = document.getElementById("modal-pilot-last-name").value;
    // document.getElementById("modal-pilot-role-select").value = "";
    var userName = document.getElementById("modal-pilot-user-name").value;
    var passWord = document.getElementById("modal-pilot-pass-word").value;
    var fliedTime = document.getElementById("modal-pilot-fly-time").value;

    // validate
    var validUsername = isValidPilotUserName(userName);
    var validfirstname = isValidPilotFirstName(firstName);
    var validlastname = isValidPilotLastName(lastName);
    var validPass = isValidPilotPass(passWord);
    var validGender = isValidGender(gender);
    var validFliedTime = isValidFliedTime(fliedTime);

    // format
    if (!validUsername || !validfirstname || !validlastname || !validGender || !validFliedTime|| !validPass) {
        return;
    }

    // check username unique
    $.ajax({
        url: "http://localhost:8080/api/final/v1/staffs/" + userName + "/exists",
        type: 'GET',
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(data,responseTxt,jqXHR) {
            // error
            if (data == undefined || data == null) {
                alert("Error when loading data");
                return;
            }
            // success
            // data == false
            if(data){
                //show error message
                showFieldErrorMessage("modal-input-errMess-user-name", "modal-pilot-user-name", error_message_username_exists);
                return;
            }else{
                createPilotViaAPI(gender,firstName,lastName,userName,passWord,fliedTime);
                return;
            }
        } 
    });
}

function updatePilot(pilotId){
    var fliedTime = document.getElementById("modal-pilot-fly-time").value;
    var role = "PILOT";
    // validate

    var validFliedTime = isValidFliedTime(fliedTime);

    // format
    if (!validFliedTime) {
        return;
    }
    var updateData= {
        "role": role,
        "soGioBay": fliedTime
    };
    //call api
    $.ajax({
        url: 'http://localhost:8080/api/final/v1/pilots/' + pilotId,
        type: 'PUT',
        data: JSON.stringify(updateData), // body
        contentType: "application/json", // type of body (json, xml, text)
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(data, textStatus, xhr) {
            // success
            document.getElementById("pilot-id").innerHTML = "";
            hidePilotModal();
            showSuccessSnackBar("Update successfully");
            // //remove disabled attribute

            // $('#modal-pilot-gender-select').attr("disabled", false);
            // // $('#modal-pilot-role-hidden').attr("hidden", false);
            // $('#modal-pilot-first-name').attr("readOnly", false);
            // $('#modal-pilot-last-name').attr("readOnly", false);
            // $('#pilot-user-name-block').attr("hidden", false);
            // $('#pilot-pass-word-block').attr("hidden", false);
            buildPilotTable();
        },
        error(jqXHR, textStatus, errorThrown) {
            alert("Error when loading data");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
    
}
function createPilotViaAPI(gender,firstName,lastName,userName,passWord,fliedTime) {
    // call api create department
    var newPilot = {
        "firstName": firstName,
        "lastName": lastName,
        "username": userName,
        "passWord": passWord,
        "gender": gender,
        "role": "PILOT",
        "soGioBay": fliedTime
    }

    $.ajax({
        url: 'http://localhost:8080/api/final/v1/pilots',
        type: 'POST',
        data: JSON.stringify(newPilot), // body
        contentType: "application/json", // type of body (json, xml, text)
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(data, textStatus, xhr) {
            // success
            hidePilotModal();
            showSuccessSnackBar("Success! New pilot created!");
            buildPilotTable();
        },
        error(jqXHR, textStatus, errorThrown) {
            alert("Error when loading data");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

function isValidPilotUserName(username) {

    if (!username) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-user-name", "modal-pilot-user-name", error_message_username);
        return false;
    }

    // validate format
    var regex = new RegExp('^(?=.*[a-z])[a-zA-Z0-9_.-]{6,50}$');
    if (!regex.test(username)) {
        showFieldErrorMessage("modal-input-errMess-user-name", "modal-pilot-user-name", error_message_username);
        return false;
    };

    hideFieldErrorMessage("modal-input-errMess-user-name", "modal-pilot-user-name");
    return true;
}

function isValidPilotFirstName(firstName) {

    if (!firstName) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-first-name", "modal-pilot-first-name", error_message_first_name);
        return false;
    }

    // validate format
    // var regex = new RegExp('^[a-zA-Z\\s]{6,50}$');
    // if (!regex.test(firstName)) {
    //     showFieldErrorMessage("modal-input-errMess-first-name", "modal-pilot-first-name", error_message_first_name);
    //     return false;
    // };

    if (firstName.length < 6 || firstName.length > 50) {
        showFieldErrorMessage("modal-input-errMess-first-name", "modal-pilot-first-name", error_message_first_name);
        return false;
    };

    hideFieldErrorMessage("modal-input-errMess-first-name", "modal-pilot-first-name");
    return true;
}

function isValidPilotLastName(name) {

    if (!name) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-last-name", "modal-pilot-last-name", error_message_name);
        return false;
    }

    // validate format
    // var regex = new RegExp('^[a-zA-Z]$');
    // if (!regex.test(name)) {
    //     showFieldErrorMessage("modal-input-errMess-last-name", "modal-pilot-last-name", error_message_name);
    //     return false;
    // };
    
    hideFieldErrorMessage("modal-input-errMess-last-name", "modal-pilot-last-name");
    return true;
}

function isValidGender(gender) {
    if (!gender) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-gender", "modal-pilot-gender-select", error_message_gender);
        return false;
    }

    hideFieldErrorMessage("modal-input-errMess-gender", "modal-pilot-gender-select");
    return true;
}

function isValidPilotPass(pass) {
    if (!pass) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-pass", "modal-pilot-pass-word", error_message_password);
        return false;
    }

    var regex = new RegExp("^(?=.{6,})[a-zA-Z0-9\\s](?=.*[@#$%^&+=]).*$");
    if (!regex.test(pass)) {
        showFieldErrorMessage("modal-input-errMess-pass", "modal-pilot-pass-word", error_message_password);
        return false;
    };

    hideFieldErrorMessage("modal-input-errMess-pass", "modal-pilot-pass-word");
    return true;
}

function isValidFliedTime(time) {
    if (!time) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-fly-time", "modal-pilot-fly-time", error_message_fly_time);
        return false;
    }

    if (Number(time) < 800 ) {
        showFieldErrorMessage("modal-input-errMess-fly-time", "modal-pilot-fly-time", error_message_fly_time);
        return false;
    };

    hideFieldErrorMessage("modal-input-errMess-fly-time", "modal-pilot-fly-time");
    return true;
}
// function showFieldErrorMessage(messageId, inputId, message) {
//     document.getElementById(messageId).innerHTML = message;
//     document.getElementById(messageId).style.display = "block";
//     document.getElementById(inputId).style.border = "1px solid red";
// }

// function hideFieldErrorMessage(messageId, inputId) {
//     document.getElementById(messageId).style.display = "none";
//     document.getElementById(inputId).style.border = "1px solid #ccc";
// }

// delete single pilot
function showDeleteSinglePilotModal(pilotId,firstName,lastName,soGioBay) {
    $('#deleteSinglePilotModal').modal('show');
    document.getElementById('delete-single-pilot-confirm-mess').innerHTML = 'Bạn có chắc chắn muốn xóa thông tin về phi công <span style="color:red;">'+ firstName + ' ' + lastName + ' có số giờ bay là ' + soGioBay + '</span>?';
    document.getElementById('delete-single-pilot-btn').onclick = function() { deleteSinglePilot(pilotId) };
}

function deleteSinglePilot(pilotId) {
    $.ajax({
        url: 'http://localhost:8080/api/final/v1/staffs/' + pilotId,
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
            showSuccessSnackBar("Thành công! Thông tin đã bị xóa");
            $('#deleteSinglePilotModal').modal('hide');
            buildPilotTable();
        }
    });
}

// delete multiple pilot
function onChangePilotCheckboxAll() {
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

function onChangePilotCheckboxItem() {
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

function showDeleteMultiplePilotsModal() {
    $('#deleteMultiplePilotsModal').modal('show');

    // get checked
    var ids = [];
    var fullNames = [];
    var i = 0;
    while (true) {
        var checkboxItem = document.getElementById("checkbox-" + i);
        if (checkboxItem !== undefined && checkboxItem !== null) {
            if (checkboxItem.checked) {
                ids.push(pilots[i].id);
                fullNames.push(pilots[i].firstName + ' ' + pilots[i].lastName);
            }
            i++;
        } else {
            break;
        }
    }
    i = 0;
    var message = "";
    while(i < ids.length){
        message += "<br>- Phi công " + fullNames[i];
        i++;
    }

    if (!ids || ids.length == 0) {
        document.getElementById('delete-pilots-confirm-mess').innerHTML = 'Choose at least one pilot to delete!';
        document.getElementById('delete-multiple-pilots-btn').style.display = 'none';
    } else {
        document.getElementById('delete-pilots-confirm-mess').innerHTML = 'This action can not be undone. Delete <span id="user-pilots-delete-message"></span>?';
        document.getElementById('user-pilots-delete-message').innerHTML += '<span style="color: red;">' + message + '</span>';
        document.getElementById('delete-multiple-pilots-btn').style.display = 'inline-block';
        document.getElementById('delete-multiple-pilots-btn').onclick = function() { deleteMultiplePilots(ids) };
    }
}

function deleteMultiplePilots(pilotIds) {
    $.ajax({
        url: 'http://localhost:8080/api/final/v1/staffs?ids=' + pilotIds.toString(),
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
            showSuccessSnackBar("Thành công, phi công đã bị xóa");
            $('#deleteMultiplePilotsModal').modal('hide');
            buildPilotTable();
        }
    });
}