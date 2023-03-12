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

Copiar las variables de entorno del archivo .env a su entorno. Esto con el objetivo de establecer la configuración necesaria para generar JWT y válidar la clave lada de teléfono.

```sh
export $(cat .env | xargs)
```

Luego ejecutar la aplicación

```sh
./gradlew bootRun
```


- Usando docker compose:

En este caso las variables de entorno están en el archivo docker-compose, por lo que no es necesario copiar las del archivo .env.

```sh
./gradlew bootjar
docker-compose up -d
```
Para detener el contenedor, simplemente ejecutar

```sh
docker-compose down
```

2. Luego de ejecutada la aplicación, ingresar a la url:


`http://localhost:8080/swagger-ui/index.html`


Esto le permitirá realizar las pruebas desde la misma documentación de la API, o ver el endpoint que puede llamar
desde su cliente preferido.


## Pruebas automáticas


Se implementaron pruebas unitarias, de integración, y aceptación.

Para ejecutar todas las pruebas, primero asegurese de copiar las variables de entorno ejecutando `export $(cat .env | xargs)`

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


## Diagrama de la solución.


Para dar solución al desafío, se optó por seguir los principios de arquitectura hexagonal y DDD. Teniendo en cuenta esto, se dividió el requerimiento de registrar un usuario en los siguientes 3 servicios de aplicación:


- User Register: encargado de registrar el usuario con su información básica y disparar el evento de UsuarioRegistrado.
  Dado que realiza un cambio de estado en la aplicación, no retorna ninguna respuesta al controlador.


- User Authenticator: Este servicio se ejecuta una vez se produce el evento de Usuario registrado. Se encarga de generar
  el token de usuario y persistirlo junto con el usuario. En este momento también se establece la última fecha de login,
  basado en la fecha de creación del usuario.


- User Finder: Simplemente devuelve toda la información que retorna el endpoint de registro de usuario.


![packages](https://drive.google.com/uc?export=view&id=1E20AEB1PWja7_Pg409lIp6gWYTHF1fL6)


Como se evidencia en la imagen, es en el controlador donde se lanza la acción de registrar el usuario y se espera que el proceso termine para volver a consultar los datos del usuario.


De este modo se puede retornar la información en la misma petición http, se aplica el principio de responsabilidad
única, y por ende se facilitan las pruebas.


A continuación el diagrama de paquetes, que evidencia la solución planteada en el código de la aplicación:


![packages](https://drive.google.com/uc?export=view&id=1q0zQPH4wMPeobnwJoO57x7M79wyHrL3Q)


A partir de este punto se podrían implementar muchas más mejoras al sistema, entre ellas la implementación de CQRS y
la comunicación de la aplicación Back y front mediante un sistema de eventos que no implique esperar a que se complete
todo el proceso y retornar la respuesta en la misma petición.


En caso de tener que retornar una respuesta en la misma petición (como está ahora), también se puede implementar un
mecanismo reactivo y tolerante a los fallos que se se puedan presentar en uno de los 3 servicios de aplicación
descritos anteriormente.


Gracias a la clara separación del dominio de las capas de infraestructura, es posible cambiar la implementación del repositorio H2 a cualquier otro mecanismo de almacenamientos.


Lo descrito en el párrafo anterior también aplica para la implementación del bus de evento, pudiendo reemplazar el de Spring Application por cualquier otro mecanismo como kafka, Rabbit,SQS, etc.
