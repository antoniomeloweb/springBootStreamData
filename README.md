The following app uses the technologies:

Spring Boot / Spring Data / Swagger Api documentation / MySql Database

A simple DDL is present in the project (arquivo schema.sql)
there´s some data for testing purpose inside the folder (arquivo data.sql)

To run the app, just execute the following class:

com.cartao.Application

A table in the MySql Database will be created:

The application.properties file is configured the datasource:

spring.datasource.url=jdbc:mysql://localhost:3307/db_cartao
spring.datasource.username=root
spring.datasource.password=usbw

For a quick startup, I recommend the following distribution of mysql environment:

Usb Web Server, available for download at:

http://www.usbwebserver.net/downloads/USBWebserver%20v8.6.zip

API

To access the documentation API Access:
http://localhost:8080/cartao/api/swagger-ui.html


