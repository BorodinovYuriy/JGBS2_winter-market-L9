angular.module('market').controller('cartController', function($scope, $http, $location, $localStorage){

    console.log("test console.log: cart.js - is working!")

    const cartContextPath = 'http://localhost:5555/cart/';
    const coreContextPath = 'http://localhost:5555/core/';

//Отображение корзины
$scope.loadCart = function(){
    $http.get(cartContextPath + 'api/v1/cart').then(function(response){
    $scope.cart = response.data;
    })
}
//Очистить корзину
$scope.clearCart = function(productId){
    $http.delete(cartContextPath + 'api/v1/cart').then(function(response){
    $scope.loadCart();
    })
}
//Удаление из корзины
$scope.deleteFromCart = function(productId){
    $http.delete(cartContextPath + 'api/v1/cart/'+ productId).then(function(response){
    $scope.loadCart();
    })
}

//Количество в корзине
$scope.changeQuantity = function(productId, number){
    if(number < 0){
            $http.put(cartContextPath + 'api/v1/cart' + '/decrease/' + productId)
            .then(function(response){
                $scope.loadCart();
            })
    }
    if(number > 0){
            $http.put(cartContextPath + 'api/v1/cart' + '/increase/' + productId)
            .then(function(response){
                $scope.loadCart();
            })
    }

}
//Оформление заказа
$scope.createOrder = function(){
     $http.post(coreContextPath + 'api/v1/orders')
                .then(function(response){
                        alert('Заказ оформлен!');
                        $scope.loadCart();
                })
};

$scope.loadCart();

});


