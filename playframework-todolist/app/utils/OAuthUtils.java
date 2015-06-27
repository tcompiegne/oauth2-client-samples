package utils;

import play.Play;

public class OAuthUtils {

	private static final String OAUTH_URL = Play.application().configuration().getString("oauth.url");
	private static final String OAUTH_CLIENT_ID = Play.application().configuration().getString("oauth.client.id");
	private static final String OAUTH_REDIRECT_URI = Play.application().configuration().getString("oauth.redirect.uri");

	public static String oAuthAuthorizeUrl() {
		return OAUTH_URL + "/oauth/authorize?response_type=code&client_id=" + OAUTH_CLIENT_ID + "&redirect_uri=" + OAUTH_REDIRECT_URI;
	}
}
