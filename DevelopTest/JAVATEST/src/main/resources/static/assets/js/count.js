expireDate = new Date()

expireDate.setMonth(expireDate.getMonth() + 6)
hitCt = eval(cookieVal("pageHit"))
hitCt++
document.cookie= "pageHIT=" + hitCt + ";expires=" + expireDate.toGMTString()

function cookieVal(cookieName) {
    thisCookie = document.cookie.split("; ")
    for(i = 0; i< thisCookie.length; i++) {
        if(cookieName == thisCookie[i].split("=")[0]) {
            return thisCookie[i].split("=")[1]
    }
}
return 0
}