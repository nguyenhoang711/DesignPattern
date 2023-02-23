function viewTuyenPage() {
    $(".main").load("./tuyenbay/tuyenPage.html", function() {
        setupTuyenSearchEvent();
        setUpTuyenFilter();
        buildTuyenTable();
    });
}

function buildTuyenTable() {
    $('#tuyen-table tbody').empty();
    getListTuyens();
}

var tuyens = [];

// paging
var tuyenCurrentPage = 1;
var size = 5;
var tongSo = 0;

// sorting
var sortField = "id";
var isAsc = false;

// get List
function getListTuyens() {
    var url = "http://localhost:8080/api/final/v1/tuyens";

    // paging
    url += '?pageNumber=' + tuyenCurrentPage + '&size=' + size;

    // sorting
    url += "&sort=" + sortField + "," + (isAsc ? "asc" : "desc");

    // search
    var search = document.getElementById("search-tuyen-input").value;
    if (search) {
        url += "&search=" + search;
    }

    // filter
    var minPrice = document.getElementById("filter-min-price-select").value;

    if (minPrice && minPrice != "") {
        url += "&minPrice=";
        if (minPrice == "LOW")
            url += 2500000
        else if (minPrice == "MEDIUM")
            url += 6500000
        else url += 8000000
    }

    var maxPrice = document.getElementById("filter-max-price-select").value;

    if (maxPrice && maxPrice != "") {
        url += "&maxPrice=";
        if (maxPrice == "LOW")
            url += 4000000
        else if (maxPrice == "MEDIUM")
            url += 8000000
        else url += 10000000
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
            tuyens = data.content;
            fillTuyenToTable();
            tongSo = data.totalPages;
            fillTuyenPaging(data.numberOfElements, data.totalPages);
            createTuyenPagination(data.totalPages);
            fillTuyenSorting();
        },
        error(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });

}

function fillTuyenToTable() {
    tuyens.forEach(function(item, index) {
        $('#tuyen-table tbody').append(
            '<tr>' +
            '<td> ' +
            '<span class="tuyen-checkbox"> ' +
            '<input id="checkbox-' + index + '" type="checkbox" onClick="onChangeTuyenCheckboxItem()"/>' +
            '<label></label>' +
            '</span>' +
            '</td>' +
            '<td>' + item.fromCity + '</td>' +
            '<td>' + item.toCity + '</td>' +
            '<td>' + item.price + '</td>' +

            '<td class="td-actions"> ' +
            '<a href="#" data-toggle="tooltip" title="Edit" onclick="openUpdateTuyenModal(' + item.id + ', \'' + item.fromCity + '\', \'' + item.toCity + '\',' + item.price +')"><i class="fa-solid fa-pencil"></i></a>' +
            '<a href="#" data-toggle="tooltip" title="Devare" onclick="showDeleteSingleTuyenModal(' + item.id + ', \'' + item.fromCity + '\', \'' + item.toCity + '\')"><i class="fa-regular fa-trash-can"></i></a>' +
            '</td>' +
            '</tr>'
        );
    });
}

function createTuyenPagination(totalPages){
    $(".pagination").empty();
    $('.pagination').append(
        '<li class="page-item" id="above-tuyen"><a class="page-link" href="#" onclick="prevTuyenPage()" id="tuyen-prev-btn">Previous</a></li>' +
        '<li class="page-item"><a class="page-link" href="#" onclick="nextTuyenPage()" id="tuyen-next-btn">Next</a></li>'
    );

    var totalTuyens = totalPages;

    while(totalTuyens > 0){
        $('#above-tuyen').after(
            '<li class="page-item"><a class="page-link" href="#">' + totalTuyens + '</a></li>'
        );

        totalTuyens -= 1;
    }

    $('.page-link').click(function(){
        const elem = $(this).text();
        if(elem != 'Previous' && elem != 'Next'){
            changeTuyenPage(Number(elem));
        }
    });
}


// paging
function fillTuyenPaging(currentSize, totalPages) {
    // // prev
    // if (tuyenCurrentPage > 1) {
    //     document.getElementById("tuyen-previousPage-btn").disabled = false;
    // } else {
    //     document.getElementById("tuyen-previousPage-btn").disabled = true;
    // }

    // // next
    // if (tuyenCurrentPage < totalPages) {
    //     document.getElementById("tuyen-nextPage-btn").disabled = false;
    // } else {
    //     document.getElementById("tuyen-nextPage-btn").disabled = true;
    // }

    // text
    document.getElementById("tuyen-page-info").innerHTML = currentSize + (currentSize > 1 ? " records " : " record ") + tuyenCurrentPage + " of " + totalPages;
}

