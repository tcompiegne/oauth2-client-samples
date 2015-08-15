'use strict';

/**
 * @ngdoc function
 * @name angularjsTodolistApp.controller:NavBarCtrl
 * @description
 * # NavBarCtrl
 * Controller of the angularjsTodolistApp
 */
angular.module('angularjsTodolistApp')
  .controller('NavBarCtrl', function ($scope, $rootScope, $window) {
		$rootScope.$on('userLoggedIn', function() {
			$scope.init();
		});	

		$rootScope.$on('userLogout', function() {
			$scope.init();
		});

		$scope.logoutRedirectUri = function() {
			return "http://localhost:8080/logout?target_url=http://localhost:9000/#/oauth2logoutcallback";
		};

		$scope.init = function() {
			$scope.username = $window.sessionStorage.getItem("username");
			$scope.isLogggedIn = $window.sessionStorage.getItem("isLogggedIn") === "true";
		};

		$scope.init();
	});
