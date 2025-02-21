# HELP.md

# msvc-entities Project Help

This project, `msvc-entities`, is designed to manage entities, entity types, and Sumagro guides. Below are some key points to help you navigate and understand the structure and functionality of the project.

## Project Structure

- **src/main/java/com/inn/entities**: Contains the main Java source code for the application.
  - **config**: Configuration classes for ModelMapper and OpenAPI.
  - **controllers**: REST controllers that handle incoming requests related to entities, entity types, and Sumagro guides.
  - **dtos**: Data Transfer Objects that represent the data structures used in the application.
  - **entities**: Entity classes that map to the database tables.
  - **exceptions**: Custom exception classes for handling errors in the application.
  - **repositories**: Interfaces for database operations related to entities and their types.
  - **services**: Service classes that contain the business logic for managing entities and their types.
  - **utils**: Utility classes that provide common functionality used throughout the application.

## Running the Application

To run the application, use the following command:

```
./mvnw spring-boot:run
```

For Windows, use:

```
mvnw.cmd spring-boot:run
```

## Building the Project

To build the project, execute:

```
./mvnw clean install
```

## Logging

Logs for the services can be found in the `logs` directory. Each service has its own log file for tracking operations and errors.

## Docker

A Dockerfile is included for containerizing the application. To build the Docker image, run:

```
docker build -t msvc-entities .
```

## Additional Resources

For further information, refer to the documentation in the `README.md` file and the comments within the code for specific classes and methods.

If you encounter any issues or have questions, please reach out to the project maintainers.