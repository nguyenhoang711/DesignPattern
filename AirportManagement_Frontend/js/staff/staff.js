function viewStaffPage() {
    $(".main").load("./staff/staffPage.html", function() {
        setupStaffSearchEvent();
        setupStaffFilter();
        buildStaffTable();
        
    });
}

function buildStaffTable() {
    $('#staff-table tbody').empty();
    getListStaffs();
}

var staffs = [];

// paging
var staffCurrentPage = 1;
var size = 5;
var totalPage = 0;

// sorting
var sortField = "id";
var isAsc = false;

// get List
function getListStaffs() {
    var url = "http://localhost:8080/api/final/v1/staffs";

    // paging
    url += '?pageNumber=' + staffCurrentPage + '&size=' + size;

    // sorting
    url += "&sort=" + sortField + "," + (isAsc ? "asc" : "desc");

    // search
    var search = document.getElementById("search-staff-input").value;
    if (search) {
        url += "&search=" + search;
    }

    // filter
    var role = document.getElementById("filter-staff-role-select").value;
    if (role && role != "All Roles") {
        url += "&role=" + role;
    }

    var gender = document.getElementById("filter-staff-gender-select").value;
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
            staffs = data.content;
            fillStaffToTable();
            totalPage = data.totalPages;
            fillStaffPaging(data.numberOfElements, data.totalPages);
            createPagination(data.totalPages);
            fillStaffSorting();
        },
        error(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });

}

function fillStaffToTable() {
    staffs.forEach(function(item, index) {
        $('#staff-table tbody').append(
            '<tr>' +
            '<td> ' +
            '<span class="staff-checkbox"> ' +
            '<input id="checkbox-' + index + '" type="checkbox" onClick="onChangeStaffCheckboxItem()"/>' +
            '<label></label>' +
            '</span>' +
            '</td>' +
            '<td>' + item.username + '</td>' +
            '<td>' + item.fullName + '</td>' +
            '<td>' + item.gender + '</td>' +
            '<td>' + item.role + '</td>' +

            '<td class="td-actions"> ' +
            '<a href="#" data-toggle="tooltip" title="Devare" onclick="showDeleteSingleStaffModal(' + item.id + ', \'' + item.fullName + '\')"><i class="fa-regular fa-trash-can"></i></a>' +
            '</td>' +
            '</tr>'
        );
    });
}

//tao thanh danh trang
function createPagination(totalPages){
    $(".pagination").empty();
    $('.pagination').append(
        '<li class="page-item" id="above-staff"><a class="page-link" href="#" onclick="prevStaffPage()" id="staff-prev-btn">Previous</a></li>' +
        '<li class="page-item"><a class="page-link" href="#" onclick="nextStaffPage()" id="staff-next-btn">Next</a></li>'
    )

    var total = totalPages;

    while(total > 0){
        $('#above-staff').after(
            '<li class="page-item"><a class="page-link" href="#">' + total + '</a></li>'
        );

        total -= 1;
    }

    $('.page-link').click(function(){
        const bien = $(this).text();
        if(bien != 'Previous' && bien != 'Next'){
            changeStaffPage(Number(bien));
        }
    });
}

function prevStaffPage() {
    if(staffCurrentPage == 1){
        changeStaffPage(staffCurrentPage);
    }else
        changeStaffPage(staffCurrentPage - 1);
}

function nextStaffPage() {
    if(staffCurrentPage == totalPage){
        changeStaffPage(staffCurrentPage);
    }else
        changeStaffPage(staffCurrentPage + 1);
}

function changeStaffPage(page) {
    staffCurrentPage = page;
    buildStaffTable();
}

// paging
function fillStaffPaging(currentSize, totalPages) {
    // // prev
    // if (staffCurrentPage > 1) {
    //     $('#above-staff').removeAttr("disabled");
    // } else {
    //     $('#above-staff').attr("disabled");
    // }
    
    // // next
    // if (staffCurrentPage < totalPages) {
    //     document.getElementById("staff-nextPage-btn").disabled = false;
    // } else {
    //     document.getElementById("staff-nextPage-btn").disabled = true;
    // }
    
    // text
    document.getElementById("staff-page-info").innerHTML = currentSize + (currentSize > 1 ? " records " : " record ") + staffCurrentPage + " of " + totalPages;
}


