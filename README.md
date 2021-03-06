# Welcome to MyProjectManager

Hi, my name is Daniel Cesario, and I made this simple project to practice same concepts and tools related to code
quality and tests.

I wanted to create a project with 100% test coverage and also able to validate this coverage ratio. On this project I
have used the jacoco's Gradle plug in to help me with this validation and also to provide reports about tests coverage
of classes and methods. I've made the unit tests using the Spock Framework with Groovy.

# Project

## What you have to install?

- Docker
- Docker Compose

## What you have to do to run the project?

Before run the application, you need to execute the docker-compose's script with this follow command to create the
PostgreSQL container and also the PgAdmin container:

    docker-compose up -d

After that you need to execute this follow gradle command to start the application:

    ./gradlew bootRun

## Jacoco's Coverage Report

The build command generate the coverage report in HTML on folder:
**build/reports/jacoco/test/html/idnex.html**

But, you can also generate the report using the task **jacocoTestReport** of Gradle with command:

    ./gradlew jacocoTestReport