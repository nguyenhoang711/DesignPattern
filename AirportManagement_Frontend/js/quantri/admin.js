function viewAdminPage() {
    $(".main").load("./quantri/adminPage.html", function() {
        setupAdminSearchEvent();
        setupAdminFilter();
        buildAdminTable();
    });
}

function buildAdminTable() {
    $('#admin-table tbody').empty();
    getListAdmins();
}

var admins = [];

// paging
var adminCurrentPage = 1;
var size = 5;
var tongAdmin = 0;

// sorting
var sortField = "id";
var isAsc = false;

// get List
function getListAdmins() {
    var url = "http://localhost:8080/api/final/v1/admins";

    // paging
    url += '?pageNumber=' + adminCurrentPage + '&size=' + size;

    // sorting
    url += "&sort=" + sortField + "," + (isAsc ? "asc" : "desc");

    // search
    var search = document.getElementById("search-admin-input").value;
    if (search) {
        url += "&search=" + search;
    }

    // filter
    var maxExpYear = $("#filter-max-exp-year-select option:selected").text();
    if(maxExpYear){
        url += "&maxNamKN=" + maxExpYear;
    }
    
    var minExpYear = $("#filter-min-exp-year-select option:selected").text();
    if(minExpYear){
        url += "&minNamKN=" + minExpYear;
    }

    var gender = document.getElementById("filter-admin-gender-select").value;
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
            admins = data.content;
            fillAdminToTable();
            tongAdmin = data.totalPages;
            fillAdminPaging(data.numberOfElements, data.totalPages);
            createAdminPagination(data.totalPages);
            fillAdminSorting();
        },
        error(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
            
    });

}

function fillAdminToTable() {
    admins.forEach(function(item, index) {
        $('#admin-table tbody').append(
            '<tr>' +
            '<td> ' +
            '<span class="admin-checkbox"> ' +
            '<input id="checkbox-' + index + '" type="checkbox" onClick="onChangeAdminCheckboxItem()"/>' +
            '<label></label>' +
            '</span>' +
            '</td>' +
            '<td>' + item.username + '</td>' +
            '<td>' + item.firstName + '</td>' +
            '<td>' + item.lastName + '</td>' +
            '<td>' + item.gender + '</td>' +
            '<td>' + item.namKN + '</td>' +

            '<td class="td-actions"> ' +
            '<a href="#" data-toggle="tooltip" title="Edit" onclick="showUpdateAdminModal('+ item.id + "," + item.namKN + ',\'' + item.firstName + '\',\'' + item.lastName + '\',\'' + item.gender + '\')"><i class="fa-solid fa-pencil"></i></a>' +
            '<a href="#" data-toggle="tooltip" title="Devare" onclick="showDeleteSingleAdminModal(' + item.id + ', \'' + item.firstName + '\',\'' + item.lastName + '\',' + item.namKN + ')"><i class="fa-regular fa-trash-can"></i></a>' +
            '</td>' +
            '</tr>'
        );
    });
}

function createAdminPagination(totalPages){
    $(".pagination").empty();
    $('.pagination').append(
        '<li class="page-item" id="above-admin"><a class="page-link" href="#" onclick="prevAdminPage()" id="admin-prev-btn">Previous</a></li>' +
        '<li class="page-item"><a class="page-link" href="#" onclick="nextAdminPage()" id="admin-next-btn">Next</a></li>'
    );

    var totalAdmins = totalPages;

    while(totalAdmins > 0){
        $('#above-admin').after(
            '<li class="page-item"><a class="page-link" href="#">' + totalAdmins + '</a></li>'
        );

        totalAdmins -= 1;
    }

    $('.page-link').click(function(){
        const elem = $(this).text();
        if(elem != 'Previous' && elem != 'Next'){
            changeAdminPage(Number(elem));
        }
    });
}

// paging
function fillAdminPaging(currentSize, totalPages) {
    // text
    document.getElementById("admin-page-info").innerHTML = currentSize + (currentSize > 1 ? " records " : " record ") + adminCurrentPage + " of " + totalPages;
}

function prevAdminPage() {
    if(adminCurrentPage == 1){
        changeAdminPage(adminCurrentPage);
    }else
        changeAdminPage(adminCurrentPage - 1);
}

