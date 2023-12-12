# Manage-User Application

## Descripción
Manage-User es una aplicación Spring Boot diseñada para la gestión de usuarios y autenticación utilizando JWT (JSON Web Token). Esta aplicación facilita el registro y la autenticación de usuarios, y gestiona la información relacionada de forma segura.

## Requisitos Previos
- Java 11 o superior.
- Gradle 8+ (para ejecución y gestión de dependencias).

## Configuración
La aplicación utiliza H2, un sistema de gestión de bases de datos en memoria, ideal para pruebas y desarrollo. Las configuraciones relevantes, incluyendo las de JWT y H2, están definidas en `src/main/resources/application.properties`.

## Colección de Postman
En el directorio raíz del proyecto se incluye una colección de Postman llamada `creacion_usuario.postman_collection.json`. Esta colección contiene ejemplos de solicitudes para facilitar la prueba y el uso de la aplicación.

## Ejecución de la Aplicación

### Con Gradle
1. Abre una terminal y navega hasta el directorio raíz del proyecto.
2. Ejecuta el siguiente comando para iniciar la aplicación:
   ```
   gradle bootRun
   ```
   Esto iniciará la aplicación en el puerto por defecto 8080.

### Con JAR Ejecutable
1. Construye el proyecto con Gradle para generar el archivo JAR:
   ```
   gradle build
   ```
2. Navega hasta `build/libs` donde se encuentra el JAR compilado.
3. Ejecuta el siguiente comando para iniciar la aplicación:
   ```
   java -jar manage-user-0.0.1-SNAPSHOT.jar
   ```

## Uso de la Aplicación
Una vez iniciada, se puedes acceder a los endpoints de la aplicación a través de un cliente API como Postman o directamente desde tu aplicación cliente.

## Pruebas
Las pruebas unitarias y de integración se pueden ejecutar con el siguiente comando:
```
gradle test
```

## Acceso a la Consola H2
Para visualizar y gestionar la base de datos en memoria H2, visita `http://localhost:8080/h2-console` después de iniciar la aplicación.

---