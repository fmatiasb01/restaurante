# 🍽️ Sistema de Gestión de Restaurante

![Java](https://img.shields.io/badge/Java-21-orange?style=flat&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green?style=flat&logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat&logo=mysql)
![Docker](https://img.shields.io/badge/Docker-Ready-blue?style=flat&logo=docker)

Sistema REST API para la gestión integral de pedidos y clientes de un restaurante, desarrollado con Spring Boot y arquitectura de patrones de diseño.

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Arquitectura](#-arquitectura)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación y Ejecución](#-instalación-y-ejecución)
- [Endpoints API](#-endpoints-api)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Patrones de Diseño](#-patrones-de-diseño)

## ✨ Características

- **Gestión de Clientes**: Creación, listado y análisis de clientes
- **Gestión de Pedidos**: Sistema completo de pedidos con herencia de tipos
- **Análisis de Negocio**: Cálculo de promedios mensuales y mejor cliente
- **Historial de Pedidos**: Búsqueda de pedidos por cliente mediante patrón Strategy
- **Manejo de Errores**: Sistema centralizado de excepciones
- **Generación de Recibos**: Exportación de pedidos a formato TXT
- **Base de Datos Relacional**: Persistencia con JPA/Hibernate
- **Containerización**: Configuración Docker lista para despliegue

## 🛠️ Tecnologías

### Backend
- **Java 21**: Lenguaje de programación principal
- **Spring Boot 3.x**: Framework para aplicaciones empresariales
- **Spring Data JPA**: Capa de persistencia y acceso a datos
- **Spring MVC**: Arquitectura REST para controladores
- **Jakarta EE**: APIs empresariales modernas

### Base de Datos
- **MySQL 8.0**: Sistema de gestión de base de datos relacional
- **Hibernate**: ORM para mapeo objeto-relacional

### Herramientas
- **Maven**: Gestión de dependencias y construcción
- **Docker & Docker Compose**: Containerización y orquestación
- **Lombok**: Reducción de código boilerplate

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas con separación de responsabilidades:

```
├── Controller Layer    → Endpoints REST
├── Service Layer       → Lógica de negocio
├── Repository Layer    → Acceso a datos
├── Model Layer         → Entidades JPA
├── DTO Layer           → Objetos de transferencia
└── Util Layer          → Utilidades y handlers
```


### Modelo de Datos

El sistema implementa **herencia de tablas** con estrategia Single Table:

- **Cliente**: Entidad principal con relación OneToMany a Pedidos
- **Pedido**: Clase base abstracta
  - **PedidoConFecha**: Extensión con timestamp automático
  - **PedidoPago**: Extensión con método de pago

## 📦 Requisitos Previos

- Java JDK 21 o superior
- Maven 3.8+
- MySQL 8.0+ (o usar Docker Compose)
- Docker y Docker Compose (opcional)

## 🚀 Instalación y Ejecución

### Opción 1: Ejecución Local

1. **Clonar el repositorio**
```shell script
git clone https://github.com/tu-usuario/restaurante.git
   cd restaurante
```


2. **Configurar la base de datos**
   
   Crear la base de datos en MySQL:
```sql
CREATE DATABASE restaurante_db;
```

   
   Editar `src/main/resources/application.properties` con tus credenciales:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/restaurante_db
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_password
```


3. **Compilar el proyecto**
```shell script
mvn clean install
```


4. **Ejecutar la aplicación**
```shell script
mvn spring-boot:run
```


La aplicación estará disponible en `http://localhost:8080`

### Opción 2: Ejecución con Docker

1. **Construir y levantar los servicios**
```shell script
docker-compose up --build
```


2. **Verificar que los contenedores estén corriendo**
```shell script
docker-compose ps
```


La aplicación y MySQL estarán disponibles automáticamente.

## 🌐 Endpoints API

### Clientes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/clientes` | Crear nuevo cliente |
| GET | `/clientes` | Listar todos los clientes |
| GET | `/clientes/mejor-cliente` | Obtener cliente con mayor promedio mensual |

**Ejemplo - Crear Cliente:**
```json
POST /clientes
{
  "nombre": "Juan Pérez",
  "email": "juan@example.com"
}
```


### Pedidos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/pedidos` | Crear nuevo pedido |
| GET | `/pedidos` | Listar todos los pedidos |
| GET | `/pedidos/historial/{nombreCliente}` | Historial de pedidos por cliente |
| GET | `/pedidos/promedio/{nombreCliente}` | Promedio mensual de gastos del cliente |

**Ejemplo - Crear Pedido:**
```json
POST /pedidos
{
  "descripcion": "Pizza Margherita + Bebida",
  "total": 25.50,
  "confirmado": true,
  "cliente": {
    "id": 1
  }
}
```


**Respuesta - Mejor Cliente:**
```json
GET /clientes/mejor-cliente
{
  "nombre": "Juan Pérez",
  "promedioMensual": 250.75
}
```


## 📁 Estructura del Proyecto

```
src/main/java/com/ejemplo/restaurante/
│
├── controller/
│   ├── ClienteController.java
│   └── PedidoController.java
│
├── dto/
│   └── MejorClienteDTO.java
│
├── model/
│   ├── Cliente.java
│   ├── Pedido.java
│   ├── PedidoConFecha.java
│   └── PedidoPago.java
│
├── repository/
│   ├── ClienteRepository.java
│   └── PedidoRepository.java
│
├── service/
│   ├── ClienteService.java
│   ├── PedidoService.java
│   ├── PedidoBusquedaStrategy.java
│   └── PedidoBusquedaPorCliente.java
│
├── util/
│   ├── GlobalExceptionHandler.java
│   ├── PedidoFormatter.java
│   ├── PedidoPrinter.java
│   └── TxtPedidoFormatter.java
│
└── RestauranteApplication.java
```


## 🎨 Patrones de Diseño

Este proyecto implementa varios patrones de diseño para mantener un código limpio y escalable:

### Strategy Pattern
- **Interfaz**: `PedidoBusquedaStrategy`
- **Implementación**: `PedidoBusquedaPorCliente`
- **Propósito**: Permite diferentes estrategias de búsqueda de pedidos

### Template Method Pattern
- **Interfaz**: `PedidoFormatter`
- **Implementación**: `TxtPedidoFormatter`
- **Propósito**: Define el esqueleto para imprimir recibos en diferentes formatos

### Repository Pattern
- Spring Data JPA proporciona repositorios para abstraer el acceso a datos

### DTO Pattern
- `MejorClienteDTO` separa la representación de datos de las entidades

### Dependency Injection
- Inyección de dependencias mediante constructores en toda la aplicación

## 🔧 Configuración Adicional

### Variables de Entorno

Puedes configurar las siguientes variables de entorno:

```shell script
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/restaurante_db
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=tu_password
SERVER_PORT=8080
```


### Generación de Recibos

Los recibos se generan automáticamente en formato TXT en el directorio raíz del proyecto con el nombre `recibo_pedido_{id}.txt`

## 📝 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## 👤 Autor

**Facundo Matias Bertoldo**

- GitHub: [@fmatiasb01](https://github.com/fmatiasb01)

---

⭐️ Si este proyecto te ha sido útil, no olvides darle una estrella!
