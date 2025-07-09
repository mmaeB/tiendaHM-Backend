# Backend para Aplicación Web Biblioteca

Proyecto generado usando [Spring Initializr](https://start.spring.io/) 

Backend para la aplicación web de una biblioteca, el frontend está desarrollado el Angular.

Este proyecto es desarrollado como parte del curso <b>Soluciones Web y Aplicaciones Distribuidas</b> con <b>NRC 7385</b>, correspondiente al <b>período UPN 2025-1</b>
- ARQUITECTURA: n-capas
## Versiones

- Project: Maven
- Language: Java
- Spring Boot: 3.4.4
- Packaging: jar 
- Java: 21

## Documentación
La API debe evolucionar según el modelo de madurez de Richardson, revise la documentación de [Richardson Maturity Model](https://martinfowler.com/articles/richardsonMaturityModel.html) según Martin Fowler

### DTO's
En Spring Boot, un DTO (Data Transfer Object) es un objeto simple que se utiliza para transferir datos entre diferentes capas de una aplicación. Los DTOs son esencialmente clases POJO (Plain Old Java Objects) que contienen datos que se deben enviar o recibir en un contexto, como en una solicitud HTTP o entre diferentes servicios de la aplicación.

<b>Ejemplo</b><br>
En nuestra aplicación web para la gestión de ```authores```, se tiene una clase ```Author``` que representa la entidad de la base de datos. Sin embargo, cuando envíes la información del ```author``` al controllador (o a la vista o a otro servicio), es posible que no desees enviar todos los campos de la entidad. En este caso, creamos un DTO llamado ```AuthorDTO``` que contenga solo los campos que se deben enviar.

Podemos hacerlo manualmente, sin embargo, podríamos utilizar librerías que nos ayuden con el mapeo de los DTO y de las entidades, algunas de ellas son:
- [Model Mapper](https://modelmapper.org/)
- [MapStruct](https://mapstruct.org/)

Para incluir <b>Model Mapper</b> consulte [MVN Repository](https://mvnrepository.com/artifact/org.modelmapper/modelmapper), seleccione la versión de interés y agregue la dependencia en el archivo ```pom.xml```
```
    <!-- https://mvnrepository.com/artifact/org.modelmapper/modelmapper -->
    <dependency>
        <groupId>org.modelmapper</groupId>
        <artifactId>modelmapper</artifactId>
        <version>3.2.3</version>
    </dependency>
```

### Hateoas
Nivel 3 - Modelo de madurez de Richarson Hateoas, incluya en el archivo ```pom.xml```
```
    <dependency>
        <groupId>org.springframework.hateoas</groupId>
        <artifactId>spring-hateoas</artifactId>
        <version>2.4.1</version>
    </dependency>
```
## Base de datos
Revise el motor de base de datos y la cadena de conexión a su base de datos en el archivo ```application.properties``` y actualízelo según corresponda. El proyecto está configurado para trabajar con MySQL, para cambiar de motor de base de datos actualice el archivo ```pom.xml```

Las dependencia incluida es:
```
    <!-- Enables MySQL database connection -->
    <!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>9.3.0</version>
    </dependency>
```

y en el archivo ```application.properties``` agregue la cadena de conexión:
```
    # JPA / Hibernate settings
    spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.database=mysql
    spring.jpa.show-sql=false
    spring.jpa.generate-ddl=true
    spring.jpa.hibernate.ddl.auto=update
    
    # Database connection
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/libraryapp
    spring.datasource.username=your_user
    spring.datasource.password=your_password
```