function nextAdminPage() {
    if(adminCurrentPage == tongAdmin){
        changeAdminPage(adminCurrentPage);
    }else
        changeAdminPage(adminCurrentPage + 1);
}

function changeAdminPage(page) {
    adminCurrentPage = page;
    buildAdminTable();
}

// Sorting
function fillAdminSorting() {
    var sortTypeClazz = isAsc ? "fa-sort-up" : "fa-sort-down";
    var defaultSortType = "fa-sort";

    switch (sortField) {
        case 'username':
            changeIconSort("admin-username-sort", sortTypeClazz);
            changeIconSort("admin-first-name-sort", defaultSortType);
            changeIconSort("admin-last-name-sort", defaultSortType);
            changeIconSort("admin-gender-sort", defaultSortType);
            changeIconSort("admin-exp-year-sort", defaultSortType);
            break;
        case 'firstName':
            changeIconSort("admin-username-sort", defaultSortType);
            changeIconSort("admin-first-name-sort", sortTypeClazz);
            changeIconSort("admin-last-name-sort", defaultSortType);
            changeIconSort("admin-gender-sort", defaultSortType);
            changeIconSort("admin-exp-year-sort", defaultSortType);
            break;

        case 'lastName':
            changeIconSort("admin-username-sort", defaultSortType);
            changeIconSort("admin-first-name-sort", defaultSortType);
            changeIconSort("admin-last-name-sort", sortTypeClazz);
            changeIconSort("admin-gender-sort", defaultSortType);
            changeIconSort("admin-exp-year-sort", defaultSortType);
            break;
        case 'gender':
            changeIconSort("admin-username-sort", defaultSortType);
            changeIconSort("admin-first-name-sort", defaultSortType);
            changeIconSort("admin-last-name-sort", defaultSortType);
            changeIconSort("admin-gender-sort", sortTypeClazz);
            changeIconSort("admin-exp-year-sort", defaultSortType);
            break;

        case 'namKN':
            changeIconSort("admin-username-sort", defaultSortType);
            changeIconSort("admin-first-name-sort", defaultSortType);
            changeIconSort("admin-last-name-sort", defaultSortType);
            changeIconSort("admin-gender-sort", defaultSortType);
            changeIconSort("admin-exp-year-sort", sortTypeClazz);
            break;
            // sort by id
        default:
            changeIconSort("admin-username-sort", defaultSortType);
            changeIconSort("admin-first-name-sort", defaultSortType);
            changeIconSort("admin-last-name-sort", defaultSortType);
            changeIconSort("admin-gender-sort", defaultSortType);
            changeIconSort("admin-exp-year-sort", defaultSortType);
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
function changeAdminSort(field) {
    if (field == sortField) {
        isAsc = !isAsc;
    } else {
        sortField = field;
        isAsc = true;
    }
    buildAdminTable();
}


// search

function setupAdminSearchEvent() {
    $("#search-admin-input").on("keyup", function(event) {
        // enter key code = 13
        if (event.keyCode === 13) {
            buildAdminTable();
        }
    });
}

// filter
function filterAdmin() {
    buildAdminTable();
}

function setupAdminFilter() {
    setupMinExpYear();
    setupMaxExpYear();
    setupAdminGender();
}

function setupMinExpYear() {
    $("#filter-min-exp-year-select").select2({
        placeholder: "Số năm KN ít nhất"
    });
}

function setupMaxExpYear() {
    $("#filter-max-exp-year-select").select2({
        placeholder: "Số năm KN nhiều nhất"
    });
}

function setupAdminGender() {
    $("#filter-admin-gender-select").select2({
        placeholder: "Select a gender"
    });
}


// Refresh Table
function refreshAdminTable() {
    // refresh paging
    adminCurrentPage = 1;
    size = 5;

    // refresh sorting
    sortField = "id";
    isAsc = false;

    // refresh search
    document.getElementById("search-admin-input").value = "";

    // refresh filter
    $('#filter-min-exp-year-select').val('').trigger('change');
    $('#filter-max-exp-year-select').val('').trigger('change');
    $('#filter-admin-gender-select').val('').trigger('change');

    // Get API
    buildAdminTable();
}

function openAdminModal() {
    $('#addAndUpdateAdminModal').modal('show');
    setupAdminModalYearEvent();
}

function hideAdminModal() {
    $('#addAndUpdateAdminModal').modal('hide');
}

// open create modal 
function openAddAdminModal() {
    openAdminModal();
    resetAddAdminForm();
}

//enter when input fliedTime field
function setupAdminModalYearEvent() {
    $("#modal-admin-exp-year").on("keyup", function(event) {
        // enter key code = 13
        if (event.keyCode === 13) {
            saveAdmin();
        }
    });
}

//update chuyen bay
function showUpdateAdminModal(id,namKN,firstName,lastName,gender) {
    openAdminModal();
    resetUpdateAdminForm(id,namKN,firstName,lastName,gender);
}

function resetUpdateAdminForm(id,namKN,firstName,lastName,gender){
    // set title
    document.getElementById("addAndUpdateAdmin-modal-title").innerHTML = "Update Admin";

    var adminIdField = document.getElementById("admin-id");
    adminIdField.value = id;
    //reset placeholder
    $('#modal-admin-gender-select').select2();

    $('#modal-admin-gender-select option[value="' + gender + '"]').prop('selected', true);
    $('#modal-admin-gender-select').attr("disabled", "true");

    // $('#modal-admin-role-hidden').attr("hidden", true);
    $('#modal-admin-first-name').attr("readOnly", true);
    $('#modal-admin-last-name').attr("readOnly", true);
    $('#admin-user-name-block').attr("hidden", 'true');
    $('#admin-pass-word-block').attr("hidden", 'true');
    document.getElementById("modal-admin-exp-year").value = namKN;
    document.getElementById('modal-admin-first-name').value = firstName;
    document.getElementById('modal-admin-last-name').value = lastName;

    // Reset all error message
    resetAdminModalErrMessage();
}

function resetAddAdminForm() {
    // $("#filter-max-start-time-select option:selected").text();
    // set title
    document.getElementById("addAndUpdateAdmin-modal-title").innerHTML = "Create New Admin";
    document.getElementById("admin-id").value = "";

    //reset placeholder
    $('#modal-type-plane-select').select2({
        placeholder: "Select gender"
    });

    //remove disabled attribute

    $('#modal-admin-gender-select').attr("disabled", false);
    // $('#modal-admin-role-hidden').attr("hidden", false);
    $('#modal-admin-first-name').attr("readOnly", false);
    $('#modal-admin-last-name').attr("readOnly", false);
    $('#admin-user-name-block').attr("hidden", false);
    $('#admin-pass-word-block').attr("hidden", false);

    // Reset all input value

    document.getElementById('modal-admin-gender-select').value = "";
    document.getElementById("modal-admin-first-name").value = "";
    document.getElementById("modal-admin-last-name").value = "";
    // document.getElementById("modal-admin-role-select").value = "";
    document.getElementById("modal-admin-user-name").value = "";
    document.getElementById("modal-admin-pass-word").value = "123@456";
    document.getElementById("modal-admin-exp-year").value = "";

    // Reset all error message
    resetAdminModalErrMessage();
}

function setupAdminGenderSelectionInForm() {
    $("#modal-admin-gender-select").select2({
        placeholder: "Select gender"
    });
}


function resetAdminModalErrMessage() {
    hideFieldErrorMessage("modal-input-errMess-admin-user-name", "modal-admin-user-name");
    hideFieldErrorMessage("modal-input-errMess-admin-first-name", "modal-admin-first-name");
    hideFieldErrorMessage("modal-input-errMess-admin-last-name", "modal-admin-last-name");
    hideFieldErrorMessage("modal-input-errMess-admin-gender", "modal-admin-gender-select");
    hideFieldErrorMessage("modal-input-errMess-admin-pass", "modal-admin-pass-word");
    hideFieldErrorMessage("modal-input-errMess-exp-year", "modal-admin-exp-year");
}

// save
function saveAdmin() {
    var id = document.getElementById("admin-id").value;
    if (!id) {
        addAdmin();
    }
     else {
        updateAdmin(id);
    }
}

var error_message_exp_year = "Số năm kinh nghiệm phải là số nguyên dương lớn hơn 3!";

function addAdmin() {
    var gender = document.getElementById('modal-admin-gender-select').value;
    var firstName = document.getElementById("modal-admin-first-name").value;
    var lastName = document.getElementById("modal-admin-last-name").value;
    // document.getElementById("modal-admin-role-select").value = "";
    var userName = document.getElementById("modal-admin-user-name").value;
    var passWord = document.getElementById("modal-admin-pass-word").value;
    var namKN = document.getElementById("modal-admin-exp-year").value;

    // validate
    var validUsername = isValidAdminUserName(userName);
    var validfirstname = isValidAdminFirstName(firstName);
    var validlastname = isValidAdminLastName(lastName);
    var validPass = isValidAdminPass(passWord);
    var validGender = isValidAdminGender(gender);
    var validExpYear = isValidExpYEar(namKN);

    // format
    if (!validUsername || !validfirstname || !validlastname || !validGender || !validExpYear|| !validPass) {
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
                showFieldErrorMessage("modal-input-errMess-admin-user-name", "modal-admin-user-name", error_message_username_exists);
                return;
            }else{
                createAdminViaAPI(gender,firstName,lastName,userName,passWord,namKN);
                return;
            }
        } 
    });
    
}

