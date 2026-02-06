# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

# Runtime stage
FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=build /app/target/babylog-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
