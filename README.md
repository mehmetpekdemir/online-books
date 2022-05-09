# Online Books Project

### What are microservices ? 

Microservices - also known as the microservice architecture - is an architectural style that structures an application as a collection of services that are
 - Highly maintainable and testable
 - Loosely coupled
 - Independently deployable
 - Organized around business capabilities
 - Owned by a small team
 
The microservice architecture enables the rapid, frequent and reliable delivery of large, complex applications. It also enables an organization to evolve its technology stack.

Also see : https://microservices.io/


### What is the Hexagonal Architecture ?

The hexagonal architecture was invented by Alistair Cockburn in an attempt to avoid known structural pitfalls in object-oriented software design, such as undesired dependencies between layers and contamination of user interface code with business logic, and published in 2005.

> A timeless goal of sofware engineering has been to separate code that changes frequently from code that is stable.
> 
> ~ James Coplien / Lean Architecture

We recommend Hexagonal Architecture for those who want to write clean, maintainable, well-defined boundary context, well-tested domain and decoupling business logic from technical code.

Also see : https://leanpub.com/get-your-hands-dirty-on-clean-architecture

### Tech Stack 
 - Java 11
 - Spring Framework(Spring Boot , Spring Cloud , Spring Data)
 - REST-API
 - Config-server
 - Eureka-server & Eureka-client
 - Feign client
 - Mongo DB
 - Docker
 - Lombok

### Design 
![DesignPhoto](https://github.com/mehmetpekdemir/online-books/blob/master/docs/design/microservice-design.png)
![BookDesign](https://github.com/mehmetpekdemir/online-books/blob/master/docs/design/book-design.png)
![CustomerDesign](https://github.com/mehmetpekdemir/online-books/blob/master/docs/design/customer-service-design.png)
![OrderDesign](https://github.com/mehmetpekdemir/online-books/blob/master/docs/design/order-service-design.png)
![StatisticsDesign](https://github.com/mehmetpekdemir/online-books/blob/master/docs/design/statistics-design.png)
![StockDesign](https://github.com/mehmetpekdemir/online-books/blob/master/docs/design/stock-design.png)

### Test Coverage 
![Book](https://github.com/mehmetpekdemir/online-books/blob/master/docs/test/book-service.png)
![Customer](https://github.com/mehmetpekdemir/online-books/blob/master/docs/test/customer-service.png)
![Order](https://github.com/mehmetpekdemir/online-books/blob/master/docs/test/order-service.png)
![Statistics](https://github.com/mehmetpekdemir/online-books/blob/master/docs/test/statistics-service.png)
![Stock](https://github.com/mehmetpekdemir/online-books/blob/master/docs/test/stock-service.png)

### Requirements

For building and running the application you need:
- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or newer . 
- [Maven](https://maven.apache.org)
- [Docker](https://www.docker.com/)
- [Lombok](https://projectlombok.org/)

### Build & Run

```
  docker-compose -f "YOUR_LOCATION\docs\docker-compose.yml" up -d 
```

```
  mvn clean install 
```

After that run sequentially: 
```
  config-server 
  eureka-server
  customer-service
  book-service
  order-service
  stock-service
  statistics-service
  
```

### Ports

![Eureka](https://github.com/mehmetpekdemir/online-books/blob/master/docs/eureka.png)
