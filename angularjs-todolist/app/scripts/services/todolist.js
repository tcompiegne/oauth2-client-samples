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
 * @name angularjsTodolistApp.service:TodoServices
 * @description
 * # TodoServices
 * Services of the angularjsTodolistApp
 */
angular.module('angularjsTodolistApp').factory('todoService', function($http, $window){
	var baseUrl = 'http://localhost:9001';
	return { 
		
		findAll: function() {
			var req = {
				method: 'GET',
 				url: baseUrl + '/rest/todos',
 				headers: {
					'Authorization' :	'Bearer ' + $window.sessionStorage.getItem("access_token")
 				}
			};
      		return $http(req).then(function(result) {	
 	    		return result.data;
      		});
		},

		add: function(todo) {
			var req = {
				method: 'POST',
 				url: baseUrl + '/rest/todos/add',
 				headers: {
					'Authorization' :	'Bearer ' + $window.sessionStorage.getItem("access_token"),
					'Content-Type' : 'application/json'
 				},
				data: '{ "description": "' + todo + '", "username" : "'+ $window.sessionStorage.getItem("username") +'" }'
			};
      		return $http(req).then(function(result) {	
 	    		return result.data;
      		});
		},

		edit: function(id, todo) {
			var req = {
				method: 'POST',
 				url: baseUrl + '/rest/todos/edit',
 				headers: {
					'Authorization' :	'Bearer ' + $window.sessionStorage.getItem("access_token"),
					'Content-Type' : 'application/json'
 				},
				data: '{ "id": ' + id + ', "description": "'+ todo + '" }'
			};
			return $http(req).then(function(result) {	
 	    		return result.data;
      		});
		},

		remove: function(id) {
			var req = {
				method: 'POST',
 				url: baseUrl + '/rest/todos/'+id+'/delete',
 				headers: {
					'Authorization' :	'Bearer ' + $window.sessionStorage.getItem("access_token")
 				}
			};
			return $http(req).then(function(result) {	
 	    		return result.data;
      		});
		}
	}; 
});
