
---
---

# CONTROL LIBRARY

<p align="justify"> CONTROL LIBRARY es una API desarrollada con Spring Boot con la finalidad de realizar trabajo practico de programación backend, e ir ganando experiencia en el uso de las tecnologías y conceptos usados en su desarrollo.</p>

<p align="justify"> Esta API se encarga de controlar el préstamo de libros en una biblioteca, así como las sanciones relacionadas a los usuarios que no cumplen con la devolución de los libros en el tiempo estipulado.
</p>

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
|[**OpenJDK 17**]("https://jdk.java.net/archive/")|[**Eclipse 2023-12**]("https://www.eclipse.org/downloads/packages/release/2023-12/r")|[**Spring Boot**]("https://spring.io/projects/spring-boot#overview")|
|[**Visual Studio Code**]("https://code.visualstudio.com/download")|[**Thunder Client Ext. VSC**]("https://marketplace.visualstudio.com/items?itemName=rangav.vscode-thunder-client")|[**Workbech 8.0**]("https://dev.mysql.com/downloads/mysql/8.0.html")|
||[**MySQL 8.0**]("https://dev.mysql.com/downloads/mysql/8.0.html")||

<sub>[Volver al índice](#indice)</sub>

---

## INSTRUCCIONES DE INSTALACION

- [Clonar el repositorio desde github.]("https://docs.github.com/es/repositories/creating-and-managing-repositories/cloning-a-repository")

- [Importar el proyecto maven en eclipse.]("https://chuidiang.org/index.php?title=Crear_proyecto_Maven_en_Eclipse#Importar_un_proyecto_maven_existente_en_Eclipse")

- Crear la base de datos (Schema) vacia en MySQL.

    ``` SQL
        CREATE DATABASE db_library;
        USE db_library;
    ```      
    > Nota: Las tablas y otras estructuras se crearán de forma automática al ejecutar la aplicación con la ayuda de JPA.

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
