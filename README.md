# MovieApp - Spring Boot & Angular
This repository contains a full-stack movie management application that consists of a Spring Boot backend and an Angular frontend.

# Prerequisites:
  - **Java JDK 21** 
  - **Maven** (for building the Spring Boot application)
  - **Node.js and npm**
  - **Angular CLI** (install globally using `npm install -g @angular/cli`)
  - **MySQL Server**


# Configurations:
  *Update Application Properties:*
  *In the `springboot` project directory* 
      --edit `src/main/resources/application.properties` with your MySQL configuration:
      --spring.datasource.url=jdbc:mysql://localhost:3306/moviedb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
      --spring.datasource.username=myuser
      --spring.datasource.password=mypassowrd

# Build and Run the Spring Boot Application
    -Open a terminal and navigate to the `springboot` directory.
    -Build the project using Maven:
       mvn clean package
    -Run the project using:
      mvn spring-boot:run
    -After Running the database created and two users added: 
        ADMIN:
          username:  amdin
          passwrod:  admin123
        USER:
          username:  user
          passwrod:  user123
    -The backend will be available at http://localhost:8080

# Build and Run the Spring Boot Application
    -Open a cmd as an admnistrator and navigate to the `angular` directory.
    -Install npm dependencies:
       npm install
    -Run the project using:
      ng serve --open
    -The Angular app will open in your default browser at http://localhost:4200:
      -Login with one of Above users:
        --ADMIN: will route to Admin Dashboard
            --Admin Dashboard: Allows searching for movies (via OMDB), adding movies to the database, and deleting movies.
        --USER: will route to User Dashboard
            --User Dashboard: Allows searching for movies and viewing the list of movies stored in the database.


  
