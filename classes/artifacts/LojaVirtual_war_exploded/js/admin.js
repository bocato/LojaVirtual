function ProdCadastreValidate() {
    var prodi=document.cadprod.prodi.value;
    var prodn=document.cadprod.prodn.value;
    var prodp=document.cadprod.prodp.value;
    if (isNull(prodn)||(isNaN(prodi))||(isNaN(prodp))||(!isInt(prodi))){
        alert("Preencha os dados do produto corretamente!");
        return false;
    }
    return true;
}

function CliCadastreValidate() {
    var user=document.cadcli.user.value;
    var pass1=document.cadcli.pass1.value;
    var pass2=document.cadcli.pass2.value;
    if (isNull(user)||(isNull(pass1))||(isNull(pass2))||pass1!=pass2) {
        alert("Preencha os dados do cliente corretamente!");
        return false;
    }
    return true;
}

function isInt(n) {
    return n % 1 === 0;
}

function isNull(s) {
    return ((s==null)||(s==""));
}