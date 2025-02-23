﻿#  CPU test
## :bulb: ***Project description***

    Simple version of Web-application. Allow get/add data from/to database.

## :scroll: ***Project structure***

    The project has an N-Tier Architecture.

+ *Dao*
+ *Service*
+ *Controller*

## :exclamation: ***Features***

+ *All CRUD operations with teacher and student*
+ *Attach/remove student to/from teacher and vice versa*
+ *Find teacher/student by firstName and lastName*
+ *Get student by teacher and vice versa*
+ *Get operations include pagination and sorting*

## :books: ***Technologies***

+ *Java 17*
+ *MySql*
+ *Maven*
+ *Stream API*
+ *Hibernate*
+ *Spring WEB*
+ *Spring Boot*
+ *Spring Boot Jpa*
+ *Lombok*
+ *REST*
+ *SOLID principles*
+ *Swagger*

## :desktop_computer: ***Quickstart***

1. Fork this repository
2. Copy link of project
3. Create new project from Version Control
4. Set the necessary parameters in resources/application.properties
```java
    spring.datasource.driverClassName=YOUR_DRIVER
    spring.datasource.url=YOUR_DATABASE_URL
    spring.datasource.username=YOUR_LOGIN
    spring.datasource.password=YOUR_PASSWORD
```

5. Run project
6. Test all controllers by swagger. Follow next link :

http://localhost:8080/swagger-ui/#/
