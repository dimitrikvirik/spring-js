function parseJwt (token) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
}
function getAuthToken(){
   const arr =   document.cookie.split(";")
  return  arr.filter(function (v){
     return  v.startsWith("access_token")
    })[0].split("=")[1]

}
function getCurrentUsername(){
    return    parseJwt(getAuthToken()).preferred_username
}





function addLoginCookies(data){
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