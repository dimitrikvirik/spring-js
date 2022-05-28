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
                    let response = JSON.parse(data)
                    let expiresIn = new Date();
                    expiresIn.setSeconds(expiresIn.getSeconds() + response.expires_in);
                    document.cookie = "access_token=" + response.access_token + ";secure=true; path=/; expires=" + expiresIn.toGMTString()

                    let refreshTokenExpiresIn = new Date();
                    if (response.refresh_expires_in !== 0) {
                        refreshTokenExpiresIn.setSeconds(refreshTokenExpiresIn.getSeconds() + response.refresh_expires_in);
                    } else {
                        refreshTokenExpiresIn = new Date(refreshTokenExpiresIn.getFullYear() + 1, refreshTokenExpiresIn.getMonth(), refreshTokenExpiresIn.getDay())
                    }
                    document.cookie = "refresh_token=" + response.refresh_token + ";secure=true; path=/; expires=" + refreshTokenExpiresIn.toGMTString()
                }
            })
            isLogged = true
        }
    }
})
if(!isLogged){
    window.location = "/auth"
}