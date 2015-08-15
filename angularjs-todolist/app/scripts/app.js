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
    'ngTouch',
		'xeditable'
  ])
  .config(function ($routeProvider, $httpProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
			.when('/login', {
				templateUrl: 'views/login.html',
				controller: 'LoginCtrl',
				controllerAs : 'login'
			})
      .when('/todos', {
        templateUrl: 'views/todolist.html',
        controller: 'TodoListCtrl',
        controllerAs: 'todoList'
      })
			.when('/userinfo', {
			  templateUrl: 'views/userinfo.html',
        controller: 'UserInfoCtrl',
        controllerAs: 'userInfo'
			})
			.when('/oauth2logoutcallback', {
				templateUrl: 'views/logout.html',
				controller: 'LogoutCtrl',
				controllerAs : 'logout'
			})
      .otherwise({
        redirectTo: '/'
      });

		$httpProvider.interceptors.push('httpInterceptor');
  })
	.factory('httpInterceptor', function($q, $location) {
		return {
			response: function (response) {
				return response;
			},
			responseError: function(response) {
				if (response.status === 401) {
					$location.path('/login');
				}
				return $q.reject(response);
			}
		};
	})
	.run(function(editableOptions) {
  	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
	});
