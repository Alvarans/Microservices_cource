var tableBody = document.querySelector("#reviews-table tbody");

// Функция для создания строки таблицы с данными пользователя

async function takeUser(userId){
    var response = await fetch("http://localhost:8084/api/userservice/user/takeuserbyid?id=" + userId)
    return response.json();
}

async function takeFilm(filmId){
    var response = await fetch("http://localhost:8082/api/filmservice/film/takefilm?id=" + filmId)
    return response.json();
}


function createRow(review) {
    var row = document.createElement("tr");
    var idCell = document.createElement("td")
    var titleCell = document.createElement("td");
    var filmCell = document.createElement("td");
    var authorCell = document.createElement("td");
    var organizationCell = document.createElement("td");
    var textCell = document.createElement("td");
    var filmRateCell = document.createElement("td");
    var reviewRateCell = document.createElement("td");

    var userId = review.reviewId;
    idCell.textContent = userId;
    titleCell.textContent = review.reviewTitle;
    takeFilm(review.film).then(response => (filmCell.textContent = response.filmTitle))
    takeUser(review.user).then(response => (authorCell.textContent = response.userNickname))
    organizationCell.textContent = review.authorOrganization;
    textCell.textContent = review.reviewText;
    filmRateCell.textContent = review.filmRate;
    reviewRateCell.textContent = review.reviewRate;

    // if (user.userRole === "reviewer") {
    //     console.log(userId);
    //     takeRate(userId)
    //         .then(jsonData => (revCell.textContent = jsonData));
    // } else {
    //     revCell.textContent = user.reviewerRating;
    // }

    row.appendChild(idCell);
    row.appendChild(titleCell);
    row.appendChild(filmCell);
    row.appendChild(authorCell);
    row.appendChild(organizationCell);
    row.appendChild(textCell);
    row.appendChild(filmRateCell);
    row.appendChild(reviewRateCell);

    return row;
}

// Функция для получения списка пользователей и создания таблицы
async function getReviews() {
    try {
        var response = await fetch("http://localhost:8083/api/reviewservice/review/getreviews");
        var reviews = await response.json();

        reviews.forEach(function(review) {

            var row = createRow(review);
            tableBody.appendChild(row);
        });
    } catch(error) {
        console.error("Возникла ошибка при получении пользователей:", error);
    }
}

getReviews();