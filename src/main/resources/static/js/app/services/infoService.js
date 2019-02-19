(function () {

	var am = angular.module('shop');

	am.service('infoService', ['$http', function($http) {
        this.list = function() {
            return $http.get('/infos', {});
        };
    }]);
})();