function prevTuyenPage() {
    if(tuyenCurrentPage == 1){
        changeTuyenPage(tuyenCurrentPage);
    }else
        changeTuyenPage(tuyenCurrentPage - 1);
}

function nextTuyenPage() {
    if(tuyenCurrentPage == tongSo){
        changeTuyenPage(tuyenCurrentPage);
    }else
        changeTuyenPage(tuyenCurrentPage + 1);
}

function changeTuyenPage(page) {
    tuyenCurrentPage = page;
    buildTuyenTable();
}

// Sorting
function fillTuyenSorting() {
    var sortTypeClazz = isAsc ? "fa-sort-up" : "fa-sort-down";
    var defaultSortType = "fa-sort";

    switch (sortField) {
        case 'fromCity':
            changeIconSort("fromCity-sort", sortTypeClazz);
            changeIconSort("toCity-sort", defaultSortType);
            changeIconSort("price-sort", defaultSortType);
            break;
        case 'toCity':
            changeIconSort("fromCity-sort", defaultSortType);
            changeIconSort("toCity-sort", sortTypeClazz);
            changeIconSort("price-sort", defaultSortType);
            break;
        case 'price':
            changeIconSort("fromCity-sort", defaultSortType);
            changeIconSort("toCity-sort", defaultSortType);
            changeIconSort("price-sort", sortTypeClazz);
            break;

            // sort by id
        default:
            changeIconSort("fromCity-sort", defaultSortType);
            changeIconSort("toCity-sort", defaultSortType);
            changeIconSort("price-sort", defaultSortType);
            break;
    }
}

function changeIconSort(id, sortTypeClazz) {
    document.getElementById(id).classList.remove("fa-sort", "fa-sort-up", "fa-sort-down");
    document.getElementById(id).classList.add(sortTypeClazz);
}

function changeTuyenSort(field) {
    if (field == sortField) {
        isAsc = !isAsc;
    } else {
        sortField = field;
        isAsc = true;
    }
    buildTuyenTable();
}

// search

function setupTuyenSearchEvent() {
    $("#search-tuyen-input").on("keyup", function(event) {
        // enter key code = 13
        if (event.keyCode === 13) {
            buildTuyenTable();
        }
    });
}

// filter
function filterTuyen() {
    buildTuyenTable();
}

function setUpTuyenFilter() {
    setupTuyenMinPrice();
    setupTuyenMaxPrice();
}

function setupTuyenMinPrice() {
    $("#filter-min-price-select").select2({
        placeholder: "Lựa chọn mức giá thấp nhất"
    });
}

function setupTuyenMaxPrice() {
    $("#filter-max-price-select").select2({
        placeholder: "Lựa chọn mức giá cao nhất"
    });
}

// Refresh Table
function refreshTuyenTable() {
    // refresh paging
    tuyenCurrentPage = 1;
    size = 5;

    // refresh sorting
    sortField = "id";
    isAsc = false;

    // refresh search
    document.getElementById("search-tuyen-input").value = "";

    // refresh filter
    $('#filter-min-price-select').val('').trigger('change');
    $('#filter-max-price-select').val('').trigger('change');

    // Get API
    buildTuyenTable();
}

function openTuyenModal() {
    $('#addAndUpdateTuyenModal').modal('show');
}

function hideTuyenModal() {
    $('#addAndUpdateTuyenModal').modal('hide');
}

// open create modal 
function openAddTuyenModal() {
    openTuyenModal();
    resetAddTuyenForm();
}

// open update modal
function openUpdateTuyenModal(tuyenId,fromCity,toCity,price) {
    openTuyenModal();
    resetUpdateTuyenForm(tuyenId,fromCity,toCity,price);
}

