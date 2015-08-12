'use strict';

/**
 * @ngdoc function
 * @name angularjsTodolistApp.controller:TodoListCtrl
 * @description
 * # TodoListCtrl
 * Controller of the angularjsTodolistApp
 */
angular.module('angularjsTodolistApp')
  .controller('TodoListCtrl', function ($scope) {
		$scope.todos = ['Item 1', 'Item 2', 'Item 3'];

		$scope.addTodo = function () {
			$scope.todos.push($scope.todo);
			$scope.todo = '';
		};
  });
