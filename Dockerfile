# Use Amazon Corretto 17 JDK Alpine as the base image
FROM amazoncorretto:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container (version-agnostic)
COPY target/babylog-*.jar app.jar

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
