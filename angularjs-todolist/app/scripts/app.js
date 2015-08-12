'use strict';

/**
 * @ngdoc overview
 * @name angularjsTodolistApp
 * @description
 * # angularjsTodolistApp
 *
 * Main module of the application.
 */
angular
  .module('angularjsTodolistApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/todos', {
        templateUrl: 'views/todolist.html',
        controller: 'TodoListCtrl',
        controllerAs: 'todoList'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
