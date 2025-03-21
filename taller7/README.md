# TALLER DE MICROSERVICIOS

En este taller se implementó un sistema de publicación de posts de hasta 140 caracteres, similar al funcionamiento de Twitter. El sistema está construido utilizando Quarkus, un framework de Java diseñado para aplicaciones nativas en la nube. 
Se ha implementado como un monolito inicialmente, luego dividido en tres microservicios independientes utilizando AWS Lambda y AWS Cognito.

## Diseño de la aplicación 

La aplicación está diseñada para cumplir con los requisitos especificados en el enunciado del taller y proporcionar una experiencia de usuario fluida y satisfactoria.
El primer paso para el desarrollo de este laboratorio, fue la implementación de un monolito haciendo uso del
framework Quarkus.  A continuación, se describen los principales componentes y características de la aplicación:

- Se implementaron tres entidades fundamentales para el sistema: `Post`, `Stream` y `User`. 

- La clase `Post` representa cada publicación realizada en el sistema, con atributos que incluyen el propietario y el contenido del post. 

- La clase `Stream` encapsula una colección de posts, ofreciendo métodos para agregar y obtener posts. 

- La clase `User` proporciona la estructura necesaria para representar a los usuarios del sistema, incluyendo atributos como el nombre de usuario y la contraseña. Estos objetos `User` son fundamentales para identificar a los propietarios de los posts.

- La clase `PostController` es un controlador REST diseñado para manejar las solicitudes relacionadas con la entidad `Post`.
  Dentro de esta clase, se encuentra el método `savePost`,  el cual se encarga de recibir las solicitudes de los usuarios para publicar posts en el sistema y guardar la información en la capa de persistencia correspondiente. 

- La clase `StreamController` es un controlador REST diseñado para manejar las solicitudes relacionadas con la obtención de posts desde el sistema. Esta clase incluye el método `getPosts`, el cual se encarga de recuperar los posts almacenados en el sistema y devolverlos en formato JSON como respuesta a la solicitud.

- La clase `UserController` es un controlador REST diseñado para manejar las solicitudes relacionadas con la gestión de usuarios en el sistema

-  Para el monolito en Quarkus, se implementó la capa de persistencia en las clases Services de cada uno de los controladores. Para el despliegue en AWS, se hace uso de una base de datos Mongo.

## Guía de Inicio

Las siguientes instrucciones le permitirán descargar una copia y ejecutar la aplicación en su máquina local.

### Prerrequisitos

- Java versión 8 OpenJDK
- Maven
- Git

1. Ubíquese sobre el directorio donde desea realizar la descarga y ejecute el siguiente comando:

```shell script
  git clone https://github.com/juliandtrianar/AREP-2025-1.git
```
2. Navegue al directorio del proyecto:

```shell script
  cd taller7
```

3. Ejecute el siguiente comando para compilar el código:

```shell script
  mvn compile
```

4. Para iniciar el servidor, ejecute el siguiente comando:
```shell script
  mvn compile quarkus:dev
```

Debería ver algo así en consola:

![image](https://github.com/user-attachments/assets/0a01dee4-420e-4235-9f3e-4e6b0668dadb)



## Probando la Aplicación.

Ingrese a la siguiente URL para ingresar a el cliente: 
```
http://localhost:8080/index.html
```
![image](https://github.com/user-attachments/assets/87c0cff7-afcb-4a3e-95a7-3fa99b2faf49)

Ingrese la mensaje a postear:

![image](https://github.com/user-attachments/assets/9979ca1d-8579-4f31-80a4-fe793918858e)


De clic en el botón `Postear`,  podrá observar que los mensajes se muestran en la parte inferior.
![image](https://github.com/user-attachments/assets/154ad52c-7f07-4e0e-8bd2-17388a43d4b0)


# Despliegue en Amazon Web Services

A continuación, se describe la arquitectura de la aplicación en Amazon Web Services (AWS). La aplicación se despliega en la infraestructura de la nube de AWS para garantizar escalabilidad, disponibilidad y seguridad.

## Arquitectura

![image](https://github.com/user-attachments/assets/a21fa032-da64-4028-8af9-b41b22664249)


## Funciones Lambda

Se desplegaron dos microservicios utilizando funciones Lambda de AWS. Estos microservicios establecen conexión con una base de datos `MongoDB` para almacenar y gestionar los datos de la aplicación.
Para cada uno de los microservicios, se compiló el proyecto ubicado en la rama `cloud` del repositorio para generar un JAR con todas las dependencias necesarias para su despliegue en AWS Lambda.

`post-function`: Esta función Lambda implementa la lógica para almacenar los posts en la base de datos `MongoDB`. Cuando se invoca, recibe un nuevo post como entrada y lo guarda en la base de datos para su posterior recuperación y visualización. Es responsable de asegurar que los posts enviados por los usuarios se almacenen correctamente en la base de datos para su uso posterior. Se mapeó el método `savePost` de la clase `PostController` para este microservicio.

![image](https://github.com/user-attachments/assets/1bce7472-8e34-43e5-8629-9b53ac095608)


`stream-function`: Esta función Lambda se encarga de proporcionar información sobre los posts almacenados en la base de datos MongoDB. Se mapeó el método `getPosts` de la clase `StreamController` para este microservicio. Al ser invocado, este método recupera los posts de la base de datos y los devuelve como respuesta al cliente que realizó la solicitud.

![image](https://github.com/user-attachments/assets/5b4b8439-f88d-4816-91fb-45e64448a305)


El uso de funciones Lambda de AWS permite una arquitectura sin servidor, escalable y de alto rendimiento.

### Pruebas

`post-function`: Se envía un nuevo post en formato JSON. Este será tomado por la clase tras la consulta y registrado en la base de datos. Si la ejecución es correcta, devolverá una respuesta HTTP con código 201.
![image](https://github.com/user-attachments/assets/a01e53a8-0d49-43b6-8f76-e1d274e37b96)

`stream-function`: No requiere input. Obtiene todos los registros de la base de datos.

![image](https://github.com/user-attachments/assets/dc2dc60b-94b3-40c0-9774-2948a2466a2e)



## Amazon S3

Utilizamos Amazon S3 para almacenar nuestros archivos estáticos, como HTML, CSS y JavaScript. Esto nos permite distribuir y servir estos archivos de manera eficiente a través de internet para nuestra aplicación web. Creamos un bucket `bucket-not-twitter` para nuestra aplicación:


![image](https://github.com/user-attachments/assets/73ce1b88-8be5-48b9-b8ea-3133f9be4668)


Habilitamos el alojamiento de un sitio web estático:



## Amazon Cognito

En nuestra aplicación, utilizamos Amazon Cognito para gestionar la autenticación de usuarios antes de que accedan al sitio web estático alojado en Amazon S3. Cuando un usuario intenta acceder al sitio web, lo redirigimos a una página de inicio de sesión vinculada a Amazon Cognito. Allí, el usuario proporciona sus credenciales de inicio de sesión y Amazon Cognito valida estas credenciales. Si son válidas, el usuario recibe un token de acceso que lo identifica. Con este token, el usuario es redirigido de vuelta al sitio web estático, donde el token se utiliza para validar su acceso y permitirle interactuar con la aplicación web estática de acuerdo con sus permisos. 

Para lograr este diseño se creo un grupo de usuarios llamado `not-twitter-users-group`


![image](https://github.com/user-attachments/assets/fd2fa8b9-4761-48cd-aced-27ef0fd03287)


Luego de autenticarse en un portal integrado por este servicio, se realiza la redirección al sitio web estatico enviando un token JWT en la URL del mismo.



Dicho token es esencial para poder consumir los servicios del back (endpoints del API Gateway). Para consultarlo desde la URL se ha modificado el JavaScript para que lea la URL, extraiga este token y lo almacene de tal forma que lo use cada vez que se consulten los servicios del back.

![image](https://github.com/user-attachments/assets/9589817a-a648-4403-ad71-3445e524cced)

![image](https://github.com/user-attachments/assets/23ce92d8-8ae3-44c6-854b-22af9e6393cb)

Con esto se asegura que solo aquellos agentes autorizados sean capaces de consumir los servicios de la solución.


## API Gateway 

En nuestra aplicación, hemos configurado el Gateway de API de AWS para actuar como el punto de entrada principal.


![image](https://github.com/user-attachments/assets/74fc802f-013a-477b-8c36-87b7b23b3f32)


Al definir recursos y métodos dentro de nuestra API, hemos establecido la estructura y el comportamiento de la interfaz que nuestra aplicación ofrece a los usuarios. Luego, hemos asignado nuestras funciones Lambda específicas para manejar estas solicitudes entrantes, asegurándonos de que cada solicitud se dirija al código adecuado para su procesamiento. 


![image](https://github.com/user-attachments/assets/69f58004-abb6-4180-ad08-e4003c239c45)


Seguridad de los endpoints:

![image](https://github.com/user-attachments/assets/9e9f9416-4628-45ad-96c9-aa4b2c75f06f)

![image](https://github.com/user-attachments/assets/73ff8759-e38b-4b2f-944e-fa6fc69642a5)

## Video Despliegue en AWS

[![Ver Video](https://drive.google.com/thumbnail?id=1wHzMNcL4qj1-vxmAXRMYiRHhb7tuG3cs)](https://drive.google.com/file/d/1wHzMNcL4qj1-vxmAXRMYiRHhb7tuG3cs/view?usp=sharing)




## Construido Con. 

- Maven - Administrador de dependencias

## Autor

- Julián David Triana Roa


## Versión
1.0.0

