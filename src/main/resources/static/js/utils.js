const row_style_visible = "table-row";
const row_style_hidden = "none";
var defaultUrl = '/api/search?';
var tempUrl = '';


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
    let allFilters = document.querySelectorAll(".table-filter");
    allFilters.forEach(filter => {
        filter.value = "";
    });
    performSearch();
}

function performSearch() {
	var url = defaultUrl;
	if ($('#searchFullName').val() != '') {
		url = url + '&fullName=' + encodeURIComponent($('#searchFullName').val());
	}
	if ($('#searchVacation').val() != '') {
    	url = url + '&vacation=' + encodeURIComponent($('#searchVacation').val());
    }
    if ($('#searchHospital').val() != '') {
        url = url + '&hospital=' + encodeURIComponent($('#searchHospital').val());
    }
    if ($('#searchDateOfBirth').val() != '') {
        url = url + '&dateOfBirth=' + encodeURIComponent($('#searchDateOfBirth').val());
    }
    if ($('#searchPhoneNumber').val() != '') {
        url = url + '&phoneNumber=' + encodeURIComponent($('#searchPhoneNumber').val());
    }
    if ($('#searchBattalion').val() != '') {
        url = url + '&battalion=' + encodeURIComponent($('#searchBattalion').val());
    }
    if ($('#searchFullTimePosition').val() != '') {
        url = url + '&fullTimePosition=' + encodeURIComponent($('#searchFullTimePosition').val());
    }
/*    if ($('#searchMilitaryRankName').val() != '') {
        url = url + '&militaryRankName=' + encodeURIComponent($('#searchMilitaryRankName').val());
    }
    if ($('#searchMilitaryMedicalCommission').val() != '') {
        url = url + '&militaryMedicalCommission=' + encodeURIComponent($('#searchMilitaryMedicalCommission').val());
    }*/
    if ($('#searchPersonalIdNumber').val() != '') {
        url = url + '&personalIdNumber=' + encodeURIComponent($('#searchPersonalIdNumber').val());
    }
    if ($('#searchDateOfArrival').val() != '') {
        url = url + '&dateOfArrival=' + encodeURIComponent($('#searchDateOfArrival').val());
    }
    if ($('#searchEnrollmentOrderNumber').val() != '') {
        url = url + '&enrollmentOrderNumber=' + encodeURIComponent($('#searchEnrollmentOrderNumber').val());
    }
    if ($('#searchOriginBrigadeArrival').val() != '') {
        url = url + '&originBrigadeArrival=' + encodeURIComponent($('#searchOriginBrigadeArrival').val());
    }
    if ($('#searchInternalOrder').val() != '') {
        url = url + '&internalOrder=' + encodeURIComponent($('#searchInternalOrder').val());
    }
	this.tempUrl = url;
	console.log(url);
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
