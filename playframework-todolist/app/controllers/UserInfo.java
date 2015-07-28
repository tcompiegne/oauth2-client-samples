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
 * 
 * @author Titouan COMPIEGNE
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
