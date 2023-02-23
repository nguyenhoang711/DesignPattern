function viewHomepage() {
    $(".main").load("./home/homePage.html", function () {
        document.getElementById("line-2").innerHTML = 'Hello ' + storage.getItem("LAST_NAME") + "!";
    });
}