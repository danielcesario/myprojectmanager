# Welcome to MyProjectManager

Hi, my name is Daniel Cesario, and I made this simple project to practice same concepts and tools related to code
quality and tests.

I wanted to create a project with 100% test coverage and also able to validate this coverage ratio. On this project I
have used:

- **Spock Framework** with Groovy to Unit Tests.
- **Jacoco**'s Gradle plugin to provide reports about tests coverage.
- **Sonar** to analyse if the best practices have been following.

# Project

## What you have to install?

- Docker
- Docker Compose

## What you have to do to run the project?

Before run the application, you need to execute the docker-compose's script with this follow command to create the
container for: PostgreSQL, and PgAdmin, and Sonar:

    docker-compose up -d

After that you need to execute this follow gradle command to start the application:

    ./gradlew bootRun

## Jacoco's Coverage Report

The build command generate the coverage report in HTML on folder:
**build/reports/jacoco/test/html/idnex.html**

But, you can also generate the report using the task **jacocoTestReport** of Gradle with command:

    ./gradlew jacocoTestReport

## Sonar

Firstly, you need to create a token to the application can connect on Sonar server, access the Sonar by following url:

    http://localhost:9000

Then, do the login with following credentials:

    user: admin
    #password: admin

Probably you have to change the default password.

On the Sonar panel, click on your user icon, after that click on **"My Account"**. Choose the **"Security"** tab,
finally, generate a token to use in the application, not forget to copy the value to paste on the **build.gradle** file,
in the task **sonarqube** at the property **sonar.login**. Now you can run the Sonar Task with following command:

    ./gradlew sonarqube
