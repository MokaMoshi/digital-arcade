Proyecto: Digital Arcade (Backend), El sistema está construido con Spring Boot y gestiona toda la lógica de inventario, categorías y transacciones de la tienda.

Tecnologías Utilizadas Java 21 (JDK)
Spring Boot 3.x
MySQL (Base de datos principal)
JPA / Hibernate (Para el mapeo de tablas)
Lombok (Para mantener el código limpio)
Maven (Gestor de dependencias)

Requisitos para correr el proyecto Tener instalado MySQL Server.
Crear la base de datos localmente:

SQL CREATE DATABASE digital_arcade_db;

Cómo ejecutarlo Clonar el repositorio.
Abrir con VS Code (asegurarse de tener las extensiones de Java instaladas).
Configurar la variable JAVA_HOME si es necesario.
Ejecutar el proyecto

Services: Lógica de negocio y validaciones.
Repositories: Interfaces que conectan con JPA.
Modelos / Entidades: Mapeo de las tablas de la base de datos.
DTOs: Objetos para transferencia de datos limpia.

Microservicios y Monitoreo Logs: Implementamos @Slf4j en los servicios y controladores para rastrear las operaciones y errores en la consola.

Conectividad: El sistema se conecta con el microservicio de Usuarios en el puerto 8081 para validar la existencia de clientes antes de cada transacción. necesito escribir esto como si lo hubiera escrito un estudiante
