(function () {

	var am = angular.module('shop');

	am.controller('ProductController', ['$scope', 'infoService', 'productService',
	        function ($scope, infoService, productService) {

        var setMessage = function(message) {
            $scope.message = message;
        };

        infoService.list().then(function(response){
            $scope.infos = response.data;
        }, function() {
            setMessage('Error getting form info list');
        });

        $scope.listProduct = function() {
            var selectedOptionIds = [];
            $scope.infos.forEach(function(info) {
                selectedOptionIds.push(info.selectedOptionId);
            });
            productService.list(selectedOptionIds).then(function(response){
                $scope.products = response.data;
            }, function() {
                setMessage('Error getting product list');
            });
        }
	}]);
})();