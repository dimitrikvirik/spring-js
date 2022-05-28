$(document).ready(function () {
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
            $("#about").html(data.about.replace(/\n/g, "<br>"))

            console.log("Success")
        },
        error: function (data) {
            console.log("error")
        }
    })
    let profileEditButton = $("#profileEditButton")
    if (getCurrentUsername() !== username) {
        profileEditButton.hide()
    }
    let allInputs = $("input")

    function clearErrors() {
        allInputs.removeClass("is-invalid")
    }

    function addError(field, message) {
        $("#" + field + "Error").text(message)
        $("#" + field).addClass("is-invalid")
    }

    allInputs.on("input", function () {
        clearErrors()
    })


    profileEditButton.click(function () {
        if (profileEditButton.text() !== "Save") {
            profileEditButton.text("Save")
            $(".editProfile").show()
            $("#profileNameInput").val($("#profileName").text())
            $("#aboutInput").val($("#about").html().replace(/<br>/g, "\n"))
            $(".showProfile").hide()
        } else {
            let anyError = false
            let profileFields = $("#profileNameInput").val().split(" ")
            if (profileFields.length !== 2) {
                addError("profileNameInput", "Wrong format")
                anyError = true
            }
            let about = $("#aboutInput").val()
            if (about.length > 5000) {
                addError("aboutInput", "Text too long")
                anyError = true
            }
            if(!anyError){
                $.ajax({
                    url: "/api/user/" + username,
                    type: "PUT",
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader('Authorization', 'Bearer ' + getAuthToken());
                    },
                    contentType: "application/json",
                    async: false,
                    data: JSON.stringify({
                        firstname: profileFields[0],
                        lastname: profileFields[1],
                        about: about
                    }),
                    success: function (data) {
                        window.location.reload()
                    },
                    error: function (data) {
                        if(data.status === 400){
                            data.responseJSON.errors.forEach(function (error){
                                if(error.field === "Firstname" || error.field === "Lastname"){
                                    addError("profileNameInput", error.defaultMessage)
                                }
                                if(error.field === "About"){
                                    addError("aboutInput", error.defaultMessage)
                                }
                            })
                        }
                    }
                })
            }
        }
    })

})


