package controllers;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
	
	private static final String OAUTH_REDIRECT_URI = Play.application().configuration().getString("oauth.redirect.uri");
	private static final String OAUTH_URL = Play.application().configuration().getString("oauth.url");
	private static final String OAUTH_CLIENT_ID = Play.application().configuration().getString("oauth.client.id");

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public static Result login() {
		return redirect(oauthAuthorizeUrl());
	}

	private static String oauthAuthorizeUrl() {
		return OAUTH_URL + "/oauth/authorize?response_type=code&client_id="+OAUTH_CLIENT_ID+"&redirect_uri="+OAUTH_REDIRECT_URI;
	}
	
}
