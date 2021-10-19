
## Simple and Sample Spring Boot Kotlin Project

Dockerized demo project using Kotlin, Spring Boot, Embeded H2, JPA and Hibernate.

## Requirements
1.  Java - 1.11.x
2.  Gradle- 3.x.x
3.  Docker- 5.x.x

**Running the App**
Type the following command in your terminal to run the app -

     ./gradlew bootRun
    
**Build and Run with Docker**
Build the project with gradle:

    ./gradlew build

Build Docker and run docker with docker-compose

    docker build . -t spring-boot-kotlin-demo

    docker-compose up -d

The app will start running at  [http://localhost:8099](http://localhost:8099/).

## Rest APIs

The app defines following for APIs.


    GET /users?user=G-khan

    POST /users
    {
	    "name":"Gökhan",
	    "username":"G-khan"
    }
