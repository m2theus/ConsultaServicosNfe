# Spring Boot


## Steps to setup

**1. Create Mysql database**
```bash
create database consulta
```

**2. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**3. Build and run the app using maven**

```bash
cd backend
mvn clean install
mvn spring-boot:run
```