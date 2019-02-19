(function () {

	var am = angular.module('shop');

	am.service('productService', ['$http', function($http) {
        this.list = function(selectedOptionIds) {
            return $http.get('/products?options=' + selectedOptionIds.toString(), {});
        };
    }]);
})();