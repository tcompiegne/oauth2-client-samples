'use strict';

/**
 * @ngdoc function
 * @name angularjsTodolistApp.service:TodoServices
 * @description
 * # TodoServices
 * Services of the angularjsTodolistApp
 */

var baseUrl = 'http://localhost:9001';

angular.module('angularjsTodolistApp').factory('todoService', function($http){
	return { 
		
		findAll: function() {
			var req = {
				method: 'GET',
 				url: baseUrl + '/rest/todos',
 				headers: {
					'Authorization' :	'Bearer d5116e25-b074-419c-bd4a-c1bf5507db33'
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
					'Authorization' :	'Bearer d5116e25-b074-419c-bd4a-c1bf5507db33',
					'Content-Type' : 'application/json'
 				},
				data: '{ "description": "' + todo + '", "username" : "userTest" }'
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
					'Authorization' :	'Bearer d5116e25-b074-419c-bd4a-c1bf5507db33'
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
					'Authorization' :	'Bearer d5116e25-b074-419c-bd4a-c1bf5507db33'
 				}
			};
			return $http(req).then(function(result) {	
 	    	return result.data;
      });
		}
	}; 
});
