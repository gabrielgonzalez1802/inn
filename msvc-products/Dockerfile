FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn package install -DskipTests --also-make --threads=2

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/msvc-products-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
