# Use a lightweight Java 17 base image
FROM openjdk:17-jdk-alpine

  # Set working directory inside the container
WORKDIR /app

  # Copy the JAR file from target to container
COPY target/*.jar app.jar

  # Expose the default Spring Boot port
EXPOSE 8080

  # Run the jar file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
