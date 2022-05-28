let username = document.location.href.split("/")[4]


$.ajax({
    url: "/api/user/" + username,
    type: "GET",
    beforeSend: function (xhr) {
        xhr.setRequestHeader('Authorization', 'Bearer ' + getAuthToken());
    },
    contentType: "application/json",
    async: false,
    success: function (data) {

        $("#profileName").text(data.firstname + " " + data.lastname)

        console.log("Success")
    },
    error: function (data) {
        console.log("error")
    }
})
let profileEditButton = $("#profileEditButton")
if(getCurrentUsername() !== username){
    profileEditButton.hide()
}

profileEditButton.click(function () {
    if(profileEditButton.text() !== "Save") {
        let profileName = $("#profileName").text()
        profileEditButton.text("Save")
        $("#profileNameBox").html("<div class=\"form-outline form-white\">\n" +
            "        <input value=\"" + profileName + "\"  type=\"text\" id=\"profileName\" class=\"form-control active\"/>\n" +
            "        <label class=\"form-label\" for=\"formWhite\">Firstname And Lastname</label>\n" +
            "    </div>")

        $("#aboutBox").html("<textarea class=\"form-control\" id=\"aboutUs\" rows=\"4\"></textarea>")
    }
    else{

    }
})



