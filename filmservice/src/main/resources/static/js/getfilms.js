var tableBody = document.querySelector("#films-table tbody");

// Функция для создания строки таблицы с данными пользователя

async function takeUserRate(filmId){
    console.log("I'm here in user rate with " + filmId);
    var response = await fetch("http://localhost:8081/api/rateservice/rating/filmrate?id=" + filmId)
    return response.text();
}

async function takeReviewRate(filmId){
    console.log("I'm here in review rate with " + filmId);
    var response = await fetch("http://localhost:8083/api/reviewservice/review/takefilmreviewrate?id=" + filmId);
    return response.text();
}


function createRow(film) {
    var row = document.createElement("tr");
    var idCell = document.createElement("td")
    var titleCell = document.createElement("td");
    var releaseCell = document.createElement("td");
    var directorCell = document.createElement("td");
    var timingCell = document.createElement("td");
    var annotationCell = document.createElement("td");
    var reviewCell = document.createElement("td")
    var userCell = document.createElement("td");

    var filmId = film.filmId;
    idCell.textContent = filmId;
    titleCell.textContent = film.filmTitle;
    releaseCell.textContent = film.releaseDate;
    directorCell.textContent = film.director;
    timingCell.textContent = film.filmTiming;
    annotationCell.textContent = film.annotation;
    takeReviewRate(filmId).then(data => (reviewCell.textContent = data))
    takeUserRate(filmId).then(data => (userCell.textContent = data));

    row.appendChild(idCell);
    row.appendChild(titleCell);
    row.appendChild(releaseCell);
    row.appendChild(directorCell);
    row.appendChild(timingCell);
    row.appendChild(annotationCell);
    row.appendChild(reviewCell);
    row.appendChild(userCell);

    return row;
}

// Функция для получения списка пользователей и создания таблицы
async function getFilms() {
    try {
        var response = await fetch("http://localhost:8082/api/filmservice/film/takefilms");
        var films = await response.json();

        films.forEach(function(film) {

            var row = createRow(film);
            tableBody.appendChild(row);
        });
    } catch(error) {
        console.error("Возникла ошибка при получении пользователей:", error);
    }
}

getFilms();