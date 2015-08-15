'use strict';

/**
 * @ngdoc function
 * @name angularjsTodolistApp.controller:UserInfoCtrl
 * @description
 * # UserInfoCtrl
 * Controller of the angularjsTodolistApp
 */

angular.module('angularjsTodolistApp')
  .controller('UserInfoCtrl', function ($http, $rootScope, $window, $location) {
		var baseUrl = 'http://localhost:8080';
		
		var req = {
				method: 'GET',
 				url: baseUrl + '/userinfo',
 				headers: {
					'Authorization' :	'Bearer ' + $window.sessionStorage.getItem("access_token")
 				}
		};

		$http(req).then(function(result) {
			$window.sessionStorage.setItem("username", result.data.username);
			$window.sessionStorage.setItem("isLoggedIn", true);
			$rootScope.$broadcast('userLoggedIn');
			$location.path("/");
    });

	});