//update tuyen form
function resetUpdateTuyenForm(tuyenId,fromCity,toCity,price){
    // set title
    document.getElementById("addAndUpdateTuyen-modal-title").innerHTML = "Cập nhật tuyến bay";

    var idField = document.getElementById("tuyen-id");
    idField.value = tuyenId;


    // fill in all avaialble value
    var fromCityField = document.getElementById("modal-fromCity");
    fromCityField.value = fromCity;
    fromCityField.setAttribute("readOnly", "true");

    var toCityField = document.getElementById("modal-toCity");
    toCityField.value = toCity;
    toCityField.setAttribute("readOnly", "true");
    
    var priceField = document.getElementById("modal-price");
    priceField.value = price;


    // min price
    setupMinPriceSelectionInForm();

    // max price
    setupMaxPriceSelectionInForm();

    //reset error message
    resetTuyenModalErrMessage();
}

function resetAddTuyenForm() {
    // set title
    document.getElementById("addAndUpdateTuyen-modal-title").innerHTML = "Tạo mới tuyến bay";

    // Reset all input value
    document.getElementById("modal-fromCity").value = "";
    document.getElementById("modal-toCity").value = "";
    document.getElementById("modal-price").value = "";
    
    //remove readOnly attribute
    $('#modal-fromCity').removeAttr('readonly');
    $('#modal-toCity').removeAttr('readonly');

    // min price
    setupMinPriceSelectionInForm();

    // max price
    setupMaxPriceSelectionInForm();

    // Reset all error message
    resetTuyenModalErrMessage();
}



function resetTuyenModalErrMessage() {
    hideFieldErrorMessage("modal-input-errMess-from-city", "modal-fromCity");
    hideFieldErrorMessage("modal-input-errMess-to-city", "modal-toCity");
    hideFieldErrorMessage("modal-input-errMess-price", "modal-price");
}

function setupMinPriceSelectionInForm() {
    $("#modal-min-price-select").select2({
        placeholder: "Mức giá thấp nhất"
    });
}

function setupMaxPriceSelectionInForm() {
    $("#modal-max-price-select").select2({
        placeholder: "Mức giá cao nhất"
    });
}


// save
function saveTuyen() {
    var id = document.getElementById("tuyen-id").value;
    if (!id) {
        addTuyen();
    }
    else {
        updateTuyen(id);
    }
}

var error_message_cities = "Tuyến bay đã tồn tại!";
var error_message_not_blank = "Thông tin bắt buộc, không được để trống";
var error_message_price = "Giá phải là số nguyên dương và lớn hơn bằng 3 000 000, nhỏ hơn bằng 50 000 000";

function addTuyen() {
    var fromCity = document.getElementById("modal-fromCity").value;
    var toCity = document.getElementById("modal-toCity").value;
    var price = document.getElementById("modal-price").value;

    // validate
    var validCities = isValidCity(fromCity, toCity);
    var validPrice = isValidPrice(price);

    // format
    if (!validCities || !validPrice) {
        return;
    }
    // unique tuyen bay
    $.ajax({
        url: "http://localhost:8080/api/final/v1/tuyens/city/" + fromCity + '/' + toCity + "/exists",
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
            // data == true
            if(data){
                //show error message
                showFieldErrorMessage("modal-input-errMess-from-city","modal-fromCity", error_message_cities);
                showFieldErrorMessage("modal-input-errMess-to-city","modal-toCity", error_message_cities);
                return;
            }else{
                createTuyenViaAPI(fromCity, toCity, price);
                return;
            }
        }
        
    });
}

