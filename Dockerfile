# Etapa 1: Construcción del JAR
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final con el JAR compilado
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/parcial-final-n-capas-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
