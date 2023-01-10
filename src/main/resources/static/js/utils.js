const row_style_visible = "table-row";
const row_style_hidden = "none";
var defaultUrl = '/api/search?';
var tempUrl = '';
var filterNames = ['#searchFullName', '#searchVacation', '#searchHospital', '#searchDateOfBirth', '#searchPhoneNumber',
'#searchBattalion', '#searchFullTimePosition', '#searchPersonalIdNumber', '#searchDateOfArrival', '#searchEnrollmentOrderNumber',
'#searchOriginBrigadeArrival', '#searchInternalOrder', '#searchMilitaryRankName'];


window.onload = () => {

}

function getTableData() {
    let rows = document.querySelectorAll('tbody tr[style*="display: table-row"]');
    let data = Array.from(rows).map(function (tr) {
        return {
            fullName: tr.querySelectorAll('td:nth-child(1)')[0].textContent,
            vacation: tr.querySelectorAll('td:nth-child(2)')[0].textContent,
            hospital: tr.querySelectorAll('td:nth-child(3)')[0].textContent,
            dateOfBirth: tr.querySelectorAll('td:nth-child(4)')[0].textContent,
            phoneNumber: tr.querySelectorAll('td:nth-child(5)')[0].textContent,
            battalion: tr.querySelectorAll('td:nth-child(6)')[0].textContent,
            fullTimePosition: tr.querySelectorAll('td:nth-child(7)')[0].textContent,
            militaryRankName: tr.querySelectorAll('td:nth-child(8)')[0].textContent,
            militaryMedicalCommission: tr.querySelectorAll('td:nth-child(9)')[0].textContent,
            personalIdNumber: tr.querySelectorAll('td:nth-child(10)')[0].textContent,
            dateOfArrival: tr.querySelectorAll('td:nth-child(11)')[0].textContent,
            enrollmentOrderNumber: tr.querySelectorAll('td:nth-child(12)')[0].textContent,
            originBrigadeArrival: tr.querySelectorAll('td:nth-child(13)')[0].textContent,
            internalOrder: tr.querySelectorAll('td:nth-child(14)')[0].textContent
        };
    });
    console.log(data);
}

function clearAllFilters() {
    //clear text, date, number inputs
    let allFilters = document.querySelectorAll(".table-filter");
    allFilters.forEach(filter => {
        filter.value = "";
    });
    //clear select inputs
    $('#searchMilitaryMedicalCommission').prop('selectedIndex', 0);
    performSearch();
}

function performSearch() {
	var url = buildUrlForTextInputs();
    if ($('#searchMilitaryMedicalCommission').find(":selected").val() != '---') {
        url = url + '&militaryMedicalCommission=' + encodeURIComponent($('#searchMilitaryMedicalCommission').find(":selected").val());
    }
	this.tempUrl = url;
	$("#resultsBlock").load(url);
}

function loadFragmentPaginationUrl(pageNum) {
    var targetPaginationUrl = this.tempUrl;
    targetPaginationUrl = !targetPaginationUrl.includes('?')
                ? targetPaginationUrl + '?page=' + pageNum
                : targetPaginationUrl + '&page=' + pageNum;
    $("#resultsBlock").load(targetPaginationUrl);
}

function showHideFilters() {
    var tableFiltersDiv = document.getElementById('filtersPanel');
    tableFiltersDiv = tableFiltersDiv.style.display === 'none' ? tableFiltersDiv.style.display = 'block' : tableFiltersDiv.style.display = 'none';
}

function buildUrlForTextInputs() {
    var url = defaultUrl;
    for (var i = 0; i < filterNames.length; i++) {
        if ($(filterNames[i]).val() != '') {
            url = url + '&' + filterNames[i].slice(7).at(0).toLowerCase() + filterNames[i].slice(8) +
            '=' + encodeURIComponent($(filterNames[i]).val());
        }
    }
    return url;
}