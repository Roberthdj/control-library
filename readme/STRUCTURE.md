
---
# «ESTRUCTURA DE LA APLICACION»
---
## Estructura general

-   dev.breafcase.library

    - [core](#core)
        - controller
        - dto
        - entity
        - repository
        - service

    - [error](#error)
        - exception
        - handler

    - [security](#security)
        - auth
        - config
        - jwt

    - [utils](#utils)

    - [validation](#validation)

##### [« Volver a la página principal»](/README.md)

---

## core

<details> 
<summary></summary>
<p align="justify"> 
Aquí encontramos las estructuras basicas o capas para crear una API como controladores, repositorios, servicios y sus implementaciones y los dto creados para el intercambio de datos de la aplicación.
</p>

#### controller

<p align="justify"> Aquí estan contenidas las clases responsables de manejar las solicitudes HTTP entrantes y enviar respuestas.</p>

#### dto

<p align="justify"> En este paquete hallamos los DTO (Data Transfer Object) utilizados para transferir datos entre la capa de presentación (controller) y la capa de servicio (service) de la aplicación.</p>

#### entity

<p align="justify"> En entity como su nombre indica hallamos las clases (entidades) que representan las tablas en la base de datos relacional. </p>


#### repository

<p align="justify"> En este paquete encontramos las clases asociadas a la capa de repositorio que es la responsable de acceder a la base de datos y realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar).</p>

#### service

<p align="justify"> Aquí se encuentran las clases asociadas a la capa de servicio (interfaz y su implementacion) que contiene la lógica de negocio y es responsable de manipular los datos recuperados de la capa de repositorio. </p>
</details>

<sub>[Volver arriba](#estructura-general)</sub>

---

## error

<details> 
<summary></summary>
<p align="justify"> 
El paquete error se encarga del manejo de las excepciones las cuales fueron personalizadas con el fin de categorizarlas en tres grupos principales ademas contiene un manejador de errores para configurar las respuestas a entregar.</p>

#### exception

<p align="justify">  
Compuesta por tres clases que extienden de RuntimeException donde cada clase se hace cargo de un tipo de excepción especifico.</p>

|||
|--|--|
|GeneralServiceException.java|INTERNAL SERVER ERROR|
|NotFoundException.java|NOT FOUND|
|ValidateFieldsException.java|BAD REQUEST|

#### handler

<p align="justify">  
Contiene una clase que extiende de ResponseEntityExceptionHandler, esta se encargará de recibir una excepcion personalizada o no y la procesará adecuadamente devolviendo la respuesta que se mostrará al cliente.
</p>
</details>

<sub>[Volver arriba](#estructura-general)</sub>

---

## security

<details> 
<summary></summary>
<p align="justify"> 
Aquí encontramos un conjunto de clases relacionadas a la seguridad de la aplicacion, estas se encargaran de crear, validar y autenticar los datos y credenciales para que un usuario puedan hacer uso de la API. 
</p>

#### auth

<p align="justify"> 
Tiene una estructura similar a core, aquí encontramos entidades, controladores, repositorios, servicios etc. pero en esta solo se manejaran datos y procesos relacionados al registro y validación de un usuario generando un token válido para que este pueda hacer uso de la aplicación o restringiendo su acceso.
</p>

#### config

<p align="justify">  
En este paquete se encuentran las clases donde se realizan las configuraciones necesarias para implementar las validaciones y autenticaciones requeridas para que un usuario pueda ingresar, esta consta de dos clases ApplicationConfig y SecurityConfig, en la primera encontramos el metodo AuthenticationProvider que provee el objeto Authentication que necesita el metodo AuthenticationManager para validar las credenciales proporcionadas por un usuario durante el proceso de inicio de sesión y decidir si se otorga o se deniega el acceso, tambien esta metodo encargado de encriptar la contraseña Y en la segunda clase por medio de SecurityFilterChain se define un conjunto de métodos para aplicar filtros de seguridad a una solicitud HTTP entrante.
</p>

#### jwt

<p align="justify">  
Como su nombre indica en este paquete se encuentran la clase JwtAuthenticationFilter aquí se define la lógica para procesar una solicitud HTTP y aplicar cualquier lógica de filtrado necesaria y JwtService donde se definira la logica para manejar la generación, validación y gestión de tokens en la aplicación.
</p>
</details>

<sub>[Volver arriba](#estructura-general)</sub>

---

## utils

<details> 
<summary></summary>
<p align="justify"> 

</p>
</details>

<sub>[Volver arriba](#estructura-general)</sub>

---

## validation

<details> 
<summary></summary>
<p align="justify"> 

</p>
</details>

<sub>[Volver arriba](#estructura-general)</sub>

---
##### [« Volver a la página principal»](/README.md)
---