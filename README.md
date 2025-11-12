# 🍽️ Sistema de Gestión de Restaurante - Sabor Gourmet

Sistema empresarial de gestión integral para el restaurante "Sabor Gourmet", desarrollado con **Spring Boot**, implementando **Programación Orientada a Aspectos (AOP)** y **Spring Security** para garantizar seguridad, auditoría y trazabilidad en todas las operaciones.

---

## 📋 Tabla de Contenidos

- [Descripción del Proyecto](#-descripción-del-proyecto)
- [Características Principales](#-características-principales)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación y Configuración](#-instalación-y-configuración)
- [Estructura del Proyecto](#️-estructura-del-proyecto)
- [Módulos Implementados](#-módulos-implementados)
- [Seguridad y Roles](#-seguridad-y-roles)
- [Aspecto de Auditoría (AOP)](#-aspecto-de-auditoría-aop)
- [Capturas de Pantalla](#-capturas-de-pantalla)
- [Acceso al Sistema](#-acceso-al-sistema)
- [Repositorio](#-repositorio)
- [Autor](#-autor)

---

## 🎯 Descripción del Proyecto

El **Sistema de Gestión de Restaurante "Sabor Gourmet"** es una aplicación empresarial diseñada para automatizar y optimizar las operaciones de un restaurante. El sistema permite:

- ✅ **Gestión de Clientes**: Registro y control de clientes frecuentes
- ✅ **Control de Mesas**: Gestión en tiempo real del estado de las mesas (Disponible, Ocupada, Mantenimiento)
- ✅ **Auditoría Completa**: Registro automático de todas las acciones mediante AOP
- ✅ **Seguridad Robusta**: Autenticación y autorización con Spring Security

### 🎓 Contexto Académico

Este proyecto fue desarrollado como parte de la evaluación de implementación de **AOP y Spring Security** en sistemas empresariales, cumpliendo con los siguientes lineamientos técnicos:

- Arquitectura basada en **Spring Boot 3+**
- Patrón **MVC (Model-View-Controller)**
- Vistas con **Thymeleaf** y diseño responsive con **Bootstrap 5**
- Persistencia con **Spring Data JPA + MySQL**

---

## ✨ Características Principales

### 🔒 Seguridad (Spring Security)
- Autenticación basada en usuario y contraseña cifrada (**BCrypt**)
- Autorización mediante roles: **ADMIN**, **MOZO**, **COCINERO**, **CAJERO**
- Restricción de acceso a rutas según permisos
- Sesiones seguras y protección CSRF

### 🧩 Programación Orientada a Aspectos (AOP)
- **Aspecto de Auditoría**: Registro automático de todas las operaciones CRUD
- Logging transversal de acciones (crear, actualizar, eliminar)
- Trazabilidad completa de cambios en el sistema
- Bitácora de auditoría almacenada en base de datos

### 🎨 Interfaz de Usuario
- Diseño moderno y profesional con **UX/UI optimizado**
- Paleta de colores elegante inspirada en "Gourmet"
- Sistema completamente responsive
- Feedback visual en tiempo real del estado de las mesas

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|:-----------|:--------|:----------|
| **Spring Boot** | 3.5.7 | Framework principal |
| **Java** | 17 | Lenguaje de programación |
| **Spring Security** | 6.x | Autenticación y autorización |
| **Spring Data JPA** | 3.x | Persistencia de datos |
| **Spring AOP** | 6.x | Programación orientada a aspectos |
| **Thymeleaf** | 3.x | Motor de plantillas |
| **MySQL** | 8.0+ | Base de datos |
| **Bootstrap** | 5.3.3 | Framework CSS |
| **Bootstrap Icons** | 1.11.3 | Iconografía |
| **Lombok** | - | Reducción de código boilerplate |
| **Maven** | 3.x | Gestor de dependencias |

---

## 📦 Requisitos Previos

Antes de instalar el proyecto, asegúrate de tener instalado:

- ☕ **Java JDK 17** o superior
- 🗄️ **MySQL Server 8.0** o superior
- 📦 **Maven 3.6+**
- 💻 **IDE**: IntelliJ IDEA, Eclipse o Visual Studio Code (recomendados)

---

## 🚀 Instalación y Configuración

### 1️⃣ Clonar el Repositorio

```bash
git clone https://github.com/Josue-Zapata-v/Gestion-Restaurante.git
cd Gestion-Restaurante
```

### 2️⃣ Crear la Base de Datos

Abre **MySQL Workbench** o tu cliente MySQL preferido y ejecuta:

```sql
CREATE DATABASE restaurant_gourmetdb;
```

> 📌 **Nota**: El nombre de la base de datos **debe ser exactamente** `restaurant_gourmetdb`

### 3️⃣ Configurar la Conexión (Opcional)

Si tu MySQL tiene usuario/contraseña diferente, edita el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/restaurant_gourmetdb?serverTimezone=America/Lima&useSSL=false
spring.datasource.username=root
spring.datasource.password=tu_contraseña_aqui
```

### 4️⃣ Compilar y Ejecutar

Opción A - Con Maven:
```bash
mvn clean install
mvn spring-boot:run
```

Opción B - Desde tu IDE:
1. Importa el proyecto como proyecto Maven
2. Ejecuta la clase principal `EvalS12Application.java`

### 5️⃣ Verificar la Instalación

El servidor se iniciará en el puerto **8081**. Deberías ver en consola:

```
Started EvalS12Application in X.XXX seconds
```

> 🎉 Las tablas se crearán automáticamente y se cargarán datos iniciales

---

## ⚙️ Estructura del Proyecto

### Controladores Principales

| Clase / Ruta | Descripción |
|:-------------|:------------|
| `MainController` <br> `/` | Maneja el redireccionamiento a la página de inicio (Mesas) |
| `TableController` <br> `/customers/tables` | Gestión y visualización del estado de las mesas (Disponible, Ocupada, Mantenimiento) |
| `CustomerController` <br> `/customers/list` | CRUD y gestión del estado (Activo/Inactivo) de los clientes registrados |
| `AdminController` <br> `/admin/bitacora` | Vista protegida para el registro de auditoría y trazabilidad de acciones |

### Arquitectura del Proyecto

```
src/main/java/com/tecsup/
├── config/              # Configuraciones de Spring Security
├── controller/          # Controladores MVC
├── model/              # Entidades JPA
├── repository/         # Interfaces de acceso a datos
├── service/            # Lógica de negocio
├── aspect/             # Aspectos AOP (Auditoría)
└── dto/                # Objetos de transferencia de datos

src/main/resources/
├── templates/          # Vistas Thymeleaf
│   ├── fragments/      # Componentes reutilizables (navbar)
│   └── customers/      # Vistas del módulo de clientes
├── static/             # Recursos estáticos (CSS, JS, imágenes)
└── application.properties
```

---

## 📊 Módulos Implementados

### 1. Módulo de Clientes y Mesas

#### 🎯 Objetivo
Gestionar la atención a clientes y el control de disponibilidad de mesas en tiempo real.

#### 📋 Proceso de Negocio

1. **Registro de Cliente** (opcional)
   - El cliente llega al restaurante
   - El recepcionista/mozo registra al cliente con su DNI, nombres, teléfono, etc.

2. **Asignación de Mesa**
   - Se asigna una mesa disponible al cliente
   - El estado de la mesa cambia automáticamente a "Ocupada"

3. **Liberación de Mesa**
   - Al finalizar el servicio, la mesa se libera
   - El estado cambia a "Disponible"

4. **Mantenimiento**
   - Las mesas pueden marcarse como "En Mantenimiento"
   - Quedan temporalmente fuera de servicio

#### 📦 Entidades Principales

**Cliente**
```java
idCliente (PK)
dni
nombres
apellidos
telefono
correo
estado (ACTIVO/INACTIVO)
```

**Mesa**
```java
idMesa (PK)
numero
capacidad
estado (DISPONIBLE/OCUPADA/MANTENIMIENTO)
```

#### ✅ Requerimientos Funcionales Implementados

- **RF1**: Sistema de registro y consulta de clientes ✔️
- **RF2**: Asignación y liberación de mesas mediante botones de acción ✔️
- **RF3**: Vista en tiempo real de mesas disponibles con badges de estado ✔️

---

## 🔐 Seguridad y Roles

### Sistema de Autenticación

El sistema utiliza **Spring Security** con las siguientes características:

- ✅ Contraseñas cifradas con **BCrypt**
- ✅ Sesiones seguras HTTP
- ✅ Protección CSRF habilitada
- ✅ Control de acceso basado en roles

### Roles Definidos

| Rol | Permisos | Rutas Accesibles |
|:----|:---------|:-----------------|
| **ADMIN** | Acceso total al sistema | Todas las rutas + `/admin/**` |
| **MOZO** | Gestión de mesas y clientes | `/customers/**`, `/pedidos/**` |
| **COCINERO** | Visualización de pedidos | `/pedidos/**` |
| **CAJERO** | Gestión de ventas | `/ventas/**` |

### Restricciones de Rutas

```java
/admin/**        → Solo ADMIN
/pedidos/**      → MOZO y COCINERO
/ventas/**       → CAJERO y ADMIN
/inventario/**   → Solo ADMIN
/customers/**    → ADMIN y MOZO
```

---

## 🔍 Aspecto de Auditoría (AOP)

### Implementación del Logging Aspect

El sistema implementa un **Aspecto de Auditoría** que registra automáticamente todas las operaciones CRUD realizadas en el sistema.

#### 📝 Funcionalidad

- **Punto de Corte**: Métodos de los servicios (`@Service`)
- **Eventos Capturados**:
  - ✅ Creación de registros (INSERT)
  - ✅ Actualización de registros (UPDATE)
  - ✅ Eliminación de registros (DELETE)
  - ✅ Cambios de estado

#### 🗄️ Bitácora (Audit Log)

Cada acción queda registrada con:

```java
idBitacora (PK)
accion          // Ej: "CREAR_CLIENTE", "CAMBIAR_ESTADO_MESA"
entidad         // Ej: "Cliente", "Mesa"
idEntidad       // ID del registro afectado
usuario         // Usuario que realizó la acción
fechaHora       // Timestamp de la operación
detalles        // Información adicional en JSON
```

#### 🔎 Vista de Auditoría

Los administradores pueden consultar la bitácora completa en:

```
http://localhost:8081/admin/bitacora
```

Esta vista muestra:
- ✅ Historial completo de operaciones
- ✅ Filtrado por fecha, usuario, entidad
- ✅ Detalles de cada transacción
- ✅ Trazabilidad completa del sistema

---

## 🖼️ Capturas de Pantalla

### Login
![Login](https://github.com/user-attachments/assets/5b6c0a40-1f27-4221-95b9-6515e56c71dd)

*Sistema de autenticación con diseño elegante y profesional*

---

### Dashboard - Gestión de Mesas
![Gestión de Mesas](https://github.com/user-attachments/assets/b4c866f5-8465-42f8-b873-d63e23372875)

*Vista en tiempo real del estado de todas las mesas con acciones rápidas*

---

### Gestión de Clientes
![Gestión de Clientes](https://github.com/user-attachments/assets/32d25c2a-2e6f-47f9-aae8-238dd6ff0e9a)

*Tabla interactiva para administrar clientes registrados*

---

### Bitácora de Auditoría
![Bitácora](https://github.com/user-attachments/assets/f0661028-5f1c-4c00-848c-559e5e5581bf)

*Registro completo de todas las operaciones del sistema*

---

## 🔑 Acceso al Sistema

### Credenciales por Defecto

Una vez iniciado el servidor, accede al sistema:

**URL**: `http://localhost:8081`

**Usuario Administrador**:
- **Usuario**: `admin`
- **Contraseña**: `admin`

> ⚠️ **Importante**: Cambia estas credenciales en producción por seguridad

### Cambiar Puerto (Opcional)

Si deseas usar otro puerto, edita `application.properties`:

```properties
server.port=9090  # Cambia 8081 por el puerto deseado
```

---

## 📚 Requerimientos No Funcionales Cumplidos

### Seguridad
- ✅ **RNF1**: Las contraseñas se almacenan cifradas con BCrypt
- ✅ **RNF2**: Solo usuarios autenticados pueden acceder al sistema
- ✅ **RNF3**: Cada acción se registra en la bitácora mediante AOP

### Rendimiento
- ✅ Consultas optimizadas con JPA
- ✅ Índices en campos clave
- ✅ Lazy loading para relaciones

### Usabilidad
- ✅ Interfaz intuitiva y responsive
- ✅ Feedback visual inmediato
- ✅ Navegación clara con navbar vertical

---

## 🔧 Configuración Avanzada

### Variables de Entorno (Producción)

Para mayor seguridad en producción, usa variables de entorno:

```bash
export DB_URL=jdbc:mysql://localhost:3306/restaurant_gourmetdb
export DB_USER=tu_usuario
export DB_PASSWORD=tu_password_seguro
```

Y modifica `application.properties`:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

---

## 📂 Repositorio

**GitHub**: [https://github.com/Josue-Zapata-v/Gestion-Restaurante-.git](https://github.com/Josue-Zapata-v/Gestion-Restaurante.git)

### Comandos Git Útiles

```bash
# Clonar
git clone https://github.com/Josue-Zapata-v/Gestion-Restaurante.git

# Ver ramas
git branch -a

# Pull de cambios
git pull origin main
```

---

## 🐛 Solución de Problemas Comunes

### Error: "Access denied for user"
**Solución**: Verifica tu usuario y contraseña de MySQL en `application.properties`

### Error: "Table doesn't exist"
**Solución**: Asegúrate de haber creado la base de datos `restaurant_gourmetdb`

### Error: "Port 8081 already in use"
**Solución**: Cambia el puerto en `application.properties` o detén el proceso que usa el puerto 8081

### No carga datos iniciales
**Solución**: Verifica que el archivo de configuración de datos iniciales esté en `src/main/resources`

---

## 🚀 Próximas Mejoras

- [ ] Módulo de Pedidos completo
- [ ] Gestión de Inventario
- [ ] Sistema de Facturación
- [ ] Reportes y Analytics
- [ ] API REST para integración con apps móviles
- [ ] Notificaciones en tiempo real con WebSockets

---

## 👨‍💻 Autor

**Josué Zapata**

- GitHub: [@Josue-Zapata-v](https://github.com/Josue-Zapata-v)
- Proyecto: Sistema de Gestión de Restaurante - Sabor Gourmet
- Institución: TECSUP

---

## 📄 Licencia

Este proyecto fue desarrollado con fines académicos como parte de la evaluación de implementación de AOP y Spring Security.

---

## 🙏 Agradecimientos

- Al profesor Coello Palomino Ricardo por la guía en el desarrollo

---

<div align="center">

**⭐ Si te gustó este proyecto, dale una estrella en GitHub ⭐**

Desarrollado con ❤️ y ☕ por [Josué Zapata](https://github.com/Josue-Zapata-v)

</div>
