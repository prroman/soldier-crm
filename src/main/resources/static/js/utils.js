const row_style_visible = "table-row";
const row_style_hidden = "none";

window.onload = () => {
    this.getUniqueValuesFromColumn();
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

function getUniqueValuesFromColumn() {
    let unique_col_values_dict = {};
    let allFilters = document.querySelectorAll(".table-filter");
    allFilters.forEach(filter => {
        let col_index = filter.parentElement.getAttribute("col-index");
        let rows = document.querySelectorAll("#soldiers-table > tbody > tr");
        rows.forEach(row => {
            let cell_value = row.querySelector("td:nth-child(" + col_index + ")").innerHTML;
            if (col_index in unique_col_values_dict) {
                if (!unique_col_values_dict[col_index].includes(cell_value)) {
                    unique_col_values_dict[col_index].push(cell_value);
                }
            } else {
                unique_col_values_dict[col_index] = new Array(cell_value);
            }
        })
    });
    this.updateSelectOptions(unique_col_values_dict);
}

function updateSelectOptions(unique_col_values_dict) {
    let allFilters = document.querySelectorAll(".table-filter");
    allFilters.forEach(filter => {
        let col_index = filter.parentElement.getAttribute('col-index');
        unique_col_values_dict[col_index].forEach(i => {
            filter.innerHTML = filter.innerHTML + `\n<option value="${i}">${i}</option>`;
        });
    });
}

function filter_rows() {
    let allFilters = document.querySelectorAll(".table-filter");
    let filter_value_dict = {};
    allFilters.forEach(filter => {
        let col_index = filter.parentElement.getAttribute('col-index');
        let value = filter.value.toLowerCase();
        if (value !== "all") {
            filter_value_dict[col_index] = value;
        }
    });
    let col_cell_value_dict = {};
    let rows = document.querySelectorAll("#soldiers-table tbody tr");
    rows.forEach(row => {
        let display_row = true;
        allFilters.forEach(filter => {
            let col_index = filter.parentElement.getAttribute('col-index');
            col_cell_value_dict[col_index] = row.querySelector("td:nth-child(" + col_index + ")").innerHTML;
        });
        for (let col_i in filter_value_dict) {
            let filter_value = filter_value_dict[col_i];
            let row_cell_value = col_cell_value_dict[col_i].toLowerCase();
            if (!row_cell_value.includes(filter_value) && filter_value !== "all") {
                display_row = false;
                break;
            }
        }
        row.style.display = display_row === true ? row_style_visible : row_style_hidden;
    });
}

function clearAllFilters() {
    let allFilters = document.querySelectorAll(".table-filter");
    allFilters.forEach(filter => {
        filter.value = "";
    });
    filter_rows();
}