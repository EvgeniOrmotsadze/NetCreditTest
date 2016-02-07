


function onClickRegister(){
    var registerObj = {};
    registerObj.name = document.getElementById("name").value;
    registerObj.password = document.getElementById("password").value;
    registerObj.phone = document.getElementById("phone").value;
    registerObj.birthday = document.getElementById("birthday").value;
    registerObj.monthSalary = document.getElementById("monthSalary").value;
    registerObj.currentLiability = document.getElementById("currentLiability").value;
    callAjax(registerObj,"/netcredit/register");
}


function onSingUp(){
    var singObj = {};
    singObj.name = document.getElementById("nameS").value;
    singObj.password = document.getElementById("passwordS").value;
    singObj.phone = "";
    singObj.birthday = "";
    singObj.monthSalary = "";
    singObj.currentLiability = "";
    callAjax(singObj,"/netcredit/sing");
}

function showSingUP(){
    document.getElementById("formRegist").style.display = 'none';
    document.getElementById("viewResult").style.display = 'none';
    document.getElementById("singUp").style.display = 'block';
}

function backToRegster(){
    document.getElementById("formRegist").style.display = 'block';
    document.getElementById("viewResult").style.display = 'none';
    document.getElementById("singUp").style.display = 'none';
}

function callAjax(registerObj,url){
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        mimeType: 'application/json',
        async: true,
        url: url,
        data: JSON.stringify(registerObj),
        success: function (result) {
             console.log("warmatebit");
             initQueue(result,url);
        }
    });
}

function initQueue(result, url ){
    if(url === "/netcredit/register"){
        showSingUP();
    }else {
        document.getElementById("formRegist").style.display = 'none';
        document.getElementById("singUp").style.display = 'none';
        document.getElementById("viewResult").style.display = 'block';
        //
        document.getElementById("vname").value = result.name;
        document.getElementById("vpassword").value = result.password;
        document.getElementById("vphone").value = result.phone;
        document.getElementById("vbirthday").value = result.birthday;
        document.getElementById("vmonthSalary").value = result.monthSalary;
        document.getElementById("vcurrentLiability").value = result.currentLiability;
        calculateMaxPosibility(result.birthday, result.monthSalary, result.currentLiability);
    }

}

function calculateMaxPosibility(birth,monthsalary,currentAvail){

    var birthYear = birth.substr(6);
    var d = new Date();
    var result = d.getYear() - parseInt(birthYear);
    result = (result *10) + (parseInt(monthsalary) - parseInt(currentAvail));
    document.getElementById("maxAbility").innerHTML = result;
}


function updateParam(){
    var updateObj = {};
    updateObj.name = document.getElementById("vname").value;
    updateObj.password = document.getElementById("vpassword").value;
    updateObj.phone = document.getElementById("vphone").value;
    updateObj.birthday =  document.getElementById("vbirthday").value;
    updateObj.monthSalary =  document.getElementById("vmonthSalary").value;
    updateObj.currentLiability = document.getElementById("vcurrentLiability").value;
    callAjax(updateObj,"/netcredit/update");
    //TODO
}