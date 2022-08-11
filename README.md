# logistics-service
Microservice to facilitate logistics for the company

The instructions to run the code:
- mvn clean install
- run the mysql scripts that are in src/main/resources/sql in your local mysql instance
- run mvn spring-boot:run

Ensure that the mysql user & password align with your local mysql instance

The swagger-ui can be found on http://localhost:8080/swagger-ui.html

There is an extra assumption I made:
The user can only input two locations when calculating the best route.
The most optimal route between two points is the straight line between those two points.

Improvements, since I didn't have much time to complete this project, I will propose some improvements:
Dockerise the project and introduce a docker-compose yaml file that will spin up a container for the spring boot project and another one for mysql (connected via a bridge network)
During the mysql container initialization, run the mysql scripts so that there are tables in the database before the spring boot applications finishes initialization.
For the GET /routes API, I would take just the locationId of the two locations the user wants to calculate the route for instead of acception the entire objects
I would also make general code refactor to make the code maintainable.