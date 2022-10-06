[![Build Status](https://app.travis-ci.com/LeynerCortez/mutantsMeli.svg?branch=main)](https://app.travis-ci.com/github/LeynerCortez/mutantsMeli)

# mutantsMeli Prueba Técnica para MELI - Escenario Mutantes ADN
Repositorio para alojar la prueba técnica de developer en MELI, consta de una solución para identificar las secuencias de ADN de manera horizontal, vertical y en las diagonales de la matriz, buscando la manera más optima de solución. 
Para obtener el resultado de la secuencia de ADN se expuso el servicio /mutant, el cual si detecta una mutación en el ADN nos lo indica con un STATUS 200 de HTTP, en caso de que sea un humano, nos lo indica con un STATUS 403 de HTTP, a tener en cuenta que para este servicio no tenemos un body de respuesta de acuerdo a las indicaciones del reto.
se tiene un segundo servicio llamado /stats, esta entrega información de la cantidad de secuencias de ADN analizadas y el conteo de las que salieron positivas para mutante, y un campo de ratio que nos muestra la proporción de los dos valores analizados.

## Ejecutando el reto 💯
Durante la ejecución del reto me sentí cómodo con las tecnologías usadas 😎, pero encontrar la solución al recorrido de la matriz me tomo tiempo y paciencia 😥, la mejor estrategia fue dividir los recorridos y no intentar realizar un único recorrido, así entonces se capturan las filas, luego columnas y por ultimo diagonales hacia arriba y hacia abajo, se realizan las diferentes validaciones de la matriz y se almacenan las secuencias en un nuevo array que posteriormente con la ayuda de expresiones regulares ❤️, lograba encontrar secuencias de ADN validas.

Pero no todo paso así de bien, tuve momentos donde me enfrente a retos en temas que no conocía 😵, lo cual me hacia tomar mas tiempo del esperado, por ejemplo la configuración del pipeline CI de Travis, y también debo aceptar que tuve retos que no logre solucionar, como por ejemplo la integración de Travis con Codecov, pero lo importante es el aprendizaje que me llevo con la ejecución de este reto.

## Comenzando 🚀
Se cuenta con la documentación de nuestra API en Swagger, la cual puede ser accedida desde el endpoint https://mutants-leyner.herokuapp.com/swagger-ui/index.html#/ allí se encuentran los diferentes servicios que expone la api, la información de los DTO de request y de response.

![image](https://user-images.githubusercontent.com/45829438/194208826-228efc56-040b-4401-bf81-dd13df1da6f4.png)

  **POST** https://mutants-leyner.herokuapp.com/mutant
  ```javascript
  {"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
  ```
  Ejecución exitosa(isMutant) `200`
  
  ```javascript
  {"dna":["ATGCGA","CTGTGC","TTATGT","AGAAGG","CCTCTA","TCACTG"]}
  ```
  Ejecución no exitosa(isHuman) `403`
  
  ```javascript
  {"dna":["ATG","CTG","TTA"]}
  ```
  Ejecución no exitosa(BadRequest) `403`
  
  **GET** https://mutants-leyner.herokuapp.com/stats
  _Response:_ 
  ```javascript
  {
    "count_mutant_dna": 11,
    "count_human_dna": 20,
    "ratio": 0.55
}
  ```

La API esta expuesta en un servicio gratuito llamado Heroku, el cual nos facilita los despliegues de los proyectos desde el repositorio de GitHub.
El endpoint es: https://mutants-leyner.herokuapp.com/ este lo podemos acompañar por algún path identificado en nuestro Swagger como lo es el /mutant o el /stats.

El proyecto se llevó a cabo con una estructura basada en arquitectura Hexagonal, donde buscamos separar las capas y conectarla por medio de adaptadores.

![image](https://user-images.githubusercontent.com/45829438/194215730-78666b17-d52b-4227-85b4-ac4546ad501e.png)

Tenemos el adaptador de mongo para la conexión con la base de datos y otro adaptador Rest donde se encuentra el controlador.
En la capa de dominio tenemos los UseCase que controlan la lógica de cada operación solicitada en el controlador y un paquete Util donde está la clase encargada de validar el ADN.

### Pre-requisitos 📋
A tener en cuenta que para ejecutar este proyecto debemos de tener configurada de manera adecuada la base de datos, en mi caso use una base de datos Mongo no relacional para almacenar los conteos de los ADN que entran por el servicio /mutant.
La configuración de la conexión de la BD se realiza por medio del archivo _application.properties_ con la variable spring.data.mongodb.uri

La BD es una mongo Atlas que nos facilita su despliegue ya que está en un cluster en la nube de AWS, el nombre del esquema de la bd es meliMutants y la tabla se llama mutants como podemos ver en la siguiente imagen.

![image](https://user-images.githubusercontent.com/45829438/194211360-e64f9ab0-4669-42b3-ade8-602d6b7e88b7.png)

La versión de Java utilizada es la _11_, por lo que también debemos de tenerla configurada en nuestro entorno de desarrollador
La aplicación se trabajó con el manejador de dependencias Gradle _8.0_
Es una aplicación construida con el framework de SpringBoot 2.7.4
Para los logs se manejó el _Log4J_ que incorpora SpringBoot, solo se usa para ciertas revisiones en el chequeo del ADN.
Para tener nuestro ambiente local debemos de clonar el repositorio y previamente cumplir con los requisitos, se levanta el servidor con un _gradle bootRun_ el servidor está por defecto en el puerto 8080.

## Compilación 🛠️
El proyecto se configuro con el pipeline de CI de Travis, el cual nos permite verificar una ejecución exitosa de la compilación del gradle build.

![image](https://user-images.githubusercontent.com/45829438/194217644-5f78867f-a927-463b-a50e-ba063f66c9c6.png)


### Instalación 🔧 y Despliegue 📦
La instalación de la aplicación se llevó a cabo en Heroku.
  - Crear una cuenta en Heroku https://id.heroku.com/login
  - Conectar Heroku con GitHub
  - Asociar el repositorio
  - habilitar deployments automaticos luego de cada pullRequest en la rama principal main.
  - Crear en la raíz del proyecto los archivos Procfile y system.properties.
   
  ![image](https://user-images.githubusercontent.com/45829438/194213121-2b4e9695-000e-4895-8067-c36ba54a5dd4.png)
  
  - El archivo system.properties debe de especificar la versión de java con la que se creó el proyecto, para nuestro caso la versión 11 
    `java.runtime.version=11`
  - El archivo Procfile especifica el objetivo de nuestro Jar a desplegar y el puerto
    `web: java -Dserver.port=$PORT -jar build/libs/mutantsMeli-0.0.1-SNAPSHOT.jar`
  - Por ultimo, realizamos nuestro commit and push y la aplicación empezara a desplegarse y nos entregara los resultados con el endpoint generado.
    
## Ejecutando las pruebas ⚙️
Las pruebas se realizaron con Junit5 y Mockito
Aspectos importantes como que se realizaron pruebas a los Controller, UseCase, Service, Util(identifyDNA)
La clase identifyDNA encargada de encontrar las secuencias validas de ADN, tambien es la encargada de validar que la información enviada se encuentre correcta, para eso se hicieron 3 tipos de validaciones que se encuentran en la definición de los test:
  * El request solo debe contener los caracteres A-C-T-G
  * La matriz debe ser cuadrada NxN
  * Al existir mínimo una secuencia de 4, la matriz no debe ser menor a 4x4.
Para el tema de mongo se utilizo @DataMongoTest que nos permite interactuar con un base de datos especial para pruebas y totalmente funcional.
para medir la efectividad de las pruebas realizadas se configuro el plugin de `JaCoCo`, el reporte lo pueden encontrar en **\mutantsMeli\build\jacocoHtml\index.html**
Las pruebas realizadas alcanzaron un porcentaje del 84%.

![image](https://user-images.githubusercontent.com/45829438/194217225-bc815e05-3861-43b5-b844-96c8daba2733.png)
