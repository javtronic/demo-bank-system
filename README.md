# Banking System

Demo Backend and Frontend for transactions bank - Technical test.

## Tech Stack - Backend
- Java 21
- Spring Boot
- Spring Data JPA
- Gradle
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
- Estado de cuenta por cliente


## Ejecución del proyecto backend

### Requisitos
- Docker
- Docker Compose
- Java 21 (solo si se desea ejecutar sin Docker)

### Pasos
1. Clonar el repositorio
2. Ejecutar:

cd bank-api
./gradlew bootJar

3. Volver a la raíz del proyecto:

cd ..

4. Levantar los servicios:

docker compose up --build

##