
---
# `«ESTRUCTURA DE LA APLICACION»` 
---
## `Estructura general`

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

## `core`

<details> 
<summary></summary>
<p align="justify"> 
Aquí encontramos las estructuras basicas o capas para crear una API como controladores, repositorios, servicios y sus implementaciones y los dto creados para el intercambio de datos.
</p>

### `controller`

<details>
<summary></summary>
<p align="justify"> Aquí estan contenidas las clases responsables de manejar las solicitudes HTTP entrantes y enviar respuestas.</p>
</details>

### `dto`

<details>
<summary></summary>
<p align="justify"> En este paquete hallamos los DTO (Data Transfer Object) utilizados para transferir datos la capa de presentación (controller) y la capa de servicio (service) de la aplicación.</p>
</details>

### `entity`

<details>
<summary></summary>
<p align="justify"> En entity como su nombre indica hallamos las clases (entidades) que representan las tablas en la base de datos relacional. </p>
</details>

### `repository`

<details>
<summary></summary>
<p align="justify"> Aquí hallamos las clases asociadas a la capa de repositorio que es la responsable de acceder a la base de datos y realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar).</p>
</details>

### `service`

<details>
<summary></summary>
<p align="justify"> Aquí se encuentran las clases asociadas a la capa de servicio (interfaz y su implementacion) que contiene la lógica de negocio y es responsable de manipular los datos recuperados de la capa de repositorio. </p>
</details>

</details>

<sub>[Volver arriba](#estructura-general)</sub>

---

## `error`

<details> 
<summary></summary>
<p align="justify"> 

</p>

### `exception`

<details>
<summary></summary>
<p align="justify">  </p>
</details>

### `handler`

<details>
<summary></summary>
<p align="justify">  </p>
</details>

</details>

<sub>[Volver arriba](#estructura-general)</sub>

---

## `security`

<details> 
<summary></summary>
<p align="justify"> 

</p>

### `auth`

<details>
<summary></summary>
<p align="justify">  </p>
</details>

### `config`

<details>
<summary></summary>
<p align="justify">  </p>
</details>

### `jwt`

<details>
<summary></summary>
<p align="justify">  </p>
</details>

</details>

<sub>[Volver arriba](#estructura-general)</sub>

---

## `utils`

<details> 
<summary></summary>
<p align="justify"> 

</p>
</details>

<sub>[Volver arriba](#estructura-general)</sub>

---

## `validation`

<details> 
<summary></summary>
<p align="justify"> 

</p>
</details>

<sub>[Volver arriba](#estructura-general)</sub>

---
##### [« Volver a la página principal»](/README.md)
---