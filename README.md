# Banking System

Demo Backend and Frontend for transactions bank - Technical test.

## Tech Stack - Backend
- Java 21
- Gradle 8.x
- Spring Boot
- JPA/Hibernate, QueryDSL
- PostgreSQL
- Docker

## Tech Stack - Frontend
- Angular 17
- Node: 22.9.0
- npm: 11.7.0

## Database
BaseDatos.sql

## Architecture
Server-Client application with layers structure.

## Funcionalidades
- Creación y gestión de clientes
- Creación e inactivación de cuentas
- Registro de movimientos (créditos y débitos)
- Consulta de movimientos por cuenta y rango de fechas


## Ejecución del proyecto backend

### Requisitos
- Docker
- Docker Compose
- Java 21 (solo si se desea ejecutar sin Docker)

### Pasos levantar Backend
1. Clonar el repositorio
2. Ejecutar desde la raiz del proyecto:

```cd bank-api```

```./gradlew bootJar```

3. Volver a la raíz del proyecto:

```cd ..```

4. Levantar los servicios:

```docker compose up --build```

5. Se exponen los servicios en una url como esta: ```http://localhost:8080/sfk-bank-api```

### Pasos levantar Backend
1. Con el repositorio ya clonado ejecutar en la raiz:

```cd bank-ng```

2. Instalar dependecias

```npm install```

3. Levantar Frontend

```npm start```

4. Se debe navegar a la url como esta: ```http://localhost:4200```
