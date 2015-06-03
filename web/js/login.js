function LoginValidate() {
    var user=document.login.user.value;
    var pass=document.login.pass.value;
    if (isNull(user)||isNull(pass)){
        alert("Preencha o login e tente novamente!");
        return false;
    }
    return true;
}

function isNull(s) {
    return ((s==null)||(s==""));
}