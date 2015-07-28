About TodoList OAuth2 Client
=================================

![oauth2-client-play-todolist] (https://github.com/tcompiegne/oauth2-client-samples/playframework-todolist/raw/master/site/oauth2_play_client_homepage.png)

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
