/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Titouan COMPIEGNE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
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
