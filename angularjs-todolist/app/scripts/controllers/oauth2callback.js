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
 * @name angularjsTodolistApp.controller:OAuth2CallbackCtrl
 * @description
 * # OAuth2CallbackCtrl
 * Controller of the angularjsTodolistApp
 */
angular.module('angularjsTodolistApp')
  .controller('OAuth2CallbackCtrl', function ($location, $window) {
		var hash = $location.path().substr(1);
		var responseParameters = hash.split("&");
    var parameterMap = [];
    for(var i = 0; i < responseParameters.length; i++) {
        parameterMap[responseParameters[i].split("=")[0]] = responseParameters[i].split("=")[1];
    }
    if(parameterMap.access_token !== undefined && parameterMap.access_token !== null) {
 			var access_token = parameterMap.access_token;
      $window.sessionStorage.setItem("access_token", access_token);
      $location.path('/userinfo');
    } else {
			console.log("Problem authenticating");
			$location.path('/');
    }
	});
