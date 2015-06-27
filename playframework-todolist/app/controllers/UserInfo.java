package controllers;

import play.Play;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import utils.OAuthUtils;

/**
 * User info controller to retrieve current user information from OAuth Server
 * 
 */
public class UserInfo extends Controller {

	private static final String OAUTH_URL = Play.application().configuration().getString("oauth.url");

	public static Promise<Result> index() {
		if (session().get("access_token") == null) {
			return Promise.pure(redirect(routes.Application.index().absoluteURL(request())));
		}

		String accessToken = session().get("access_token");
		WSRequestHolder req = WS.url(OAUTH_URL + "/userinfo");
		req.setHeader("Authorization", "Bearer " + accessToken);

		// Get the JSON Response
		Promise<Result> resultPromise = req.get().map(new Function<WSResponse, Result>() {
			public Result apply(WSResponse response) {
				if (200 == response.getStatus()) {
					// get the user info
					String username = response.asJson().get("username").textValue();
					session().put("username", username);

					return redirect(routes.Application.index().absoluteURL(request()));
				}

				// go back to authentication
				return redirect(OAuthUtils.oAuthAuthorizeUrl());
			}
		});
		return resultPromise;
	}
}
