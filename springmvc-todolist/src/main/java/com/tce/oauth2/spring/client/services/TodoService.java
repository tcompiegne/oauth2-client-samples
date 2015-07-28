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
package com.tce.oauth2.spring.client.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tce.oauth2.spring.client.models.Todo;
import com.tce.oauth2.spring.client.models.TodoResponse;

/**
 * 
 * @author Titouan COMPIEGNE
 *
 */
@Service
public class TodoService {

	@Value("${oauth.resource.server.url}")
	private String OAUTH_RESOURCE_SERVER_URL;
	
	@Autowired
	private RestTemplate restTemplate;

	public List<Todo> findAll(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<Todo[]> response = restTemplate.exchange(OAUTH_RESOURCE_SERVER_URL + "/rest/todos", HttpMethod.GET, entity, Todo[].class);
		Todo[] todos = response.getBody();
		
		return Arrays.asList(todos);
	}

	public List<Todo> findByUsername(String accessToken, String username) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<Todo[]> response = restTemplate.exchange(OAUTH_RESOURCE_SERVER_URL + "/rest/todos/" + username, HttpMethod.GET, entity, Todo[].class);
		Todo[] todos = response.getBody();
		
		return Arrays.asList(todos);
	}

	public Todo add(String accessToken, Todo todo) {
		// Set the headers
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-Type", "application/json");
		
		// Post request
		HttpEntity<Todo> request = new HttpEntity<Todo>(todo, headers);
		Todo todoAdded = restTemplate.postForObject(OAUTH_RESOURCE_SERVER_URL + "/rest/todos/add", request, Todo.class);

		return todoAdded;
	}
	
	public Todo edit(String accessToken, Todo todo) {
		// Set the headers
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-Type", "application/json");
		
		// Post request
		HttpEntity<Todo> request = new HttpEntity<Todo>(todo, headers);
		Todo todoEdited = restTemplate.postForObject(OAUTH_RESOURCE_SERVER_URL + "/rest/todos/edit", request, Todo.class);

		return todoEdited;
	}

	public TodoResponse delete(String accessToken, Long id) {
		// Set the headers
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-Type", "application/json");
		
		// Post request
		HttpEntity<String> request = new HttpEntity<String>("", headers);
		TodoResponse response = restTemplate.postForObject(OAUTH_RESOURCE_SERVER_URL + "/rest/todos/" + id + "/delete", request, TodoResponse.class);

		return response;
	}

}
