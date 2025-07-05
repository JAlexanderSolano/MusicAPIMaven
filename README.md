# 🎵 MusicAPI

API REST para la gestión de listas de reproducción musicales, con autenticación JWT y consulta de géneros/artistas vía Spotify API.

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot 3.1
- Spring Security
- H2 Database
- Maven
- Swagger/OpenAPI 3
- API de Spotify

## 🔐 Autenticación

La API requiere autenticación con **JWT**. Primero debes obtener un token mediante:

POST http://localhost:8080/auth/login


### Ejemplo de request:
json
{
  "username": "usuario",
  "password": "contraseña"
}

Ejemplo de respuesta:
{
  "token": "eyJhbGciOiJIUzI1NiIsIn..."
}

Luego, agrega el token en las peticiones desde postman:
Authorization: Bearer <token>

📚 Documentación Swagger
http://localhost:8080/swagger-ui/index.html

Ahí puedes verificar todos los endpoints disponibles y su documentacion.

📂 Endpoints principales

| Método | Endpoint                         | Descripción                             |
| ------ | -------------------------------- | --------------------------------------- |
| POST   | `/auth/login`                    | Autenticación y obtención de JWT        |
| GET    | `/lists`                         | Obtener todas las listas                |
| POST   | `/lists`                         | Crear nueva lista de reproducción       |
| GET    | `/lists/{nombre}`                | Obtener lista por nombre                |
| DELETE | `/lists/{nombre}`                | Eliminar lista                          |
| POST   | `/lists/{nombre}/canciones`      | Agregar canción a lista                 |
| DELETE | `/lists/{nombre}/canciones/{id}` | Eliminar canción por ID de una lista    |
| GET    | `/spotify/artista/{nombre}`      | Buscar artista usando la API de Spotify |


🛠 Requisitos
JDK 17
Maven
Cuenta de desarrollador en Spotify Developer Dashboard

▶️ Cómo ejecutar el proyecto
1. Clona el repositorio:

git clone https://github.com/tu-usuario/MusicAPI.git
cd MusicAPI

2. Ejecuta la API:

mvn spring-boot:run

3. Accede a Swagger:
http://localhost:8080/swagger-ui/index.html



