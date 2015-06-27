package controllers;

import java.util.List;

import play.mvc.Controller;
import play.mvc.Result;
import services.TodoService;
import views.html.todolist;

public class Todo extends Controller {

	public static Result index() {
		// access token missing
		// call OAuth Authorization Server to get one
		if (!session().containsKey("access_token")) {
			return redirect(routes.Application.login().absoluteURL(request()));
		}

		List<models.Todo> todos = TodoService.findAll(session().get("access_token"));
		return ok(todolist.render(todos));
	}
}