function updateAdmin(adminId){
    var namKN = document.getElementById("modal-admin-exp-year").value;

    // validate

    var validExp = isValidExpYEar(namKN);

    // format
    if (!validExp) {
        return;
    }
    var role = "ADMIN";
    var updateData= {
        "role": role,
        "namKN": namKN
    };
    //call api
    $.ajax({
        url: 'http://localhost:8080/api/final/v1/admins/' + adminId,
        type: 'PUT',
        data: JSON.stringify(updateData), // body
        contentType: "application/json", // type of body (json, xml, text)
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(data, textStatus, xhr) {
            // success
            hideAdminModal();
            showSuccessSnackBar("Update successfully");
            buildAdminTable();
        },
        error(jqXHR, textStatus, errorThrown) {
            alert("Error when loading data");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
    
}
function createAdminViaAPI(gender,firstName,lastName,userName,passWord,namKN) {
    // call api create department
    var newAdmin = {
        "firstName": firstName,
        "lastName": lastName,
        "username": userName,
        "passWord": passWord,
        "gender": gender,
        "role": "ADMIN",
        "namKN": namKN
    }

    $.ajax({
        url: 'http://localhost:8080/api/final/v1/admins',
        type: 'POST',
        data: JSON.stringify(newAdmin), // body
        contentType: "application/json", // type of body (json, xml, text)
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(data, textStatus, xhr) {
            // success
            hideAdminModal();
            showSuccessSnackBar("Success! New admin created!");
            buildAdminTable();
        },
        error(jqXHR, textStatus, errorThrown) {
            alert("Error when loading data");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

function isValidAdminUserName(username) {

    if (!username) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-admin-user-name", "modal-admin-user-name", error_message_username);
        return false;
    }

    // validate format
    var regex = new RegExp('^(?=.*[a-z])[a-zA-Z0-9_.-]{6,50}$');
    if (!regex.test(username)) {
        showFieldErrorMessage("modal-input-errMess-admin-user-name", "modal-admin-user-name", error_message_username);
        return false;
    };

    hideFieldErrorMessage("modal-input-errMess-admin-user-name", "modal-admin-user-name");
    return true;
}

function isValidAdminFirstName(firstName) {

    if (!firstName) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-admin-first-name", "modal-admin-first-name", error_message_first_name);
        return false;
    }

    // validate format
    // var regex = new RegExp('^[a-zA-Z\\s]{6,50}$');
    // if (!regex.test(firstName)) {
    //     showFieldErrorMessage("modal-input-errMess-first-name", "modal-admin-first-name", error_message_first_name);
    //     return false;
    // };

    if (firstName.length < 6 || firstName.length > 50) {
        showFieldErrorMessage("modal-input-errMess-admin-first-name", "modal-admin-first-name", error_message_first_name);
        return false;
    };

    hideFieldErrorMessage("modal-input-errMess-admin-first-name", "modal-admin-first-name");
    return true;
}

function isValidAdminLastName(name) {

    if (!name) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-admin-last-name", "modal-admin-last-name", error_message_name);
        return false;
    }

    // validate format
    // var regex = new RegExp('^[a-zA-Z]$');
    // if (!regex.test(name)) {
    //     showFieldErrorMessage("modal-input-errMess-last-name", "modal-admin-last-name", error_message_name);
    //     return false;
    // };
    
    hideFieldErrorMessage("modal-input-errMess-admin-last-name", "modal-admin-last-name");
    return true;
}

function isValidAdminGender(gender) {
    if (!gender) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-admin-gender", "modal-admin-gender-select", error_message_gender);
        return false;
    }

    hideFieldErrorMessage("modal-input-errMess-admin-gender", "modal-admin-gender-select");
    return true;
}

function isValidAdminPass(pass) {
    if (!pass) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-admin-pass-word", "modal-admin-pass-word", error_message_password);
        return false;
    }

    var regex = new RegExp("^(?=.{6,})[a-zA-Z0-9\\s](?=.*[@#$%^&+=]).*$");
    if (!regex.test(pass)) {
        showFieldErrorMessage("modal-input-errMess-admin-pass-word", "modal-admin-pass-word", error_message_password);
        return false;
    };

    hideFieldErrorMessage("modal-input-errMess-admin-pass-word", "modal-admin-pass-word");
    return true;
}

function isValidExpYEar(exp) {
    if (!exp) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-exp-year", "modal-admin-exp-year", error_message_exp_year);
        return false;
    }

    if (Number(exp) < 3 ) {
        showFieldErrorMessage("modal-input-errMess-exp-year", "modal-admin-exp-year", error_message_exp_year);
        return false;
    };

    hideFieldErrorMessage("modal-input-errMess-exp-year", "modal-admin-exp-year");
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

// delete single admin
function showDeleteSingleAdminModal(adminId,firstName,lastName,namKN) {
    $('#deleteSingleAdminModal').modal('show');
    document.getElementById('delete-single-admin-confirm-mess').innerHTML = 'Bạn có chắc chắn muốn xóa thông tin về quản lý <span style="color:red;">'+ firstName + ' ' + lastName + ' có số năm kinh nghiệm là ' + namKN + '</span>?';
    document.getElementById('delete-single-admin-btn').onclick = function() { deleteSingleAdmin(adminId) };
}

function deleteSingleAdmin(adminId) {
    $.ajax({
        url: 'http://localhost:8080/api/final/v1/staffs/' + adminId,
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
            $('#deleteSingleAdminModal').modal('hide');
            buildAdminTable();
        }
    });
}

// delete multiple admin
function onChangeAdminCheckboxAll() {
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

function onChangeAdminCheckboxItem() {
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

function showDeleteMultipleAdminsModal() {
    $('#deleteMultipleAdminsModal').modal('show');

    // get checked
    var ids = [];
    var fullNames = [];
    var i = 0;
    while (true) {
        var checkboxItem = document.getElementById("checkbox-" + i);
        if (checkboxItem !== undefined && checkboxItem !== null) {
            if (checkboxItem.checked) {
                ids.push(admins[i].id);
                fullNames.push(admins[i].firstName + ' ' + admins[i].lastName);
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
        document.getElementById('delete-admins-confirm-mess').innerHTML = 'Choose at least one admin to delete!';
        document.getElementById('delete-multiple-admins-btn').style.display = 'none';
    } else {
        document.getElementById('delete-admins-confirm-mess').innerHTML = 'This action can not be undone. Delete <span id="user-admins-delete-message"></span>?';
        document.getElementById('user-admins-delete-message').innerHTML += '<span style="color: red;">' + message + '</span>';
        document.getElementById('delete-multiple-admins-btn').style.display = 'inline-block';
        document.getElementById('delete-multiple-admins-btn').onclick = function() { deleteMultipleAdmins(ids) };
    }
}

function deleteMultipleAdmins(adminIds) {
    $.ajax({
        url: 'http://localhost:8080/api/final/v1/staffs?ids=' + adminIds.toString(),
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
            $('#deleteMultipleAdminsModal').modal('hide');
            buildAdminTable();
        }
    });
}