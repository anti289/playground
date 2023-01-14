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

## changelog
- moved from Lombok to Java17 records where applicable
- added endpoint to add a URL that should be loaded regularly for a user

## TODO
### planning
- make plans: what happens if there is a change, how to notify a user?
- check content from request to be different in specific parts, a timestamp will ruin the plans
### implementation
- security
    - start with an adaptable solution like reading the username from http headers
- implement scheduler to run over urls
- use Java17 httpclient
- use gradle
  - try to keep maven for reference
- create openapi yaml
- export postman collection
- tests
- store data in nosql db
- setup docker-compose for nosql
- get endpoint for fetched versions
#### new: ALGORITHM IMPL
- add a generic endpoint to send a name and a value to (Strings)
- a registry sends the value to the handler for the given name
- implement handlers to compute a response for the given value
- let the handlers compute if a given number can be divided by x without rest
- implement performance test for handlers
- improve performance of the handlers and learn from it

_Copyright 2023 Andreas Timm_
