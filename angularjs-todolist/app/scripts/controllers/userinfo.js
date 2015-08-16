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
 * @ngdoc function
 * @name angularjsTodolistApp.controller:UserInfoCtrl
 * @description
 * # UserInfoCtrl
 * Controller of the angularjsTodolistApp
 */

angular.module('angularjsTodolistApp')
  .controller('UserInfoCtrl', function ($http, $rootScope, $window, $location, CONFIG) {
		
		var req = {
			method: 'GET',
			url: CONFIG.OAUTH_URL + '/userinfo',
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