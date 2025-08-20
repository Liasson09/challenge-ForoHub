# ForoHub API 🚀

API REST para gestión de foros desarrollada con Spring Boot y autenticación JWT.

## 📋 Descripción

ForoHub es una API que permite a los usuarios crear, gestionar y participar en tópicos de discusión. Incluye sistema de autenticación seguro y autorización basada en roles.

## 🛠️ Tecnologías

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security** (JWT)
- **Spring Data JPA**
- **MySQL**
- **Flyway** (Migraciones)
- **Maven**

## ⚡ Inicio Rápido

### Prerrequisitos
- Java 17+
- MySQL 8.0+
- Maven 3.6+

### Instalación

1. **Clonar el repositorio**
```bash
git clone <url-del-repositorio>
cd challenge-ForoHub
```

2. **Configurar base de datos**
```properties
# application.properties
spring.datasource.url=tu_bd
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

## 🔐 Autenticación

### Registro de usuario
```http
POST /usuarios
Content-Type: application/json

{
    "login": "usuario",
    "clave": "password123",
    "nombre": "Mi Nombre",
    "email": "email@ejemplo.com"
}
```

### Login
```http
POST /login
Content-Type: application/json

{
    "login": "usuario",
    "clave": "password123"
}
```

**Respuesta:**
```json
{
    "jwTtoken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

## 📚 Endpoints Principales

> **Nota:** Todos los endpoints de tópicos requieren autenticación JWT
> 
> `Authorization: Bearer {token}`

### Tópicos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/topicos` | Listar todos los tópicos |
| `POST` | `/topicos` | Crear nuevo tópico |
| `GET` | `/topicos/{id}` | Obtener tópico por ID |
| `PUT` | `/topicos/{id}` | Actualizar tópico* |
| `DELETE` | `/topicos/{id}` | Eliminar tópico* |
| `GET` | `/topicos/mis-topicos` | Listar mis tópicos |

_*Solo el autor del tópico puede actualizar o eliminar_

## 🔄 Estados de Tópicos

- `ABIERTO` - Tópico recién creado
- `EN_PROCESO` - En discusión
- `RESUELTO` - Problema solucionado
- `CERRADO` - Tópico cerrado

## 🛡️ Seguridad

- **Contraseñas encriptadas** con BCrypt
- **Tokens JWT** con expiración de 2 horas
- **Autorización por autor** para operaciones CRUD
- **Validación de datos** en todos los endpoints


## 📝 Notas Adicionales

- Los tópicos duplicados (mismo título y mensaje) no son permitidos
- Los tokens JWT expiran en 2 horas
- Solo el autor de un tópico puede modificarlo o eliminarlo
- La aplicación usa Flyway para manejar migraciones de base de datos

---

Desarrollado por **Milagros**
