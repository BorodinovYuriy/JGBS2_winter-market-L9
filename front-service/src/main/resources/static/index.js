//Маршруты
(function () {
    angular
        .module('market', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
           $routeProvider
               .when('/', {
                   templateUrl: 'welcome/welcome.html',
                   controller: 'welcomeController'
               })
               .when('/store', {
                   templateUrl: 'store/store.html',
                   controller: 'storeController'
               })
               .when('/cart', {
                   templateUrl: 'cart/cart.html',
                   controller: 'cartController'
               })
               .otherwise({
                   redirectTo: '/'
               });
       }

    function run($rootScope, $http, $localStorage){
        if($localStorage.winterMarketUser){
            try{
                let jwt = $localStorage.winterMarketUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if(currentTime > payload.exp){
                    console.log("Token is expired!");
                    delete $localStorage.winterMarketUser;
                    $http.defaults.headers.common.Authorization ='';
                    }
            }catch (e){
            }

            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.winterMarketUser.token;
        }
    }
})();

//Authentication-----------------------------
angular.module('market').controller('indexController', function($scope, $http, $location, $localStorage){

    console.log("test console.log: index.js - is working!")

//auth/auth->тут ходим за токенами
    $scope.tryToAuth = function(){
        $http.post('http://localhost:5555/auth/auth', $scope.user)
            .then(function successCallback(response){
                if(response.data.token){
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

        console.log("User is authorized, token: " + $http.defaults.headers.common.Authorization)

                $scope.user.username = null;
                $scope.user.password = null;

                $location.path('/');
            }
        }, function errorCallback(response){
        });
    };

    $scope.tryToLogout = function(){
        $scope.clearUser();
        $scope.user = null;
        $location.path('/');
    };

    $scope.clearUser = function(){
        delete $localStorage.winterMarketUser;
        $http.defaults.headers.common.Authorization = '';
            console.log("User shutdown, [Authorization] is empty")
    };

    $scope.isUserLoggedIn = function(){
        if ($localStorage.winterMarketUser){
            return true;
        }else{
            return false;
        }
    };
});

