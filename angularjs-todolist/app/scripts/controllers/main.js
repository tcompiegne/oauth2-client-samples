'use strict';

/**
 * @ngdoc function
 * @name angularjsTodolistApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the angularjsTodolistApp
 */
angular.module('angularjsTodolistApp')
  .controller('MainCtrl', function ($scope, $window) {
		$scope.isLoggedIn = $window.sessionStorage.getItem("isLoggedIn") === "true";
  });
