# msvc-entities

Este proyecto es un microservicio que gestiona entidades, tipos de entidades y guías de Sumagro. Está diseñado para ser escalable y fácil de mantener, siguiendo las mejores prácticas de desarrollo de software.

## Estructura del Proyecto

El proyecto sigue una estructura organizada que facilita la navegación y el desarrollo:

- **src/main/java/com/inn/entities**: Contiene el código fuente del microservicio.
  - **config**: Clases de configuración para ModelMapper y OpenAPI.
  - **controllers**: Controladores que manejan las solicitudes HTTP.
  - **dtos**: Objetos de transferencia de datos que representan las entidades.
  - **entities**: Clases que representan las entidades en la base de datos.
  - **exceptions**: Clases que manejan las excepciones personalizadas.
  - **repositories**: Interfaces que definen las operaciones de acceso a datos.
  - **services**: Clases que contienen la lógica de negocio.
  - **utils**: Clases utilitarias que proporcionan métodos comunes.

- **src/main/resources**: Contiene archivos de configuración, como `application.properties`.

- **logs**: Archivos de registro para monitorear el comportamiento del servicio.

## Requisitos

- Java 11 o superior
- Maven
- Spring Boot

## Cómo Ejecutar el Proyecto

1. Clona el repositorio:
   ```
   git clone <url-del-repositorio>
   ```

2. Navega al directorio del proyecto:
   ```
   cd msvc-entities
   ```

3. Ejecuta el proyecto usando Maven:
   ```
   ./mvnw spring-boot:run
   ```

## Endpoints

El microservicio expone varios endpoints para interactuar con las entidades, tipos de entidades y guías de Sumagro. Consulta la documentación de la API para más detalles sobre los endpoints disponibles.

## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir, por favor abre un issue o un pull request.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.