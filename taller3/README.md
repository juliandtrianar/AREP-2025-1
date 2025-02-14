# TALLER DE ARQUITECTURAS DE SERVIDORES DE APLICACIONES, META PROTOCOLOS DE OBJETOS, PATRÓN IOC, REFLEXIÓN

## Diseño de la aplicación

La aplicación está diseñada para cumplir con los requisitos especificados en el enunciado del taller y proporcionar una experiencia de usuario fluida y satisfactoria. A continuación, se describen los principales componentes y características de la aplicación:

Se definieron las siguientes anotaciones:

- `@Component`:  Se utiliza para marcar las clases como componentes que ofrecen servicios web.
- `@GetMapping`: Esta anotación se usa para mapear métodos a solicitudes HTTP GET. Cuando se aplica `@GetMapping` a un método dentro de un componente, se especifica la ruta o la URL relativa a la cual ese método manejará las solicitudes GET.
- `@PostMapping`: Esta anotación se usa para mapear métodos a solicitudes HTTP POST. Cuando se aplica `@PostMapping` a un método dentro de un componente, se especifica la ruta o la URL relativa a la cual ese método manejará las solicitudes POST.
- `@PathVariable`: Esta anotación se utiliza para mapear variables de la URL de una solicitud a parámetros en un método controlador.
- `@RequestParam`: Esta anotación se utiliza para vincular parámetros de solicitud HTTP a parámetros de método en controladores.
- `@RequestBody`: Esta anotación se utiliza para vincular un parámetro al cuerpo de la solicitud HTTP.

## Guía de Inicio

Las siguientes instrucciones le permitirán descargar una copia y ejecutar la aplicación en su máquina local.

### Prerrequisitos

- Java versión 8 OpenJDK
- Maven
- Git

## Instalación 

1. Ubíquese sobre el directorio donde desea realizar la descarga y ejecute el siguiente comando:
   
     ``` 
     git clone https://github.com/juliandtrianar/AREP-2025-1.git

      ```

2. Navegue al directorio del proyecto:
   
      ```
       cd taller3
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

    ```
     java -cp target/LAB3_AREP-1.0-SNAPSHOT.jar edu.escuelaing.arep.app.HTTPServer 
     ```

7. Verifique en la linea de comanos que se imprimió el mensaje **Listo para recibir ...**
   
![image](https://github.com/user-attachments/assets/9fd6e408-e214-4b13-9139-78e048a7fe5d)



## Construyendo aplicaciones

Las anotaciones  `GetMapping` y  `PostMapping` registran el valor con el cual el usuario puede acceder a los servicios con el atributo `value` produces, el atributo `produces` permite cambiar el tipo de retorno, por ejemplo `application/json`.

- `GetMapping`: La anotación @GetMapping se usa para mapear métodos de controlador a solicitudes HTTP GET. Se aplica sobre métodos de controlador y se especifica la ruta relativa del endpoint que manejará. Por ejemplo, al aplicar @GetMapping("/products") a un método getAllProducts(), este método manejará las solicitudes GET a la URL "/products". 

- `PostMapping`: La anotación @PostMapping se utiliza para mapear métodos de controlador a solicitudes HTTP POST. Al igual que @GetMapping, se aplica sobre métodos de controlador y se especifica la ruta relativa del endpoint que manejará. Por ejemplo, al aplicar @PostMapping("/products") a un método saveProduct(String newProduct), este método manejará las solicitudes POST a la URL "/products".
  
- `PathVariable`: En algunos casos, es necesario capturar variables de la URL dentro de los métodos del controlador. El valor de la variable de ruta se extraería de la URL y se pasaría al método como argumento. En esta versión, solo está implementado para una variable.

- `PathVariable`: Se utiliza para extraer los parámetros de una solicitud HTTP. En esta versión, solo está implementado para un parámetro.

Ejemplo de un servicio GET:

```
    @GetMapping(value = "/movies", produces = "application/json")
    public static String getMovieInformation(@RequestParam String title) throws IOException {
        APIController apiMovies = new APIController();
        return  apiMovies.connectToMoviesAPI(title);
    }
