package controllers;

import play.Play;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSAuthScheme;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import utils.OAuthUtils;

public class OAuth extends Controller {

	private static final String OAUTH_URL = Play.application().configuration().getString("oauth.url");
	private static final String OAUTH_CLIENT_ID = Play.application().configuration().getString("oauth.client.id");
	private static final String OAUTH_CLIENT_SECRET = Play.application().configuration().getString("oauth.client.secret");
	private static final String OAUTH_REDIRECT_URI = Play.application().configuration().getString("oauth.redirect.uri");
	
	// Callback from OAuth Authorization EndPoint
	public static Promise<Result> callback() {
		// get the authorization code
		String authorizationCode = request().getQueryString("code");
		
		// call OAuth Server with response code to get the access token
		WSRequestHolder req = WS.url(OAUTH_URL+"/oauth/token").setAuth(OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, WSAuthScheme.BASIC);
		req.setQueryParameter("grant_type", "authorization_code");
		req.setQueryParameter("code", authorizationCode);
		req.setQueryParameter("redirect_uri", OAUTH_REDIRECT_URI);
		
		// Get the JSON Response
		Promise<Result> resultPromise = req.post("content").map(
		        new Function<WSResponse, Result>() {
		            public Result apply(WSResponse response) {
		            	if (200 == response.getStatus()) {
		            		// get the access token
		            		String accessToken = response.asJson().get("access_token").textValue();
		            		session().put("access_token", accessToken);
		            		// get the user information
		            		return redirect(routes.UserInfo.index().absoluteURL(request()));
		            	}
		            		
	            		// go back to authentication
		                return redirect(OAuthUtils.oAuthAuthorizeUrl());
		            }
		        }
		);
		
		return resultPromise; 
	}
	
	public static Result logoutCallback() {
		session().clear();
		return redirect(routes.Application.index());
	}
	
}
