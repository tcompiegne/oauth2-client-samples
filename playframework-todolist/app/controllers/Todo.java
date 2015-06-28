package controllers;

import java.util.List;

import play.libs.Json;
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
