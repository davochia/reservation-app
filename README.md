# Neagen Oy reservation system


### Introduction
This web application is used for managing appointments in a simple reservation system. 
It consists of two parts, a REST API for the backend and a user interface. 
The REST API endpoint (`/appointments`) returns a list of the appointments currently in the system, in JSON format.
The user interface (`/ui`) shows the appointments in list format.

### Architecture
The java package com.neagen.devtest.rest contains the (rudimentary) REST API implementation. The API can be accessed at `http://localhost:8080/appointments`
The client-side user interface is implemented in `src/main/resources/templates/ui.html` and it can be accessed at `http://localhost:8080/ui` It uses Vue.js and Bootstrap as a framework. The welcome page `http://localhost:8080` shows an elementary login screen.

### Todo
The customer requested several improvements for this application. Can you please prioritize them, according to your available time and skills, and implement the necessary changes? 
You can make any changes required to the existing code and you can use external libraries. _You do not need to implement all of them!_

* [Bug] Adding new appointments does not work (and fails silently!)
* [New feature] Delete existing appointments
* [New feature] Edit appointments (change dates and description)
* [New feature] Sort the appointments (by start date and/or description)
* [Improvement] Styling the UI
* [Improvement] Show the appointments in a calendar view (Google Calendar -style)
* [New feature] Support for multiple users. Each user shall see only their own appointments (Hint: use the "name" parameter)
* [New feature] Database persistence. Use an in-memory database (Sqlite, H2) to store and retrieve the appointments. 

### Reference Documentation

Prerequisites:
* Java JDK version 8 or higher. To check, run `java -version`

Unpack the project, build and run:

* Building the project: `./gradlew build`
* Running the project: `./gradlew bootRun`

For further reference, please consider the following links:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.0/gradle-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#web)
* [Vue.js](https://vuejs.org/)
* [Bootstrap](https://getbootstrap.com/)
