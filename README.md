# 📦 SISTEMA DE LOGÍSTICA - PROYECTO FINAL P2

## 📖 DESCRIPCIÓN
Sistema completo de gestión logística desarrollado en Java con JavaFX para la administración, creación y seguimiento de envíos.

## 🎯 FUNCIONALIDADES PRINCIPALES

### 👤 MÓDULO USUARIO
- **Registro y Login** - Sistema seguro de autenticación
- **Cotización** - Calculadora de costos de envío
- **Creación de Pedidos** - Formulario completo para nuevos envíos
- **Rastreo** - Seguimiento en tiempo real de envíos
- **Historial** - Consulta de todos los envíos realizados

### 👨‍💼 MÓDULO ADMINISTRADOR
- **Gestión de Usuarios** - Administración completa de usuarios
- **Aprobación de Repartidores** - Validación de solicitudes
- **Asignación de Envíos** - Distribución automática de pedidos
- **Reportes** - Generación de estadísticas y reportes
- **Sistema de Pagos** - Control de estados de pago

### 🚚 MÓDULO REPARTIDOR
- **Panel de Control** - Vista de envíos asignados
- **Actualización de Estados** - Cambio de estados de entrega
- **Gestión de Entregas** - Control completo del proceso

## 🛠️ TECNOLOGÍAS UTILIZADAS

| Tecnología | Versión | Uso |
|------------|---------|-----|
| Java | 17+ | Lenguaje principal |
| JavaFX | 19+ | Interfaz gráfica |
| FXML | - | Diseño de vistas |
| CSS | - | Estilos de interfaz |

## 📁 ESTRUCTURA DEL PROYECTO

## 🏗️ PATRONES DE DISEÑO IMPLEMENTADOS

| Patrón | Clase | Función |
|--------|-------|---------|
| **Factory** | `UserFactory` | Creación de usuarios |
| **Builder** | `UserBuilder` | Construcción de objetos |
| **Observer** | `User` | Notificaciones de estado |
| **Strategy** | `CalculadoraCostoEnvio` | Cálculo de costos |
| **Decorator** | `EnvioDecorator` | Servicios adicionales |
| **Facade** | `SistemaLogisticaFacade` | Simplificación del sistema |

## 🚀 INSTALACIÓN Y EJECUCIÓN

### **Requisitos Previos:**
- ☑️ Java JDK 17 o superior
- ☑️ IDE (IntelliJ, Eclipse o NetBeans)
- ☑️ Maven (incluido en el proyecto)

### **Pasos para Ejecutar:**
1. **Descargar el proyecto** desde GitHub
2. **Abrir en IntelliJ** → File → Open → Seleccionar carpeta
3. **Esperar** a que Maven descargue las dependencias
4. **Buscar el archivo:** `HelloApplication.java`
5. **Click derecho** → Run 'HelloApplication.main()'

### **Credenciales de Prueba:**
- **Admin:** admin@gmail.com / 0000
- **Usuario:** juan@gmail.com / 1234

## 👥 ROLES DEL SISTEMA

| Rol | Permisos | Descripción |
|-----|----------|-------------|
| **ADMIN** | Todos los permisos | Administrador completo del sistema |
| **USER** | Módulo usuario | Clientes que realizan envíos |
| **REPARTIDOR** | Módulo repartidor | Encargados de realizar entregas |

## 📞 INFORMACIÓN DEL PROYECTO

- **📅 Fecha:** noviembre 16
- **🎓 Curso:** Programación II
- **🏫 Universidad:** Uniquindio
- **👥 Integrantes:** juan mendez - stiven patiño 

## 🔗 ENLACES IMPORTANTES

- 📚 [Documentación Java](https://docs.oracle.com/javase/)
- 🎨 [Documentación JavaFX](https://openjfx.io/)
- 📖 [Patrones de Diseño](https://refactoring.guru/design-patterns)
