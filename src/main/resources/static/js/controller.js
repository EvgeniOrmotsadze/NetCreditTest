angular.module("myApp", []).controller('myController', function ($scope, $http) {
    console.log('angular load');

    $scope.showRegister = true;
    $scope.showSingIn = false;
    $scope.showResult = false;
    $scope.logOutDiv = false;

    $scope.onClickRegister = function () {
        if(validateRegister()) {
            $http.post('/netcredit/register', {
                name: $scope.Name,
                password: $scope.Password,
                phone: $scope.Phone,
                birthday: $scope.Birthday,
                monthSalary: $scope.MonthSalary,
                currentLiability: $scope.CurreentLia
            })
                .success(function (response) {
                    console.log('result', response);
                    if (response == true) {
                        $scope.showSingIn = true;
                        $scope.showRegister = false;
                        $scope.validation = false;
                    } else {
                        $scope.validation = true;
                        $scope.validParam = "username or password is invalid; password must be > 4 and name length must be > 2";
                    }
                })
                .error(function (error) {
                    console.log(error);
                });
        }else{
            $scope.validation = true;
        }
    };

    function validateRegister(){
        var birth  = document.getElementById('birthday').value;
        var year = birth.split('-')[0];
        var date = new Date();
        if(document.getElementById("name").value == "" || document.getElementById("password").value == "" ||
            document.getElementById("birthday").value == ""){
            $scope.validParam = "Please Fill Required Field";
            return false;
        }

        if(date.getFullYear() - parseInt(year) < 18){
            $scope.validParam = "on your age can't get loan from Net Credit !";
            return false;
        }

        return true;
    }

    $scope.onSingUp = function (){
        $http.post('/netcredit/sing',{name:$scope.nameS, password:$scope.passwordS})
            .success(function (response) {
                    console.log('result', response);
                    $scope.showRegister = false;
                    $scope.showSingIn = false;
                    $scope.showResult = true;
                    $scope.logOutDiv = true;
                    $scope.vname = response.name;
                    $scope.vpassword = response.password;
                    $scope.vphone = response.phone;
                    $scope.vbirthday = response.birthday;
                    $scope.vmonthSalary = response.monthSalary;
                    $scope.vcurrentLiability = response.currentLiability;
                    $scope.MaxAvail = "Your Max Available Amount : " + calculateMaxAvail(response);
            })
            .error(function (error) {
                $scope.validation = true;
                $scope.validParam = "User Doesn't Exist. Please Start  Registration";
                console.log(error);
            });
    };

    $scope.backToRegister = function (){
        $scope.showRegister = true;
        $scope.showSingIn = false;
        $scope.validation = false;
    };

    $scope.showSingUP = function(){
        $scope.showSingIn = true;
        $scope.showRegister = false;
        $scope.validation = false;
    }

    $scope.updateParam = function(){
            $http.post('/netcredit/update',{
            name:$scope.vname, password: $scope.vpassword ,phone: $scope.vphone,
            birthday: $scope.vbirthday,monthSalary: $scope.vmonthSalary,currentLiability:$scope.vcurrentLiability
            })  .success(function (response) {
                    alert("update successfully");
                })
                .error(function (error) {
                    console.log(error);
                });
    };

    $scope.logOut = function(){
        $http.post('/netcredit/logout')
            .success(function (response) {
                $scope.showRegister = false;
                $scope.showSingIn = true;
                $scope.showResult = false;
                $scope.logOutDiv = false;
            })
            .error(function (error) {
                console.log(error);
            });
    };

    function calculateMaxAvail(data){
        var  currentDate = new Date();
        var age = (currentDate.getFullYear() - parseInt(data.birthday.substr(0,4)));
        if(age < 18 || age > 65){
            return 0;
        }else {
            var max = age * 10 + parseInt(data.monthSalary - data.currentLiability);
            console.log(data.birthday + " " + data.monthSalary + " " + data.currentLiability);
            if(max < 0) return 0;

            return max;
        }
    }
});
