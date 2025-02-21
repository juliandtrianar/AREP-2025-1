# TALLER DE DE MODULARIZACIÓN CON VIRTUALIZACIÓN E INTRODUCCIÓN A DOCKER

En este  taller se creó una aplicación web usando el micro-framework de Spark java, la aplicación web ofrece las siguientes funciones: Seno, Coseno, Palíndromo, Vector. Una vez implementada esta aplicación se procede a construir un container docker, desplegarlo y configurarlo en nuestra máquina local. Luego, creamos un repositorio en DockerHub y subiremos la imagen al repositorio.

## Diseño de la aplicación

La aplicación está diseñada para cumplir con los requisitos especificados en el enunciado del taller y proporcionar una experiencia de usuario fluida y satisfactoria. A continuación, se describen los principales componentes y características de la aplicación:

- La clase `SparkWebServer` define un servidor web Java utilizando el framework Spark, configurando rutas para manejar solicitudes HTTP GET en diferentes endpoints. Inicia el servidor en un puerto específico, y establece la ubicación de los archivos estáticos en el directorio `/public`. Además, implementa endpoints para calcular funciones trigonométricas, verificar palíndromos y calcular la distancia entre dos puntos en un plano cartesiano, extrayendo parámetros de las solicitudes HTTP.
- Se creó un contenedor Docker que encapsula la aplicación `SparkWebServer`, lo que facilita su despliegue y ejecución en diferentes entornos de manera consistente. Este contenedor se configuró para incluir todas las dependencias necesarias y se definió un archivo `Dockerfile` para construirlo adecuadamente. 

## Extensión de la aplicación.

- Se podrían implmentar características adicionales como soporte para otros métodos HTTP.

## Guía de Inicio

Las siguientes instrucciones le permitirán descargar una copia y ejecutar la aplicación en su máquina local.

### Prerrequisitos

- Java versión 8 OpenJDK
- Docker
- Maven
- Git

## Instalación 

### Docker

1. Abra la aplicación de escritorio de Docker.
![image](https://github.com/user-attachments/assets/26894d1c-9ba6-41ba-aaaf-3ffc8049556e)


2. Ejecute el siguiente comando para descargar la imagen del repositorio de docker:
   
     ``` docker run -d -p 34000:46000 --name unruffled_blackburn dockersparkprimer
     ```

   Debería ver algo así en consola:

![image](https://github.com/user-attachments/assets/fe2a2a25-c972-49c7-ac4e-35d5ad14bad4)

   Verifique en la aplicación de escritorio de Docker que el contenedor se esté ejecutando
   
   ![image](https://github.com/user-attachments/assets/9b125856-c928-4f0f-b181-543a6a902de6)


### Repositorio en GitHub

1. Ubíquese sobre el directorio donde desea realizar la descarga y ejecute el siguiente comando:
   
     ``` 
     git clone https://github.com/juliandtrianar/AREP-2025-1.git
      ```

2. Navegue al directorio del proyecto:
   
      ``` 
      cd taller4
      ```

3. Ejecute el siguiente comando para compilar el código:

      ``` 
      mvn compile 
      ```

5.  Ejecute el siguiente comando para empaquetar el proyecto:
   
      ``` 
      mvn package 
      ``` 

6. Para iniciar el servidor, ejecute el siguiente comando:

    ``` java -cp "target/classes;target/dependency/*" org.eci.arep.app.SparkWebServer 
    ```

7. Verifique en la linea de comandos que se está ejecutando la aplicación:

![image](https://github.com/user-attachments/assets/112c1a3f-4ce8-408d-9932-f161a59a6221)


   
## Probando la Aplicación.  

Ingrese a la siguiente URL para ingresar a el cliente: `http://localhost:34000/index.html`.
Si está ejecutando la aplicación desde la máquina virtual de java y no con el contenedor, el puerto es el `4567`.

Ingrese en los campos del formulario los valores, de clic en el botón `Search`:
![image](https://github.com/user-attachments/assets/336a1fc4-dc95-48a7-a8f5-af46a3fc42e5)



- Para las funciones Seno y Coseno ingresé el valor que desea calcular en radianes.
- Para la función palíndromo ingrese la palabra que quiere evaluar, si la palabra es palíndromo retornará true,  de lo contrario retornará false.
- Para la función Vector, ingrese las coordenadas de los puntos en el formato **x,y**

![image](https://github.com/user-attachments/assets/79ad8731-9bdb-46fb-88ab-98f4d5c9ea7b)


## Autor

- Julián David Triana Roa

## Construido con. 

- Maven - Administrador de dependencias

## Versión
1.0.0

