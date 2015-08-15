'use strict';

/**
 * @ngdoc function
 * @name angularjsTodolistApp.controller:LogoutCtrl
 * @description
 * # LogoutCtrl
 * Controller of the angularjsTodolistApp
 */
angular.module('angularjsTodolistApp')
  .controller('LogoutCtrl', function ($location, $rootScope, $window) {
		$window.sessionStorage.clear();
		$rootScope.$broadcast('userLogout');
		$location.path("/");
	});