```
En este ejemplo, se tiene un método de controlador que maneja las solicitudes HTTP GET a la ruta "/movies" y produce una respuesta en formato JSON. `@RequestParam`: Esta anotación se utiliza para extraer parámetros de la URL de la solicitud HTTP. En este caso, el parámetro title se espera que esté presente en la URL de la solicitud GET. Por ejemplo, si la URL de la solicitud GET es "/movies?title=Wish", el valor "Wish" se asignará al parámetro title.

```
    @GetMapping(value = "/products/", produces = "application/json")
    public static String getProductsById(@PathVariable String id){
        return productService.getProductById(id).toString();
    }
```
Este método maneja las solicitudes GET dirigidas a la ruta `/products/` seguida de un identificador único de producto, como por ejemplo `/products/1`. Captura este identificador único de producto de la URL como un parámetro de ruta utilizando la anotación `@PathVariable`.

Ejemplo de un servicio POST:

```
    @PostMapping(value = "/products", produces = "application/json")
    public static String saveProduct(@RequestBody String newProduct){
        Product product = new Product(newProduct);
        productService.addProduct(product);
        return product.toString();
    }
```

Este método maneja las solicitudes POST dirigidas a la ruta "/products". Cuando se recibe una solicitud, espera que el cuerpo de la misma contenga información sobre un nuevo producto en formato JSON. Utilizando esta información, crea un nuevo objeto de tipo Product. Posteriormente, devuelve los detalles del producto recién creado.


## Probando la Aplicación.  

### Archivos Estáticos

Entrega archivos estáticos como páginas HTML, CSS, JS e imágenes:

![image](https://github.com/user-attachments/assets/b382618c-1229-4fe9-971b-c29d00366ac0)
![image](https://github.com/user-attachments/assets/8520d65e-5ca0-4e90-bab6-c8fc2b82f4ab)
![image](https://github.com/user-attachments/assets/fdbdf8ee-2d8c-4a8f-abe4-6b8cca230420)
![image](https://github.com/user-attachments/assets/800f9700-2326-4a00-be6d-9e95d5d9000e)


Si no se encuentra el archivo en el directorio especificado, se mostrará un mensaje de error:




### GET


`/hello`retorna un mensaje como el mostrado en el ejemplo dado en el enunciado:
![image](https://github.com/user-attachments/assets/0b67430f-1c12-4998-b33b-18141fe049b4)


`/movies` realiza una solicitud a una API de películas utilizando el título proporcionado en los parámetros de la consulta del URI y devuelve la respuesta en formato JSON.

![image](https://github.com/user-attachments/assets/8c8a072c-3164-44cf-9806-a26a2f2aa435)


Este mismo servicio puede ser usado por clientes web para dar un mejor formato a la salida de la consulta:
![image](https://github.com/user-attachments/assets/54e3d8b4-b37c-40b7-9a97-61bf1a2b0bb2)


`/products` muestra todos los productos registrado en el servicio.

![image](https://github.com/user-attachments/assets/0c375cbd-6047-4c65-a27a-744936b59835)



### POST

Se implementó un servicio sencillo para enviar al servidor solicitudes POST para la creación de productos:

![image](https://github.com/user-attachments/assets/5cce3956-5ad1-48bd-9dd0-f5491cee1a55)


El servidor retorna el JSON del producto creado.
Podemos acceder a todos los productos al acceder al servicio get con ruta `/products`

![image](https://github.com/user-attachments/assets/7e5c7393-8454-4f99-a8b5-db21f141f78c)


Si se quiere acceder a un producto en específico, se implementó un método que hace uso de la anotación `@PathVariable`:

![image](https://github.com/user-attachments/assets/3d277f65-75c2-417c-9e53-7ecef25894fe)


## Ejecutando las Pruebas.  

A continuación se muestra cómo ejecutar las pruebas desde la línea de comandos y un IDE como IntelliJ.

1. Navegue al directorio del proyecto con la línea de comandos.
2. Ejecute el siguiente comando:
   
   ```
    mvn test 
    ```
3. Debe mostrarse en pantalla que las pruebas fueron exitosas.

![image](https://github.com/user-attachments/assets/806a2340-a703-4ab3-8618-0798eede16ad)


## Construido Con. 

- Maven - Administrador de dependencias

## Versión
1.0.0

## Autor

- Julián David Triana Roa


