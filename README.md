# Betting Bull

### Microservices

![architecture](https://firebasestorage.googleapis.com/v0/b/survey-flowmedia.appspot.com/o/Betting%20Bull.png?alt=media&token=ff118fc1-7624-4fbd-90ec-69aefefea897)

# Requirements

In order to run this you need to have:

* [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or better
* [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/)

### How to run

Execute `run-microservices.sh` shell script.

Gateway service will be up and running at port `8080`.
Endpoint `/actuator/routes` will list all available routes.

Swagger documentation is added and available at:
* `localhost:8080/fee/swagger-ui.html`
* `localhost:8080/teams-and-players/swagger-ui.html`