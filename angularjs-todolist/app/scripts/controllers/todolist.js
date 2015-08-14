'use strict';

/**
 * @ngdoc function
 * @name angularjsTodolistApp.controller:TodoListCtrl
 * @description
 * # TodoListCtrl
 * Controller of the angularjsTodolistApp
 */
angular.module('angularjsTodolistApp')
  .controller('TodoListCtrl', function ($scope, $location, todoService) {
		
		$scope.addTodo = function() {
			todoService.add($scope.todo).then(function(data) {
				$scope.todos.push(data);
				$scope.todo = '';
				$('#add-todo').modal('hide');
			});
		};

		$scope.editTodo = function(id, todo) {
			todoService.edit(id, todo).then(function(data) {
			});
		};

		$scope.deleteTodo = function(todo) {
			todoService.remove(todo.id).then(function(data) {
				$scope.todos.splice($scope.todos.indexOf(todo), 1);
			});
		};

		todoService.findAll().then(function(data) {
       $scope.todos = data;
   	});
	
	});
