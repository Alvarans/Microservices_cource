var tableBody = document.querySelector("#users-table tbody");

// Функция для создания строки таблицы с данными пользователя

async function takeRate(userId){
    console.log("I'm here with " + userId);
    var response = await fetch("http://localhost:8083/api/reviewservice/review/takereviewerrate?id=" + userId)
    return response.text();
}


function createRow(user) {
    var row = document.createElement("tr");
    var idCell = document.createElement("td")
    var nicknameCell = document.createElement("td");
    var emailCell = document.createElement("td");
    var roleCell = document.createElement("td");
    var rateCell = document.createElement("td");
    var revCell = document.createElement("td");


    var userId = user.userId;
    idCell.textContent = userId;
    nicknameCell.textContent = user.userNickname;
    emailCell.textContent = user.userEmail;
    roleCell.textContent = user.userRole;
    rateCell.textContent = user.userRating;


    if (user.userRole === "reviewer") {
        console.log(userId);
        takeRate(userId)
            .then(jsonData => (revCell.textContent = jsonData));
    } else {
        revCell.textContent = user.reviewerRating;
    }

    row.appendChild(idCell);
    row.appendChild(nicknameCell);
    row.appendChild(emailCell);
    row.appendChild(roleCell);
    row.appendChild(rateCell);
    row.appendChild(revCell);

    return row;
}

// Функция для получения списка пользователей и создания таблицы
async function getUsers() {
    try {
        var response = await fetch("http://localhost:8084/api/userservice/user/getusers");
        var users = await response.json();

        users.forEach(function(user) {

            var row = createRow(user);
            tableBody.appendChild(row);
        });
    } catch(error) {
        console.error("Возникла ошибка при получении пользователей:", error);
    }
}

getUsers();