# Cheese Manager

Cheese Manager is a ReST API implemented in Spring Boot.

The API definition is based on OpenAPI 3.0.0 standards.

The overall purpose is to provide a CRUD API for managing and persisting Cheeses.

## Build
mvn clean install

Generate ReST API code from OpenAPI definitions in src/main/resources/CheeseManager.yaml

The OpenAPI generator is run in the compile phase, so if the CheeseManager.yaml changes, do mvn clean compile to regenerate the code.

## Run

mvn spring-boot:run

Swagger Docs at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) - or whatever the host:port is

## Features

### Request logging
Incoming requests including their payloads are logged via this Spring filter: RequestLoggingFilterConfig.java

Logback config for this is in logback.xml

Based on the blog post at [https://www.baeldung.com/spring-http-logging](https://www.baeldung.com/spring-http-logging)


### Logging Actuator Endpoint
Logging level can be varied at runtime - based on the blog post at [https://www.baeldung.com/spring-boot-changing-log-level-at-runtime](https://www.baeldung.com/spring-boot-changing-log-level-at-runtime)

To view all the current logging levels: HTTP GET http://{host:port}/actuator/loggers

To change the level for a given logger, send an HTTP POST like the following:

`curl -i -X POST -H 'Content-Type: application/json' -d '{"configuredLevel": "DEBUG"}' http://{application host:port}/actuator/loggers/org.springframework.web.filter.CommonsRequestLoggingFilter`
