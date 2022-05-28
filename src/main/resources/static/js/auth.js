function switchToSignUp() {
    document.getElementById("tab-register").click()
}
function switchToSignIn() {
    document.getElementById("tab-login").click()
}
let allInputs = $("input")
$("#singUpButton").click(function () {
    clearErrors()
    let username = $("#registerUsername")
    let firstName = $("#registerFirstname")
    let lastName = $("#registerLastname")
    let email = $("#registerEmail")
    let password = $("#registerPassword")
    let repeatPassword = $("#registerRepeatPassword")
    let registerCheck = $("#registerCheck")

    let isValid = true
    if(password.val() !== repeatPassword.val()){
        addError(password[0].id, "Passwords do not match")
        addError(repeatPassword[0].id, "Passwords do not match")
        isValid = false
    }
    if(registerCheck.is(":checked") === false) {
        addError(registerCheck[0].id, "You must agree to the terms and conditions")
        isValid = false
    }
    if(isValid) {
        $.ajax({
            url: "/api/auth/register",
            type: "POST",
            contentType: "application/json",
            async: false,
            data: JSON.stringify({
                username: username.val(),
                firstname: firstName.val(),
                lastname: lastName.val(),
                email: email.val(),
                password: password.val()
            }),
            error: function (data) {
                if(data.status === 400){
                    data.responseJSON.errors.forEach(function (error) {
                       addError("register" +  error.field, error.defaultMessage)
                   })
                }
            },
            success: function (data){
                switchToSignIn()
            }

        })
    }
})

function clearErrors(){
    allInputs.removeClass("is-invalid")
}
function addError(field, message){
    $("#" + field + "Error").text(message)
    $("#" + field).addClass("is-invalid")
}
allInputs.on("input",function (){
    clearErrors()
})

$("#signInButton").click(function (){
    clearErrors()
    let username = $("#loginUsername")
    let password = $("#loginPassword")
    let rememberMe = $("#loginCheck").is(":checked")

    $.ajax({
        url: "/api/auth/login",
        type: "POST",
        contentType: "application/json",
        async: false,
        data: JSON.stringify({
            username: username.val(),
            password: password.val(),
            rememberMe: rememberMe
        }),
        error: function (data){
            if(data.status === 400){
                data.responseJSON.errors.forEach(function (error){
                    addError("login" + error.field, error.defaultMessage)
                })
            }
            else if(data.status === 401){
                addError("loginUsername", data.responseJSON.message)
                addError("loginPassword", data.responseJSON.message)
            }
        },
      success: function (data){
          addLoginCookies(data)
          document.cookie.split(";").forEach(e => {
              if(e.startsWith("access_token")){
                  document.location = "/user/" +    parseJwt(e.split("=")[1]).preferred_username
              }
          })
      }
    })


})

