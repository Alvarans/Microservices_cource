var myform = document.getElementById("myform");
myform.addEventListener("submit", handleSubmit);
var value = "";

function handleSubmit(event) {
    event.preventDefault();
    var myform =    document.getElementById("myform");
    var formData = new FormData(myform);
    console.log("I'm a " + formData.get("login") +".");
    console.log("I'm here!");
    getUser(formData.get("login")).then(response => (value = response));
    console.log("User value" + value)

}
async function getUser(textLogin) {
    try {
        var response = await fetch("http://localhost:8084/api/userservice/user/takeuserbynickname?nickname=" + textLogin);
        var user = await response.json();
        if (!(textLogin === user.userNickname)) {
            alert(`Такого пользователя нет`);
        } else {
            document.location.href = "http://localhost:8084/index.html";
        }
        console.log("This is " + user.userNickname + ".His email " + user.userEmail);
    } catch(error) {
        console.error("Возникла ошибка при получении пользователя:", error);
    }
}