function switchToSignUp() {
    document.getElementById("tab-register").click()
}
function switchToSignIn() {
    document.getElementById("tab-login").click()
}

let allInputs = $("input")
function clearErrors(){
    allInputs.removeClass("is-invalid")
}
function addError(field, message){
    $("#" + field[0].id + "Error").text(message)
    $("#" + field[0].id).addClass("is-invalid")
}



allInputs.on("input",function (){
    clearErrors()
})

$("#singUpButton").click(function () {

    clearErrors()
    let username = $("#username")
    let firstName = $("#registerFirstname")
    let lastName = $("#registerLastname")
    let email = $("#registerEmail")
    let password = $("#registerPassword")
    let repeatPassword = $("#registerRepeatPassword")
    let registerCheck = $("#registerCheck")

    let isValid = true
    if(password.val() !== repeatPassword.val()){
        addError(password, "Passwords do not match")
        addError(repeatPassword, "Passwords do not match")
        isValid = false
    }
    if(registerCheck.is(":checked") === false) {
        addError(registerCheck, "You must agree to the terms and conditions")
        isValid = false
    }
    if(isValid) {
        $.ajax({
            url: "/api/auth/register",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            async: false,
            data: {
                username: username.val(),
                firstName: firstName.val(),
                lastName: lastName.val(),
                email: email.val(),
                password: password.val()
            },
            success: function (data) {
                switchToSignIn()
            },
            error: function (data) {

            }

        })
    }
})

