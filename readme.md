.<p align="center">🛠️ RentaHerramientas Pro v1.0</p><p align="center"><img src="https://img.shields.io/badge/Backend-Spring_Boot_3.2.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"><img src="https://img.shields.io/badge/Security-JWT_Stateless-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white" alt="JWT"><img src="https://img.shields.io/badge/Frontend-Vanilla_JS_ES6-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" alt="JS"><img src="https://img.shields.io/badge/Database-MySQL_8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"></p>🛰️ System ArchitectureEl ecosistema está basado en un modelo Decoupled Architecture (Arquitectura Desacoplada), separando totalmente la lógica de negocio del cliente visual.Fragmento de códigograph TD
    📝 Descripción del Proyecto 
RentaHerramientas es una plataforma integral desacoplada (Backend-Frontend) diseñada para digitalizar el proceso de alquiler de equipos de construcción. Permite a los Proveedores gestionar su inventario con carga de imágenes reales y a los Clientes reservar herramientas mediante una interfaz moderna, garantizando la seguridad transaccional a través de tokens JWT y una arquitectura robusta en Java.

🚀 Tecnologías Utilizadas
Lenguaje: Java 17 (LTS)

Framework: Spring Boot 3.2.5

Seguridad: Spring Security + JSON Web Token (JWT)

Base de Datos: MySQL 8.0

Persistencia: Spring Data JPA + Hibernate

Documentación: Swagger / OpenAPI 3.0

Frontend: JavaScript ES6, HTML5, CSS3 (Bootstrap 5)
🔧 Instalación y Configuración
 1. Clonar el repositorio
    git clone https://github.com/kevihxd/Proyecto_RentaHerramientas_BACKEND.git
cd Proyecto_RentaHerramientas_BACKEND

2. Configuración de la Base de Datos
Ejecuta el script SQL incluido en el proyecto para crear el schema y las tablas necesarias.

CREATE DATABASE db_renta_herramientas

3. Configuración del application.properties
Ubica el archivo en src/main/resources/application.properties y ajusta tus credenciales:

# Servidor
server.port=8080

# Conexión MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/db_renta_herramientas?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tu_contraseña_aqui

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Seguridad JWT
jwt.secret=UnaClaveSecretaMuyLargaParaElAlgoritmoHS256_2026_Campus
jwt.expiration=86400000

<img width="481" height="167" alt="image" src="https://github.com/user-attachments/assets/e910e2ba-6bd3-450b-9e57-bd97a5dc3868" />

Gemini said
Aquí tienes un README.md diseñado con un estándar profesional de ingeniería, optimizado para ser la pieza central de tu repositorio en GitHub y cumplir con todos los criterios de evaluación de Campuslands.

🛠️ RentaHerramientas Pro v1.0
📝 Descripción del Proyecto
RentaHerramientas es una plataforma integral desacoplada (Backend-Frontend) diseñada para digitalizar el proceso de alquiler de equipos de construcción. Permite a los Proveedores gestionar su inventario con carga de imágenes reales y a los Clientes reservar herramientas mediante una interfaz moderna, garantizando la seguridad transaccional a través de tokens JWT y una arquitectura robusta en Java.

🚀 Tecnologías Utilizadas
Lenguaje: Java 17 (LTS)

Framework: Spring Boot 3.2.5

Seguridad: Spring Security + JSON Web Token (JWT)

Base de Datos: MySQL 8.0

Persistencia: Spring Data JPA + Hibernate

Documentación: Swagger / OpenAPI 3.0

Frontend: JavaScript ES6, HTML5, CSS3 (Bootstrap 5)

🔧 Instalación y Configuración
1. Clonar el repositorio
Bash
git clone https://github.com/kevihxd/Proyecto_RentaHerramientas_BACKEND.git
cd Proyecto_RentaHerramientas_BACKEND
2. Configuración de la Base de Datos
Ejecuta el script SQL incluido en el proyecto para crear el schema y las tablas necesarias.

SQL
CREATE DATABASE db_renta_herramientas;
3. Configuración del application.properties
Ubica el archivo en src/main/resources/application.properties y ajusta tus credenciales:

Properties
# Servidor
server.port=8080

# Conexión MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/db_renta_herramientas?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tu_contraseña_aqui

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Seguridad JWT
jwt.secret=UnaClaveSecretaMuyLargaParaElAlgoritmoHS256_2026_Campus
jwt.expiration=86400000
📊 Diagrama Relacional
El sistema sigue un modelo relacional normalizado para garantizar la integridad de las rentas y el inventario:

(Nota: Sube tu imagen a la carpeta docs del repo y actualiza este link)

🔐 Autenticación y Roles
El sistema implementa RBAC (Role-Based Access Control). Cada solicitud protegida debe incluir el header: Authorization: Bearer <token_jwt>.

Rol	Permisos
CLIENTE	Explorar catálogo, crear reservas, ver historial propio.
PROVEEDOR	CRUD completo de sus herramientas (incluye imágenes), gestionar reservas.
ADMIN	Supervisión total de usuarios, herramientas y reportes financieros.
📑 Endpoints Principales (API)
Autenticación
POST /auth/register - Registro de nuevos usuarios.

POST /auth/login - Retorna el JWT.

Herramientas (Multipart para Imágenes)
POST /api/tools (Solo Proveedor)
<img width="409" height="156" alt="image" src="https://github.com/user-attachments/assets/bba685da-1cbb-43af-b077-6f267c384c1b" />
<img width="310" height="183" alt="image" src="https://github.com/user-attachments/assets/eccf9a41-e30b-4701-808e-3087fcfe9f66" />
🧪 Pruebas (Testing)
Pruebas Unitarias e Integración
Se utiliza JUnit 5 y Mockito para asegurar la calidad del código. Para ejecutar los tests desde la terminal:
<img width="405" height="85" alt="image" src="https://github.com/user-attachments/assets/7a4beb25-72b4-4321-8078-4a29038e7f4e" />
Para probar manualmente con Swagger UI:
Accede a http://localhost:8080/swagger-ui/index.html tras iniciar el servidor.

<img width="174" height="46" alt="image" src="https://github.com/user-attachments/assets/ffd7284d-1f99-4e1e-8a62-f0e49d5e4132" />
<img width="747" height="1024" alt="image" src="https://github.com/user-attachments/assets/33e6f4af-fbbb-49dc-9caa-c31e32a23676" />





