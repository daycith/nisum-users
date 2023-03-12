# Nisum Users

## Tecnologías

- Java 17
- Spring Boot 3.0.4
- H2
- Gradle
- Spring Events

## Prueba de la aplicación

1. Ejecutar

La aplicación se puede ejecutar mediante una de las siguientes opciones:

- Ejecución en la máquina host:
```sh
 ./gradlew bootRun
```
- Usando docker:

```sh
 ./gradlew bootjar
```

```sh
 docker build -t nisum_users .
```

```sh
 docker run -d -p 8080:8080 nisum_users
```
 
2. Luego de ejcutada la aplicación, ingresar a la url:

```sh
http://localhost:8080/swagger-ui
```

Esto le permitirá realizar las pruebas desde la misma documentación de la API, o ver los endpoints que puede llamar desde su cliente preferido.

## Pruebas automáticas

Se implementaron pruebas unitarias, de integración y aceptación.

Comando para ejecutar todas las pruebas:
```sh
./gradlew test
```

## Script de Base de datos

```roomsql
drop table if exists phones cascade;
drop table if exists users cascade;
create table phones (user_id varchar(255) not null, city_code varchar(255), country_code varchar(255), number varchar(255));
create table users (id varchar(255) not null, created timestamp(6), email varchar(255), isactive boolean, last_login timestamp(6), modified timestamp(6), name varchar(255), password varchar(255), token varchar(255), primary key (id));
````

El archivo se encuentra en la ruta src/main/resources/data.sql.

La creación de las tablas ya se realiza automáticamente , por lo que no es necesario ejecutarlo.





