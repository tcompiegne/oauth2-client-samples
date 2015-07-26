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

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tce.oauth2.spring.client.models.OAuthToken;

/**
 * 
 * @author Titouan COMPIEGNE
 *
 */
@Controller
public class OAuthController {
	
	@Value("${oauth.url}")
	private  String OAUTH_URL;

	@Value("${oauth.client.id}")
	private String OAUTH_CLIENT_ID;
	
	@Value("${oauth.client.secret}")
	private String OAUTH_CLIENT_SECRET;
	
	@Value("${oauth.redirect.uri}")
	private String OAUTH_REDIRECT_URI;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//Callback from OAuth Authorization EndPoint
	@RequestMapping("/oauth2callback")
	public String callback(@RequestParam("code") String authorizationCode, HttpServletRequest request) {
		
		// call OAuth Server with response code to get the access token
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(OAUTH_URL+"/oauth/token")
        .queryParam("grant_type", "authorization_code")
        .queryParam("code", authorizationCode)
        .queryParam("redirect_uri", OAUTH_REDIRECT_URI);
		ResponseEntity<OAuthToken> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, new HttpEntity<>(createHeaders(OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET)), OAuthToken.class);
		if (response.getStatusCode().is4xxClientError()) {
			return "redirect:/login";
		}
		
		// get the access token
		OAuthToken oauthToken = response.getBody();
		// set access token to the session
		request.getSession().setAttribute("access_token", oauthToken.getAccessToken());
		// get the user information
		return "redirect:/userinfo";
	}
	
	@RequestMapping("/oauth2logoutcallback")
	public String logoutCallback(HttpServletRequest request) {
		request.getSession(false).invalidate();
		return "redirect:/";
	}
	
	@SuppressWarnings("serial")
	private HttpHeaders createHeaders(final String username, final String password){
	   return new HttpHeaders(){
	      {
	         String auth = username + ":" + password;
	         byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF-8")));
	         String authHeader = "Basic " + new String( encodedAuth );
	         set("Authorization", authHeader);
	         set("Content-Type", "application/json");
	      }
	   };
	}

}
