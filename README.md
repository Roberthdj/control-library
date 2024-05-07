
---
---

# CONTROL LIBRARY

<p align="justify"> CONTROL LIBRARY es una API desarrollada con Spring Boot con la finalidad de realizar trabajo practico de programación backend, e ir ganando experiencia en el uso de las tecnologias y conceptos usados en su desarrollo.</p>

<p align="justify"> Esta API se encarga de controlar el prestamo de libros en una biblioteca, asi como las sanciones relacionadas a los usuarios que no cumplen con la devolución de los libros en el tiempo estipulado.</p>

---

## INDICE

* [ESTRUCTURA DEL PROYECTO](#estructura-del-proyecto)
* [TECNOLOGIAS UTILIZADAS](#tecnologias-utilizadas)
* [INSTRUCCIONES DE INSTALACION](#instrucciones-de-instalacion)
* [INSTRUCCIONES DE USO](#instrucciones-de-uso)
* [DESARROLLADO POR](#desarrollado-por)

---

## ESTRUCTURA DEL PROYECTO

### [Ver estructura de la aplicación »](/STRUCTURE.md)

<sub>[Volver al índice](#indice)</sub>

---

## TECNOLOGIAS UTILIZADAS

||||
|:---:|:---:|:---:|
|<a href="https://jdk.java.net/archive/" target="blank" rel="noopener noreferrer">**OpenJDK 17**</a>|<a href="https://www.eclipse.org/downloads/packages/release/2023-12/r" target="blank" rel="noopener noreferrer">**Eclipse 2023-12**</a>|<a href="https://spring.io/projects/spring-boot#overview" target="blank" rel="noopener noreferrer">**Spring Boot**</a>|
|<a href="https://code.visualstudio.com/download" target="blank" rel="noopener noreferrer">**Visual Studio Code**</a>|<a href="https://marketplace.visualstudio.com/items?itemName=rangav.vscode-thunder-client" target="blank" rel="noopener noreferrer">**Thunder Client Ext. VSC**</a>|<a href="https://dev.mysql.com/downloads/mysql/8.0.html" target="blank" rel="noopener noreferrer">**Workbech 8.0**</a>|
||<a href="https://dev.mysql.com/downloads/mysql/8.0.html" target="blank" rel="noopener noreferrer">**MySQL 8.0**</a>||

<sub>[Volver al índice](#indice)</sub>

---

## INSTRUCCIONES DE INSTALACION

- <a href="https://docs.github.com/es/repositories/creating-and-managing-repositories/cloning-a-repository" target="blank" rel="noopener noreferrer">Clonar el repositorio desde github.</a>

- <a href="https://chuidiang.org/index.php?title=Crear_proyecto_Maven_en_Eclipse#Importar_un_proyecto_maven_existente_en_Eclipse" target="blank" rel="noopener noreferrer">Importar el proyecto maven en eclipse.</a>

- Crear la base de datos (Schema) vacia en MySQL.

    ``` SQL
        CREATE DATABASE db_library;
        USE db_library;
    ```      
    > Las tablas y otras estructuras se crearán al ejecutar la aplicación.
- Configurar las variables de entorno  

    - Menu **~>** Run **~>** Run Configurations **~>** seleccionar Environment **~>** Add
        |NOMBRE|CONTIENE|
        |--|--|
        |DBNAME|nombre de la base de datos|
        |DBUSER|usuario de la base de datos
        |DBPASS|password de la base de datos  
        |DBPORT|número del puerto (8080, 80xx)|
        |DBURL|127.0.0.1:3306 ó localhost|
        |SECRET_KEY|hash de 256 bits|

- Correr la aplicación desde eclipse.
    - Explorador de proyectos **~>** clic derecho sobre control-library **~>** Run As **~>** Spring Boot App

<sub>[Volver al índice](#indice)</sub>

---

## INSTRUCCIONES DE USO

### [Ver uso de la aplicación »](/USE.md)

<sub>[Volver al índice](#indice)</sub>

---

## DESARROLLADO POR

<a href="https://github.com/roberthdj" target="blank">
    <img align="center" src="https://avatars.githubusercontent.com/u/120141795?v=4" alt="roberthdj-rodriguez" height="125" width="125"/>
    <br>
    «Roberth de Jesus Rodriguez Castro»
</a>

<sub>[Volver al índice](#indice)</sub>

---
---
