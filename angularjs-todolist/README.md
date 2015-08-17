OAuth2 AngularJS TodoList Application Client
=================================

![oauth2-client-angularjs-todolist](https://github.com/tcompiegne/oauth2-client-samples/raw/master/angularjs-todolist/site/oauth2_angularjs_client_homepage.png)


This is a simple Web Application Client that provides a Todo List Manager fetching Resource Provider protected by OAuth 2. The application is based on AngularJS. 

You can check the [parent project](https://github.com/tcompiegne/oauth2-client-samples) to get more details about the other parts of this project (OAuth2 Authorization Server and OAuth Resource Providers).

This project is generated with [yo angular generator](https://github.com/yeoman/generator-angular) version 0.12.1.

## Client side
* [AngularJS](https://angularjs.org/)
* [Twitter bootstrap](http://getbootstrap.com/)
* [HTML5](http://www.w3.org/TR/html5/) and [CSS3](http://www.w3schools.com/css/css3_intro.asp)

## Server side
* [Grunt](http://gruntjs.com/)


## Run the application

* Install [NodeJS](https://nodejs.org/)
* It comes with [npm](https://www.npmjs.com/)
* Install [Grunt](http://gruntjs.com/) : npm install -g grunt-cli
* Install and run the application :

```
$ git clone https://github.com/tcompiegne/oauth2-client-samples
$ cd oauth2-client-samples/angularjs-todolist
$ npm install && bower install
$ grunt serve
...
(app starts and listen on port 9000)

browse the following URL: http://localhost:9000
```

Run `grunt` for building and `grunt serve` for preview.

## Testing

Running `grunt test` will run the unit tests with karma.

## OAuth2 Implicit Flow

Compare to the other projects wich use OAuth2 Authorization Code Flow, this project use the OAuth2 Implicit Flow which is more appropriate for this kind of projects (client projects) :

![oauth2-implicit-flow](https://github.com/tcompiegne/oauth2-client-samples/raw/master/angularjs-todolist/site/implicit_flow.png)


