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
package com.tce.oauth2.spring.client.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.tce.oauth2.spring.client.models.User;

/**
 * 
 * @author Titouan COMPIEGNE
 *
 */
@Controller
public class UserInfoController {

	@Value("${oauth.url}")
	private String OAUTH_URL;
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/userinfo")
	public String index(HttpServletRequest request) {
		if (request.getSession().getAttribute("access_token") == null) {
			return "redirect:/";
		}

		String accessToken = (String) request.getSession().getAttribute("access_token");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + accessToken);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		ResponseEntity<User> response = restTemplate.exchange(OAUTH_URL+ "/userinfo", HttpMethod.GET, entity, User.class);

		if (response.getStatusCode().is4xxClientError()) {
			return "redirect:/login";
		}
		
		User user = response.getBody();
		request.getSession(false).setAttribute("username", user.getUsername());
		return "redirect:/";
	}
}