function updateTuyen(tuyenId){

    var price = document.getElementById("modal-price").value;
    var validPrice = isValidPrice(price);

    if(!validPrice){
        return;
    }

    var updatItem = {
        "price": price
    };
    //call api
    $.ajax({
        url: 'http://localhost:8080/api/final/v1/tuyens/' + tuyenId,
        type: 'PUT',
        data: JSON.stringify(updatItem), // body
        contentType: "application/json", // type of body (json, xml, text)
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(data, textStatus, xhr) {
            document.getElementById("tuyen-id").innerHTML = null;
            // success
            hideTuyenModal();
            showSuccessSnackBar("Update successfully");
            buildTuyenTable();
        },
        error(jqXHR, textStatus, errorThrown) {
            alert("Error when loading data");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });

}

function createTuyenViaAPI(fromCity, toCity, price) {

    // call api create department
    var newTuyen = {
        "fromCity": fromCity,
        "toCity": toCity,
        "price": price
    }

    $.ajax({
        url: 'http://localhost:8080/api/final/v1/tuyens',
        type: 'POST',
        data: JSON.stringify(newTuyen), // body
        contentType: "application/json", // type of body (json, xml, text)
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(data, textStatus, xhr) {
            // success
            hideTuyenModal();
            showSuccessSnackBar("Success! New tuyen bay created!");
            buildTuyenTable();
        },
        error(jqXHR, textStatus, errorThrown) {
            alert("Error when loading data");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

function isValidCity(fromCity, toCity) {
    var check = false;
    //not blank
    if (!fromCity || !toCity) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-from-city", "modal-fromCity", error_message_not_blank);
        showFieldErrorMessage("modal-input-errMess-to-city", "modal-toCity", error_message_not_blank);
        return false;
    }
    hideFieldErrorMessage("modal-input-errMess-from-city", "modal-fromCity");
    hideFieldErrorMessage("modal-input-errMess-to-city", "modal-toCity");
    return true;
}

function isValidPrice(price) {
    if (!price) {
        // show error message
        showFieldErrorMessage("modal-input-errMess-price", "modal-price", error_message_not_blank);
        return false;
    }
    price = Number(price);
    if(price < 3000000 || price > 50000000){
        //show error message
        showFieldErrorMessage("modal-input-errMess-price","modal-price",error_message_price);
        return false;
    }

    hideFieldErrorMessage("modal-input-errMess-price", "modal-price");
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


// delete single tuyen
function showDeleteSingleTuyenModal(tuyenId, fromCity, toCity) {
    $('#deleteSingleTuyenModal').modal('show');
    document.getElementById('delete-single-tuyen-confirm-mess').innerHTML = 'This action can not be undone. Delete tuyến bay từ <span style="color:red;">' + fromCity + ' tới ' + toCity + '</span>?';
    document.getElementById('delete-single-tuyen-btn').onclick = function() { deleteSingleTuyen(tuyenId) };
}

function deleteSingleTuyen(tuyenId) {
    $.ajax({
        url: 'http://localhost:8080/api/final/v1/tuyens/' + tuyenId,
        type: 'DELETE',
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(storage.getItem("USERNAME") + ":" + storage.getItem("PASSWORD")));
        },
        success: function(result,responseTxt,jqXHR) {
            // error
            if (result == undefined || result == null) {
                alert("Error when loading data");
                return;
            }
            // alert(jqXHR.textStatus);
            // success
            showSuccessSnackBar("Success! Tuyen deleted.");
            $('#deleteSingleTuyenModal').modal('hide');
            buildTuyenTable();
        }
    });
}

// delete multiple tuyen
function onChangeTuyenCheckboxAll() {
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

function onChangeTuyenCheckboxItem() {
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

function showDeleteMultipleTuyensModal() {
    $('#deleteMultipleTuyensModal').modal('show');

    // get checked
    var ids = [];
    var message = "";
    var i = 0;
    while (true) {
        var checkboxItem = document.getElementById("checkbox-" + i);
        if (checkboxItem !== undefined && checkboxItem !== null) {
            if (checkboxItem.checked) {
                ids.push(tuyens[i].id);
                message += '<br> -Tuyến bay từ ' + tuyens[i].fromCity + ' tới ' + tuyens[i].toCity;
            }
            i++;
        } else {
            break;
        }
    }

    if (!ids || ids.length == 0) {
        document.getElementById('delete-tuyens-confirm-mess').innerHTML = 'Choose at least one tuyen to delete!';
        document.getElementById('delete-multiple-tuyens-btn').style.display = 'none';
    } else {
        document.getElementById('delete-tuyens-confirm-mess').innerHTML = 'This action can not be undone. Delete <span id="user-tuyens-delete-message"></span>?';
        document.getElementById('user-tuyens-delete-message').innerHTML += '<span style="color: red;">' + message + '</span>';
        document.getElementById('delete-multiple-tuyens-btn').style.display = 'inline-block';
        document.getElementById('delete-multiple-tuyens-btn').onclick = function() { deleteMultipleTuyens(ids) };
    }
}

function deleteMultipleTuyens(tuyenIds) {
    $.ajax({
        url: 'http://localhost:8080/api/final/v1/tuyens?ids=' + tuyenIds.toString(),
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
            showSuccessSnackBar("Success! Tuyen bay deleted.");
            $('#deleteMultipleTuyensModal').modal('hide');
            buildTuyenTable();
        }
    });
}