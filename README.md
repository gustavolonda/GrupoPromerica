<center>
  <h1> MyWeather </h1>
</center>
<code><p align="center"><img height="400" src="https://github.com/gustavolonda/GrupoPromerica/blob/master/imagApp/swagger.png" title="app" style="display: block;margin-left: auto;margin-right: auto;"></p></code>

### Introducción

El proyecto está desarrollado en java con spring boot,y lee los  web services de Open Weather Map para obtener los datos. La base de datos que se utilizó es h2, que es una base de datos en memoria.
Está compuesta por 4 api:
El primero es para obtener el clima actual, el llamado se mediante el método get
Ejemplo:
GET: http://localhost:8095/api/v1/weather?lat=10.01&lon=-84.10
Response:
```{"status": "Ok",
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

El segundo es para  guardar una latitud y longitud, se usa el método post
Ejemplo:
POST: http://localhost:8095/api/v1/weather

Request:
```{
"lat": 1.01,
"lon": -8.10
                        }
```
Response:
```{
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

El tercero utiliza un método get para obtener todos datos guardados en la base de datos
Ejemplo:
GET: http://localhost:8095/api/v1/weather/history

Response:
```{
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


El cuarto responde 200 cada vez que es llamado y no tiene seguridad. Se puede llamar sin token y se usa el método get.
Ejemplo
GET: http://localhost:8095/sys/status

Herramientas utilizadas
Java 17
Spring boot 3.1.4
Gradle
H2
Spring Security
Auth0
Docker
Docker Compose
Kubernetes
Minikube
OpenWeather
Github


### Prueba Realizada

<code><p align="center"><img src="https://github.com/gustavolonda/GrupoPromerica/blob/master/imagApp/app.gif" title="app" style="display: block;margin-left: auto;margin-right: auto;"></p></code>
