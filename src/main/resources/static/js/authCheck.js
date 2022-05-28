let isLogged = false;
let needReLogin = true;



document.cookie.split(";").forEach(e => {
    if(e.startsWith("access_token")){
        isLogged = true
        needReLogin = false
    }
    else if(e.startsWith("refresh_token")) {
        if (needReLogin) {
            $.ajax({
                url: "/api/auth/re-login",
                type: "POST",
                contentType: "application/json",
                async: false,
                data: JSON.stringify({
                    refresh_token: e.split("=")[1]
                }),
                success: function (data) {
                    addLoginCookies(data)
                }
            })
            isLogged = true
        }
    }
})
if(!isLogged){
    window.location = "/auth"
}