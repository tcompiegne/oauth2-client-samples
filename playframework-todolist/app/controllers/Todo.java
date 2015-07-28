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

import java.util.List;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.TodoService;
import views.html.todolist;

/**
 * 
 * @author Titouan COMPIEGNE
 *
 */
public class Todo extends Controller {

	public static Result index() {
		// access token missing
		// call OAuth Authorization Server to get one
		if (!session().containsKey("access_token")) {
			return redirect(routes.Application.login().absoluteURL(request()));
		}

		List<models.Todo> todos = TodoService.findByUsername(session().get("access_token"), session().get("username"));
		return ok(todolist.render(todos));
	}

	public static Result add() {
		String description = request().body().asFormUrlEncoded().get("description")[0];
	  TodoService.add(session().get("access_token"), session().get("username"), description);
		return redirect(routes.Todo.index().absoluteURL(request()));
	}

	public static Result edit() {
		Long id = Long.valueOf(request().body().asFormUrlEncoded().get("pk")[0]);
		String description = request().body().asFormUrlEncoded().get("value")[0];
		models.Todo todo = TodoService.edit(session().get("access_token"), id, description);
		return ok(Json.toJson(todo)).as("application/json");
	}

	public static Result delete(Long id) {
		TodoService.delete(session().get("access_token"), session().get("username"), id);
		return redirect(routes.Todo.index().absoluteURL(request()));
	}
}
