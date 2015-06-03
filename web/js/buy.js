function dataBuyValidate() {
    var name=document.buyCart.name.value;
    var end=document.buyCart.end.value;
    var cpf=document.buyCart.cpf.value;
    if (isNull(name)||isNull(end)||isNull(cpf)){
        alert("Preencha todos os campos e tente novamente!");
        return false;
    }
    return true;
}

function isNull(s) {
    return ((s==null)||(s==""));
}