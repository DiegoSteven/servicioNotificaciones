
Un microservicio Spring Boot diseñado para manejar notificaciones de sistemas de rastreo GPS, procesando eventos de plataformas como Traccar y entregando notificaciones a través de múltiples canales.

##  Descripción del Proyecto

Este microservicio actúa como un hub central de notificaciones en un ecosistema distribuido de rastreo GPS. Procesa eventos en tiempo real y entrega notificaciones a través de email, WebSocket, SMS, push notifications y plataformas de mensajería como Telegram.

##  Arquitectura del Sistema

El microservicio sigue una arquitectura por capas con separación clara de responsabilidades:

- **Capa Web**: Controladores REST para manejo de eventos y notificaciones
- **Capa de Servicio**: Lógica de negocio y orquestación de notificaciones  
- **Capa de Repositorio**: Acceso a datos y consultas
- **Capa de Integración**: Dispatchers para diferentes canales de notificación

## Stack Tecnológico

- **Spring Boot 3.4.4** - Framework principal
- **Java 17** - Plataforma de ejecución
- **Spring Data JPA** - Persistencia de datos
- **MySQL** - Base de datos principal
- **MongoDB** - Almacenamiento secundario
- **WebSocket** - Comunicación en tiempo real
- **Spring Mail** - Integración de email

## Prerrequisitos

- Java 17 o superior
- Maven 3.6+
- MySQL 8.0+
- MongoDB (opcional)
- Acceso a plataforma Traccar (puerto 8080)
- Servicio de sesiones (puerto 8082)

## Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone https://github.com/DiegoSteven/MicroservicioNotificaciones.git
cd MicroservicioNotificaciones
```

### 2. Configurar Base de Datos

Ejecutar el script SQL para crear la estructura de base de datos:

```bash
mysql -u root -p < database/notificationsdb.sql
```

### 3. Configurar application.properties

```properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/notificationsdb
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

# Configuración de email
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=tu_email@gmail.com
spring.mail.password=tu_app_password

# URLs de servicios externos
traccar.api.url=http://localhost:8080
session.service.url=http://localhost:8082
```

### 4. Compilar y Ejecutar

```bash
# Usando Maven Wrapper
./mvnw clean install
./mvnw spring-boot:run

# O usando Maven directamente
mvn clean install
mvn spring-boot:run
```

El servicio estará disponible en `http://localhost:8084`

## Endpoints Principales

### Procesamiento de Eventos
- `POST /api/notifications/events` - Recibe eventos de Traccar

### Gestión de Notificaciones
- `GET /api/notifications` - Obtener todas las notificaciones
- `POST /api/notifications` - Crear nueva notificación
- `PUT /api/notifications/{id}` - Actualizar notificación
- `DELETE /api/notifications/{id}` - Eliminar notificación

### Pruebas y Mensajería
- `POST /api/notifications/test` - Envía notificación de prueba
- `POST /api/notifications/test/{notificator}` - Prueba notificador específico
- `POST /api/notifications/send/{notificator}` - Envía mensaje a usuarios [3](#1-2) 

## Tipos de Eventos Soportados

El sistema soporta múltiples tipos de eventos GPS:

### Eventos de Estado del Dispositivo
- `deviceOnline` - Dispositivo conectado
- `deviceOffline` - Dispositivo desconectado
- `deviceInactive` - Dispositivo inactivo

### Eventos de Movimiento
- `deviceMoving` - Dispositivo en movimiento
- `deviceStopped` - Dispositivo detenido
- `deviceOverspeed` - Exceso de velocidad

### Eventos de Geocerca
- `geofenceEnter` - Entrada a geocerca
- `geofenceExit` - Salida de geocerca

### Otros Eventos
- `alarm` - Alarmas del dispositivo
- `ignitionOn` / `ignitionOff` - Encendido/apagado
- `maintenance` - Mantenimiento
- `driverChanged` - Cambio de conductor

## Canales de Notificación

### Activos
- **Email**: Notificaciones por correo electrónico
- **Web**: Notificaciones en tiempo real via WebSocket

### En Desarrollo (Comentados)
- **Pushover**: Push notifications
- **SMS**: Mensajes de texto
- **Telegram**: Bot de Telegram
- **Traccar Push**: Notificaciones móviles [4](#1-3) 

## Estructura de Base de Datos

El sistema utiliza las siguientes tablas principales:

- `tc_notifications` - Configuraciones de notificación
- `tc_user_notification` - Asociaciones usuario-notificación
- `tc_device_notification` - Asociaciones dispositivo-notificación
- `tc_group_notification` - Asociaciones grupo-notificación

## Pruebas

```bash
# Ejecutar pruebas
./mvnw test

# Probar notificación específica
curl -X POST http://localhost:8084/api/notifications/test/web \
  -H "Content-Type: application/json" \
  -b "JSESSIONID=tu_session_id"
```

## Configuración Avanzada

### Habilitar Notificadores Adicionales

Para habilitar notificadores comentados, descomenta las clases en:
- `src/main/java/com/example/services/Notificators/`

Y agrega las configuraciones necesarias en `application.properties`:

```properties
# Pushover
pushover.token=tu_app_token
pushover.defaultUserKey=tu_user_key

# Telegram
telegram.bot.token=tu_bot_token
telegram.chat.id=tu_chat_id

# Traccar Push
traccar.push.url=https://www.traccar.org/push/
traccar.push.key=tu_api_key
```

## Integración con Traccar

El microservicio está diseñado para integrarse con Traccar GPS. Configura Traccar para enviar eventos a:

```
POST http://localhost:8080/api/notifications/events
```

## Solución de Problemas

### Error de Conexión a Base de Datos
- Verificar que MySQL esté ejecutándose
- Confirmar credenciales en `application.properties`
- Asegurar que la base de datos `notificationsdb` existe

### Notificaciones No Se Envían
- Verificar configuración SMTP para email
- Comprobar logs del servicio
- Validar que las notificaciones estén configuradas con `always=true`

### Error de Autenticación
- Verificar que el servicio de sesiones esté ejecutándose en puerto 8082
- Confirmar que las cookies de sesión se están enviando correctamente

## Licencia

Este proyecto está bajo la Licencia Apache 2.0.

## Contribuir

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