// Sorting
function fillStaffSorting() {
    var sortTypeClazz = isAsc ? "fa-sort-up" : "fa-sort-down";
    var defaultSortType = "fa-sort";

    switch (sortField) {
        case 'username':
            changeIconSort("username-sort", sortTypeClazz);
            changeIconSort("fullname-sort", defaultSortType);
            changeIconSort("gender-sort", defaultSortType);
            changeIconSort('role-sort', defaultSortType);
            break;
        case 'fullName':
            changeIconSort("username-sort", defaultSortType);
            changeIconSort("fullname-sort", sortTypeClazz);
            changeIconSort("gender-sort", defaultSortType);
            changeIconSort('role-sort', defaultSortType);
            break;
        case 'gender':
            changeIconSort("username-sort", defaultSortType);
            changeIconSort("fullname-sort", defaultSortType);
            changeIconSort("gender-sort", sortTypeClazz);
            changeIconSort('role-sort', defaultSortType);
            break;
        case 'role':
            changeIconSort("username-sort", defaultSortType);
            changeIconSort("fullname-sort", defaultSortType);
            changeIconSort("gender-sort", defaultSortType);
            changeIconSort('role-sort', sortTypeClazz);
            break;
            // sort by id
        default:
            changeIconSort("username-sort", defaultSortType);
            changeIconSort("fullname-sort", defaultSortType);
            changeIconSort("gender-sort", defaultSortType);
            changeIconSort('role-sort', defaultSortType);
            break;
    }
}

function changeIconSort(id, sortTypeClazz) {
    document.getElementById(id).classList.remove("fa-sort", "fa-sort-up", "fa-sort-down");
    document.getElementById(id).classList.add(sortTypeClazz);
}

function changeStaffSort(field) {
    if (field == sortField) {
        isAsc = !isAsc;
    } else {
        sortField = field;
        isAsc = true;
    }
    buildStaffTable();
}

// search

function setupStaffSearchEvent() {
    $("#search-staff-input").on("keyup", function(event) {
        // enter key code = 13
        if (event.keyCode === 13) {
            buildStaffTable();
        }
    });
}


// filter
function filterStaff() {
    buildStaffTable();
}

function setupStaffFilter() {
    setupStaffRole();
    setupStaffGender();
}

function setupStaffRole() {
    $("#filter-staff-role-select").select2({
        placeholder: "Select a role"
    });
}
function setupStaffGender() {
    $("#filter-staff-gender-select").select2({
        placeholder: "Select a gender"
    });
}


// Refresh Table
function refreshStaffTable() {
    // refresh paging
    staffCurrentPage = 1;
    size = 5;

    // refresh sorting
    sortField = "id";
    isAsc = false;

    // refresh search
    document.getElementById("search-staff-input").value = "";

    // refresh filter
    // $("#filter-department-select").empty();
    $('#filter-staff-role-select').val('').trigger('change');
    $('#filter-staff-gender-select').val('').trigger('change');

    // Get API
    buildStaffTable();
}


// delete single Staff
function showDeleteSingleStaffModal(staffId, fullName) {
    $('#deleteSingleStaffModal').modal('show');
    document.getElementById('delete-single-staff-confirm-mess').innerHTML = 'This action can not be undone. Delete <span style="color:red;">' + staffId + "    " + fullName + '</span>?';
    document.getElementById('delete-single-staff-btn').onclick = function() { deleteSingleStaff(staffId) };
}

function deleteSingleStaff(staffId) {
    $.ajax({
        url: 'http://localhost:8080/api/final/v1/staffs/' + staffId,
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
            showSuccessSnackBar("Success! Staff deleted.");
            $('#deleteSingleStaffModal').modal('hide');
            buildStaffTable();
        }
    });
}

// delete multiple Staff
function onChangeStaffCheckboxAll() {
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

function onChangeStaffCheckboxItem() {
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

function showDeleteMultipleStaffsModal() {
    $('#deleteMultiplestaffsModal').modal('show');

    // get checked
    var ids = [];
    var fullnames = [];
    var i = 0;
    while (true) {
        var checkboxItem = document.getElementById("checkbox-" + i);
        if (checkboxItem !== undefined && checkboxItem !== null) {
            if (checkboxItem.checked){
                ids.push(staffs[i].id);
                fullnames.push(staffs[i].fullName);
            }
            i++;
        } else {
            break;
        }
    }

    if (!ids || ids.length == 0) {
        document.getElementById('delete-staffs-confirm-mess').innerHTML = 'Choose at least one staff to delete!';
        document.getElementById('delete-multiple-staffs-btn').style.display = 'none';
    } else {
        document.getElementById('delete-staffs-confirm-mess').innerHTML = 'This action can not be undone. Delete <span id="user-fullName-delete-message"></span>?';
        document.getElementById('user-fullName-delete-message').innerHTML += '<span style="color: red;">' + fullnames.join(", ") + '</span> (<span style="color: red;">' + ids.length + '</span> ' + (ids.length == 1 ? 'staff' : 'staffs') + ')';
        document.getElementById('delete-multiple-staffs-btn').style.display = 'inline-block';
        document.getElementById('delete-multiple-staffs-btn').onclick = function() { deleteMultipleStaffs(ids) };
    }
}

function deleteMultipleStaffs(staffIds) {
    $.ajax({
        url: 'http://localhost:8080/api/final/v1/staffs?ids=' + staffIds.toString(),
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
            showSuccessSnackBar("Success! Staff deleted.");
            $('#deleteMultiplestaffsModal').modal('hide');
            buildStaffTable();
        }
    });
}