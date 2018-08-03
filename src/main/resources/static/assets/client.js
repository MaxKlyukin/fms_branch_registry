let dbUpdateButton, searchCodeField, searchSubmitButton,
branchInfoElement, branchInfoCodeElement, branchInfoNameElement;

document.addEventListener("DOMContentLoaded", () => {
    dbUpdateButton = document.querySelector(".db-update-button");
    searchCodeField = document.querySelector(".search-code-field");
    searchSubmitButton = document.querySelector(".search-submit-button");
    branchInfoElement = document.querySelector(".branch-info");
    branchInfoCodeElement = document.querySelector(".branch-info-code");
    branchInfoNameElement = document.querySelector(".branch-info-name");

    dbUpdateButton.addEventListener("click", updateDB);
    searchSubmitButton.addEventListener("click", searchBranchByCode);
    searchCodeField.addEventListener("keyup", (event) => {
        if (event.keyCode === 13) {
            searchBranchByCode();
        }
    });
});

function searchBranchByCode() {
    const code = searchCodeField.value;

    makeRequest("GET", "/branches/" + code).then((result) => {
        hideBranchInfo();
        if (result.code == 200) {
            searchCodeField.value = "";
            showBranchInfo(result.response.data);
        } else if (result.code == 404) {
            alert("No branch was found. May be you need to update DB!");
        } else {
            alert("Error " + result.code + " occurred");
        }
    });
}

function updateDB() {
    makeRequest("POST", "/updates").then((result) => {
        if (result.code == 200) {
            alert("DB was updated!");
        } else if (result.code == 500 && result.response.error) {
            alert(result.response.error);
        } else {
            alert("Error " + result.code + " occurred");
        }
    });
}

function hideBranchInfo() {
    branchInfoElement.style.display = "none";
}

function showBranchInfo(branchInfo) {
    branchInfoElement.style.display = "block";

    branchInfoCodeElement.innerText = branchInfo.code;
    branchInfoNameElement.innerText = branchInfo.name;
}

function makeRequest(method, url) {
    var request = new XMLHttpRequest();
    request.open(method, url, true);
    request.setRequestHeader("Accept", "application/json");

    const promise = new Promise((resolve, reject) => {
        request.onreadystatechange = () => {
            if (request.readyState == 4) {
                resolve({
                  code: request.status,
                  response: request.responseText ? JSON.parse(request.responseText) : null
                });
            }
        };
    });

    request.send();

    return promise;
}