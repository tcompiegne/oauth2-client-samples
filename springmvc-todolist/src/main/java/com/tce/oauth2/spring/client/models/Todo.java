package com.tce.oauth2.spring.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Titouan COMPIEGNE
 *
 */
public class Todo {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("username")
	private String username;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
