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
