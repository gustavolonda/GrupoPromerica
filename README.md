<center>
  <h1> MyWeather </h1>
</center>
<code><p align="center"><img height="400" src="https://github.com/gustavolonda/GrupoPromerica/blob/master/imagApp/swagger.png" title="app" style="display: block;margin-left: auto;margin-right: auto;"></p></code>

### Introducción

El proyecto está desarrollado en java con spring boot y usa los  web services de Open Weather Map para obtener los datos. La base de datos que se utilizó es h2, la cual es una base de datos en memoria.

Está compuesta por 4 api:

* El primero es para obtener el clima actual, el llamado se mediante el método get.

GET: http://localhost:8095/api/v1/weather?lat=10.01&lon=-84.10

Response:
```
{
  "status": "Ok",
   "message": "El proceso terminó exitosamente",
   "result": {
       "id": "d8b39e19-9128-465c-af6b-af1958cf31a7",
       "lat": 10.01,
       "lon": -84.1,
       "weather": "Fog",
       "tempMin": 291.83,
       "tempMax": 294.0,
       "humidity": 87,
       "queryServerDate": "05-04-2024 02:19 AM UTC",
       "createDate": "05-04-2024 02:14 AM UTC",
       "modifyDate": "05-04-2024 02:19 AM UTC",
       "status": "A"
   }
}
```

* El segundo es para  guardar una latitud y longitud, se usa el método post.

POST: http://localhost:8095/api/v1/weather

Request:
```
{
  "lat": 1.01,
  "lon": -8.10
}
```
Response:
```
{
   "status": "Ok",
   "message": "El proceso terminó exitosamente",
   "result": {
       "id": "05f23da8-9f57-402d-b6e4-69eeea43f549",
       "lat": 1.01,
       "lon": -8.1,
       "weather": null,
       "tempMin": 0.0,
       "tempMax": 0.0,
       "humidity": 0,
       "queryServerDate": null,
       "createDate": "05-04-2024 02:56 AM UTC",
       "modifyDate": "05-04-2024 02:56 AM UTC",
       "status": "A"
   }
}
```

* El tercero utiliza un método get para obtener todos los datos guardados en la base de datos.

GET: http://localhost:8095/api/v1/weather/history

Response:
```
{
   "status": "Ok",
   "message": "El proceso terminó exitosamente",
   "result": [
       {
           "id": "d8b39e19-9128-465c-af6b-af1958cf31a7",
           "lat": 10.01,
           "lon": -84.1,
           "weather": "Fog",
           "tempMin": 291.83,
           "tempMax": 294.0,
           "humidity": 87,
           "queryServerDate": "05-04-2024 02:19 AM UTC",
           "createDate": "05-04-2024 02:14 AM UTC",
           "modifyDate": "05-04-2024 02:19 AM UTC",
           "status": "A"
       },
       {
           "id": "f17218ab-297e-4bc4-81e9-fac8ca9b1eb3",
           "lat": 23.34,
           "lon": 10.56,
           "weather": "Clouds",
           "tempMin": 297.95,
           "tempMax": 297.95,
           "humidity": 9,
           "queryServerDate": "05-04-2024 02:19 AM UTC",
           "createDate": "05-04-2024 02:19 AM UTC",
           "modifyDate": "05-04-2024 02:19 AM UTC",
           "status": "A"
       },
       {
           "id": "05f23da8-9f57-402d-b6e4-69eeea43f549",
           "lat": 1.01,
           "lon": -8.1,
           "weather": null,
           "tempMin": 0.0,
           "tempMax": 0.0,
           "humidity": 0,
           "queryServerDate": null,
           "createDate": "05-04-2024 02:56 AM UTC",
           "modifyDate": "05-04-2024 02:56 AM UTC",
           "status": "A"
       }
   ]
}


```


* El cuarto responde 200 cada vez que es llamado y no tiene seguridad. Se puede llamar sin token y se usa el método get.

GET: http://localhost:8095/sys/status

Link de Swagger: http://localhost:8095/swagger-ui/index.html/

### Herramientas utilizadas
* Java 17
* Spring boot 3.1.4
* Gradle
* H2
* Spring Security
* Auth0
* Docker
* Docker Compose
* Kubernetes
* Minikube
* OpenWeather
* Github
* Swagger
  
### Crear Imagen Docker
Para la creación de la imagen es necesario tener instalado docker y docker compose. Se ejecuta el comando  ` sudo docker-compose up --build -d `, dentro de la carpeta backend.
También se puede usar el comando ` sudo docker build -t backend_weather . -f ./myWeather/Dockerfile ` y se tiene que estar dentro de la carpeta backend

### Subir Imagen a Docker Hub
Para subir la imagen a docker hub se necesita tener una cuenta de usuario, crear un repositorio e iniciar sesión de usuario con la terminal (`docker login`).  Se hace una copia de la imagen con el nombre del repositorio de docker hub (`docker tag backend_weather glonda/weather`) y se sube con el comando: docker push glonda/weather

### Deployar en Kubernetes
Para deployar la imagen a docker, se necesita tener un hambiente de kubernetes. Yo instale Minikube, el cual es muy recomendable.
Se debe aplicar los archivos de deployment y el service para ejecutar la imagen. Se usa el comando  `kubectl apply -f ./deployment-weather.yaml -f ./my-weather.yaml` dentro de la carpeta kubernetes.

### Prueba Realizada
Dentro de la carpeta postman, se encuentra un archivo json con todo los datos necesarios. El cual debe ser importado en postman para realizar pruebas. 


<code><p align="center"><img src="https://github.com/gustavolonda/GrupoPromerica/blob/master/imagApp/app.gif" title="app" style="display: block;margin-left: auto;margin-right: auto;"></p></code>
