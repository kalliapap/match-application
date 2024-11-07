# Match Application

Welcome to the Match Application repository

### Preparing the dockerized development environment
Run the following commands from the root directory

`docker compose up --build`

The following containers are created:

| Port  | Container    | Description            |
|-------|--------------|------------------------|
| 25432 | postgresql   | Postgres db port       |
| 8080  | match-app    | Match application port |

### Build and deploy without docker
`cd match-server` folder and run:

    match-application/match-server$ mvn liquibase:update -Ppostgres 

then return to the root directory and run:

`mvn clean install`

and `cd match-server` and start the Spring Boot Application by running the 

`mvn spring-boot:run`

### Swagger

Find Swagger documentation page here:

http://localhost:8080/openapi/swagger-ui/index.html#/

