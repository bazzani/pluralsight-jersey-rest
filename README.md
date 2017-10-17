## Synopsis

The purpose of this project is to create a RESTful sever web application and client using the Jersey library.

## Motivation

I have created this project to help solidify my understanding of the Jersey library to create RESTful servers and clients after watching the Pluralsight course "RESTFul Services in Java using Jersey" @ https://app.pluralsight.com/library/courses/restful-services-java-using-jersey/table-of-contents.

## Getting started
This Java EE project was created using Adam Bien's minimalist maven archetype, which can be found at:
- http://www.adam-bien.com/roller/abien/entry/essential_javaee_7_pom_xml

# Sonar Report
The Sonar report for this project can be found @
* https://sonarcloud.io/dashboard?id=com.pluralsight.websockets%3Awebsockets-chat-app

Sonar maven command
```
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar \
    -Dsonar.host.url=https://sonarcloud.io \
    -Dsonar.organization=bazzani-github \
    -Dsonar.login=b5c80850abb54d57f065ca7e5f7159d8daaaedf6
```
