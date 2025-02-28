# Proyecto Telefonica Inn

## Descripción

Este proyecto es una aplicación desarrollada con Spring Boot para gestionar los servicios de Telefonica Inn. Incluye varios microservicios que se ejecutan en conjunto para proporcionar una funcionalidad completa.

## Estructura del Proyecto

- `/msvc-warehouses`: Microservicio para la gestión de almacenes.
- `/msvc-users`: Microservicio para la gestión de usuarios.
- `/msvc-products`: Microservicio para la gestión de productos.
- `/msvc-payments`: Microservicio para la gestión de pagos.
- `/msvc-orders`: Microservicio para la gestión de pedidos.
- `/msvc-gateway-server`: Servidor de gateway API.
- `/msvc-eureka-server`: Servidor Eureka para el registro de servicios.
- `/msvc-entities`: Microservicio para la gestión de entidades.
- `/msvc-clients`: Microservicio para la gestión de clientes.
- `/msvc-address`: Microservicio para la gestión de direcciones.

## Requisitos Previos

- Java 17 o superior
- Maven
- Docker (opcional, para contenedores)

## Instalación

1. Clona el repositorio:

   ```bash
   git clone https://github.com/tu-usuario/telefonica-inn.git
   cd telefonica-inn
   ```

2. Navega a cada microservicio y ejecuta el siguiente comando para instalar las dependencias:
   ```bash
   mvn package install -DskipTests --also-make --threads=2 --batch-mode
   ```

## Ejecución de Servicios

Para ejecutar todos los servicios a la vez, puedes utilizar los siguientes comandos:

### Usando Docker

Si prefieres usar Docker, asegúrate de tener Docker instalado y ejecuta:

```bash
docker-compose up
```

### Usando Maven

Navega a cada microservicio y ejecuta:

```bash
./mvnw spring-boot:run
```

## Contribución

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -am 'Añadir nueva funcionalidad'`).
4. Sube tus cambios (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.



