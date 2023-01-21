angular.module('market').controller('storeController', function($scope, $http, $location, $localStorage){

    console.log("test console.log: store.js - is working!")

    const storeContextPath = 'http://localhost:5555/core/';
    const cartContextPath = 'http://localhost:5555/cart/';

    $scope.pageNumber = 1;

//Получение списка продуктов
$scope.loadProducts = function() {
                $http({
                    url: storeContextPath + 'api/v1/products',
                    method: 'GET',
                    params: {
                             p: $scope.pageNumber,
                             min_price: $scope.filter ? $scope.filter.min_price : null,
                             max_price: $scope.filter ? $scope.filter.max_price : null,
                             title_part: $scope.filter ? $scope.filter.title_part : null
                    }
                }).then(function(response) {
                                  $scope.productList = response.data.content;
                })
}
//Пагинация
$scope.change_page = function(pageVar) {
             $scope.pageNumber = $scope.pageNumber + pageVar;
             if($scope.pageNumber <= 0){
                    $scope.pageNumber = 1
             }
             $http({
                    url: storeContextPath + 'api/v1/products',
                    method: 'GET',
                    params: {
                             p: $scope.pageNumber,
                             min_price: $scope.filter ? $scope.filter.min_price : null,
                             max_price: $scope.filter ? $scope.filter.max_price : null,
                             title_part: $scope.filter ? $scope.filter.title_part : null
                    }
             }).then(function(response) {
                     $scope.productList = response.data.content;
             })
}
//Информация о продукте
$scope.showProductInfo = function(productId){
    $http.get(storeContextPath + 'api/v1/products/'+productId)
            .then(function(response){
            alert(response.data.title);
    })
}
//Добавить в корзину
$scope.addToCart = function(productId){
    $http.get(cartContextPath + 'api/v1/cart/add/' + productId).then(function(response){
    $scope.loadCart();
    });
}

////Удаление
//$scope.deleteProductById = function(id){
//            $http.delete('http://localhost:5555/core/api/v1/products/' + id)
//            .then(function(response) {
//                $scope.loadProducts();
//            })
//}

$scope.loadProducts();

});

