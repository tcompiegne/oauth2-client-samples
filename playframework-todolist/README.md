About TodoList OAuth2 Client
=================================

![oauth2-client-play-todolist] (https://github.com/tcompiegne/oauth2-client-play-todolist/raw/master/site/oauth2_client_homepage.png)

How to use it ?
==============================

1 . First of all checkout my other projects to retrieve and run the OAuth2 Authorization Server : 

<pre>
- git clone https://github.com/tcompiegne/oauth2-server.git
- cd oauth2-server
- mvn jetty:run (listen on port 8080)
</pre>

and the OAuth2 resource server :

<pre>
- git clone https://github.com/tcompiegne/oauth2-resource-server-play-todolist.git
- cd oauth2-resource-server-play-todolist
- activator "~run 9001"
</pre>

Finally checkout and run the OAuth2 client :

<pre>
- git clone https://github.com/tcompiegne/oauth2-client-play-todolist.git
- cd oauth2-resource-server-play-todolist
- activator run
</pre>

The resource server and the client application require the installation of the play framework :

https://www.playframework.com/ (current version : 2.3.8)

2 . browse the following URL: `http://localhost:9000` and you ready to go !

OAuth 2 in Action
==============================

To access your OAuth 2 protected Todo List, you must singin with the account that is linked with Authorization Server defined (by default) by these parameters :

```xml
  <!-- Default authentication manager (Tests only) -->
	<sec:authentication-manager>
		<sec:authentication-provider>
			<sec:user-service>
				<sec:user name="userTest" password="userTest" authorities="ROLE_USER" />
			</sec:user-service>
		</sec:authentication-provider>
    </sec:authentication-manager>
```
To do so, we will use the Authorization Code Grant Flow :

![oauth2-auth-code-flow] (https://github.com/tcompiegne/oauth2-client-play-todolist/raw/master/site/auth_code_flow.png)

When you click on the "Sign in" button you can see thay you are redirected to the Authorization Server Login page (see the url change) :

![login-page] (https://github.com/tcompiegne/oauth2-client-play-todolist/raw/master/site/login_page.png)

After the login process, you are redirected back to the client application and you can see that "My Todos" link shows up as a profile button with the logout action.

![user-page] (https://github.com/tcompiegne/oauth2-client-play-todolist/raw/master/site/user_page.png)

The "My Todos" page retrieve your todos from the Resource Server thanks to the access_token retrieve after logged in to the authorization server :

![user-todo-list] (https://github.com/tcompiegne/oauth2-client-play-todolist/raw/master/site/user_todo_list.png)

What's next ?
==============================

You can replace the authentication-manager with your own authentication manager like LDAP, Database, WebServices, etc ...
and replace the token store with for example the [couchbase token store](https://github.com/tcompiegne/oauth2-couchbase-token-store "Couchbase Token Store") and create an administration web site to manage your OAuth apps client to get ready for production environments.

Enjoy !
