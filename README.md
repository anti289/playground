# Webservice Change Detection backend

Service to retrieve data from a webservice regularly and detect changes in the response.

see REST endpoints OpenApi documentation in _/src/main/resources/static/openapi.yaml_

check the postman collection in _/postman/_

configuration to be done in _/src/main/java/resources/application.properties_

database: h2

## run
1. use Java 17 (LTS)
2. prepare `mvnw clean install`
3. execute `mvnw spring-boot:run`


## TODO
- security
- add endpoint to add a url that should be loaded regularly
- what happens if there is a diff?
- implement scheduler to run over urls
- use Java17 records and httpclient
- migrate to gradle
- openapi
- postman
- tests

_Copyright 2022 Andreas Timm_
