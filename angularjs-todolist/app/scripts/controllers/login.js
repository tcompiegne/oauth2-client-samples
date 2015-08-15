'use strict';

/**
 * @ngdoc function
 * @name angularjsTodolistApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the angularjsTodolistApp
 */
angular.module('angularjsTodolistApp')
  .controller('LoginCtrl', function ($window) {
		$window.location.href = "http://localhost:8080/oauth/authorize?response_type=token&client_id=test&redirect_uri=http://localhost:9000/oauth2callback.html";	
	});
