package services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.Todo;
import play.Play;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class TodoService {

	private static final String OAUTH_RESOURCE_SERVER_URL = Play.application().configuration().getString("oauth.resource.server.url");

	public static List<Todo> findAll(String accessToken) {
		WSRequestHolder req = WS.url(OAUTH_RESOURCE_SERVER_URL + "/rest/todos");
		req.setHeader("Authorization", "Bearer " + accessToken);

		Promise<List<Todo>> jsonPromise = req.get().map(new Function<WSResponse, List<Todo>>() {
			public List<Todo> apply(WSResponse response) {
				JsonNode json = response.asJson();
				ArrayNode results = (ArrayNode) json;
				List<Todo> todos = new ArrayList<Todo>();
				Iterator<JsonNode> it = results.iterator();

				while (it.hasNext()) {
					JsonNode node = it.next();
					Todo todo = new Todo();
					todo.setDescription(node.get("description").asText());
					todos.add(todo);
				}

				return todos;
			}
		});
		return jsonPromise.get(5000);
	}
}
